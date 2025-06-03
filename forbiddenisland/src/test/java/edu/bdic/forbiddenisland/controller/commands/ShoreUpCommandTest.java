package edu.bdic.forbiddenisland.controller.commands;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ShoreUpCommandTest {

    @BeforeEach
    void setUp() {
        // 每次测试前重置 SessionManager
        SessionManager.getInstance().clear();
    }

    @Test
    void testExecuteInvokesGameModelShoreUpWithCorrectTileIndex() {
        int tileIndex = 12;
        ShoreUpCommand cmd = new ShoreUpCommand(tileIndex);

        // Mock GameModel.getInstance()
        GameModel mockModel = mock(GameModel.class);
        try (MockedStatic<GameModel> modelStatic = mockStatic(GameModel.class)) {
            modelStatic.when(GameModel::getInstance).thenReturn(mockModel);

            // 调用 execute，不应抛出异常
            assertDoesNotThrow(cmd::execute);

            // 验证 GameModel.shoreUpTile(12) 被调用一次
            modelStatic.verify(GameModel::getInstance, times(1));
            verify(mockModel, times(1)).shoreUpTile(eq(tileIndex));
        }
    }

    @Test
    void testToMessageConstructsProperMessage() {
        // 设置 SessionManager 中的 sessionId 和 playerId
        SessionManager.getInstance().setSession("sessShore", 21, false);

        int tileIndex = 7;
        ShoreUpCommand cmd = new ShoreUpCommand(tileIndex);

        Message msg = cmd.toMessage();

        assertEquals(MessageType.SHORE_UP, msg.getType(), "消息类型应为 SHORE_UP");
        assertEquals("sessShore", msg.getSessionId(), "sessionId 应来自 SessionManager");
        assertEquals(21, msg.getPlayerId(), "playerId 应来自 SessionManager");

        assertNotNull(msg.getPayload(), "payload 不应为 null");
        assertTrue(msg.getPayload().has("tile"), "payload 应包含 tile 字段");
        assertEquals(tileIndex, msg.getPayload().get("tile").asInt(),
                "payload.tile 值应与构造时 tileIndex 一致");
    }

    @Test
    void testFromMessageParsesFieldCorrectly() throws Exception {
        int expectedTile = 5;
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("tile", expectedTile);

        Message fakeMsg = new Message(
                MessageType.SHORE_UP,
                "ignored",
                0,
                payload
        );

        ShoreUpCommand cmd = ShoreUpCommand.fromMessage(fakeMsg);

        Field tileField = ShoreUpCommand.class.getDeclaredField("tileIndex");
        tileField.setAccessible(true);
        int actualTileIndex = tileField.getInt(cmd);
        assertEquals(expectedTile, actualTileIndex,
                "反射读取的 tileIndex 应与 payload 中的 tile 值一致");
    }
}
