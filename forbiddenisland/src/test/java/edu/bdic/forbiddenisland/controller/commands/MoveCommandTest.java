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

class MoveCommandTest {

    @BeforeEach
    void setUp() {
        // 每次测试前重置 SessionManager
        SessionManager.getInstance().clear();
    }

    @Test
    void testExecuteInvokesGameModelMovePlayerWithCorrectParameters() {
        int playerIndex = 4;
        int toTileIndex = 10;
        MoveCommand cmd = new MoveCommand(playerIndex, toTileIndex);

        // 静态 Mock GameModel.getInstance()
        GameModel mockModel = mock(GameModel.class);
        try (MockedStatic<GameModel> modelStatic = mockStatic(GameModel.class)) {
            modelStatic.when(GameModel::getInstance).thenReturn(mockModel);

            // 调用 execute，不应抛出异常
            assertDoesNotThrow(cmd::execute);

            // 验证 GameModel.movePlayer(4, 10) 被调用一次
            modelStatic.verify(GameModel::getInstance, times(1));
            verify(mockModel, times(1)).movePlayer(eq(playerIndex), eq(toTileIndex));
        }
    }

    @Test
    void testToMessageConstructsProperMessage() {
        // 设置 SessionManager 中的 sessionId 和 playerId
        SessionManager.getInstance().setSession("sessMove", 8, false);

        int playerIndex = 8; // 虽然构造参数使用，但 toMessage 用的是 SessionManager.getPlayerId()
        int toTileIndex = 15;
        MoveCommand cmd = new MoveCommand(playerIndex, toTileIndex);

        Message msg = cmd.toMessage();

        assertEquals(MessageType.MOVE, msg.getType(), "消息类型应为 MOVE");
        assertEquals("sessMove", msg.getSessionId(), "sessionId 应来自 SessionManager");
        assertEquals(8, msg.getPlayerId(), "playerId 应来自 SessionManager");

        assertNotNull(msg.getPayload(), "payload 不应为 null");
        assertTrue(msg.getPayload().has("to"), "payload 应包含 to 字段");
        assertEquals(toTileIndex, msg.getPayload().get("to").asInt(),
                "payload.to 值应与构造时 toTileIndex 一致");
    }

    @Test
    void testFromMessageParsesFieldsCorrectly() throws Exception {
        int expectedPlayerId = 5;
        int expectedTo = 20;
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("to", expectedTo);

        Message fakeMsg = new Message(
                MessageType.MOVE,
                "ignoredSession",
                expectedPlayerId,
                payload
        );

        MoveCommand cmd = MoveCommand.fromMessage(fakeMsg);

        Field playerField = MoveCommand.class.getDeclaredField("playerIndex");
        playerField.setAccessible(true);
        int actualPlayerIndex = playerField.getInt(cmd);
        assertEquals(expectedPlayerId, actualPlayerIndex,
                "反射读取的 playerIndex 应与 Message.getPlayerId() 一致");

        Field toField = MoveCommand.class.getDeclaredField("toTileIndex");
        toField.setAccessible(true);
        int actualToTileIndex = toField.getInt(cmd);
        assertEquals(expectedTo, actualToTileIndex,
                "反射读取的 toTileIndex 应与 payload 中的 \"to\" 一致");
    }
}
