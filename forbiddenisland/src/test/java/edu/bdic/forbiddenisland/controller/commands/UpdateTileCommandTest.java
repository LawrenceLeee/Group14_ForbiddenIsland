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

class UpdateTileCommandTest {

    @BeforeEach
    void setUp() {
        // 每次测试前重置 SessionManager
        SessionManager.getInstance().clear();
    }

    @Test
    void testExecuteInvokesGameModelUpdateTileStatusWithCorrectParameters() {
        int tileIndex = 8;
        String status = "FLOODED";
        UpdateTileCommand cmd = new UpdateTileCommand(tileIndex, status);

        // 静态 Mock GameModel.getInstance()
        GameModel mockModel = mock(GameModel.class);
        try (MockedStatic<GameModel> modelStatic = mockStatic(GameModel.class)) {
            modelStatic.when(GameModel::getInstance).thenReturn(mockModel);

            // 调用 execute，不应抛异常
            assertDoesNotThrow(cmd::execute);

            // 验证 GameModel.updateTileStatus(8, "FLOODED") 被调用一次
            modelStatic.verify(GameModel::getInstance, times(1));
            verify(mockModel, times(1)).updateTileStatus(eq(tileIndex), eq(status));
        }
    }

    @Test
    void testToMessageConstructsProperMessage() {
        // 设置 SessionManager 中的 sessionId 和 playerId
        SessionManager.getInstance().setSession("sessUpdate", 13, false);

        int tileIndex = 5;
        String status = "DRY";
        UpdateTileCommand cmd = new UpdateTileCommand(tileIndex, status);

        Message msg = cmd.toMessage();

        assertEquals(MessageType.UPDATE_TILE, msg.getType(), "消息类型应为 UPDATE_TILE");
        assertEquals("sessUpdate", msg.getSessionId(), "sessionId 应来自 SessionManager");
        assertEquals(13, msg.getPlayerId(), "playerId 应来自 SessionManager");

        assertNotNull(msg.getPayload(), "payload 不应为 null");
        assertTrue(msg.getPayload().has("tile"), "payload 应包含 tile 字段");
        assertTrue(msg.getPayload().has("status"), "payload 应包含 status 字段");
        assertEquals(tileIndex, msg.getPayload().get("tile").asInt(),
                "payload.tile 值应与构造时 tileIndex 一致");
        assertEquals(status, msg.getPayload().get("status").asText(),
                "payload.status 值应与构造时 status 一致");
    }

    @Test
    void testFromMessageParsesFieldsCorrectly() throws Exception {
        int expectedTile = 2;
        String expectedStatus = "SUNK";
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("tile", expectedTile);
        payload.put("status", expectedStatus);

        Message fakeMsg = new Message(
                MessageType.UPDATE_TILE,
                "ignored",
                0,
                payload
        );

        UpdateTileCommand cmd = UpdateTileCommand.fromMessage(fakeMsg);

        Field tileField = UpdateTileCommand.class.getDeclaredField("tileIndex");
        tileField.setAccessible(true);
        int actualTileIndex = tileField.getInt(cmd);
        assertEquals(expectedTile, actualTileIndex,
                "反射读取的 tileIndex 应与 payload 中的 tile 一致");

        Field statusField = UpdateTileCommand.class.getDeclaredField("status");
        statusField.setAccessible(true);
        String actualStatus = (String) statusField.get(cmd);
        assertEquals(expectedStatus, actualStatus,
                "反射读取的 status 应与 payload 中的 status 一致");
    }
}
