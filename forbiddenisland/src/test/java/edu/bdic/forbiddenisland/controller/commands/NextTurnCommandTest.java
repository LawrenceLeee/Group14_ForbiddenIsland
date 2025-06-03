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
import static org.mockito.Mockito.*;

class NextTurnCommandTest {

    @BeforeEach
    void setUp() {
        // 每次测试前重置 SessionManager
        SessionManager.getInstance().clear();
    }

    @Test
    void testExecuteInvokesGameModelEndTurn() {
        NextTurnCommand cmd = new NextTurnCommand();

        // 静态 Mock GameModel.getInstance()
        GameModel mockModel = mock(GameModel.class);
        try (MockedStatic<GameModel> modelStatic = mockStatic(GameModel.class)) {
            modelStatic.when(GameModel::getInstance).thenReturn(mockModel);

            // 调用 execute，不应抛异常
            assertDoesNotThrow(cmd::execute);

            // 验证 GameModel.endTurn() 被调用一次
            modelStatic.verify(GameModel::getInstance, times(1));
            verify(mockModel, times(1)).endTurn();
        }
    }

    @Test
    void testToMessageConstructsProperMessage() {
        // 设置 SessionManager 中的 sessionId 和 playerId
        SessionManager.getInstance().setSession("sessNext", 9, false);

        NextTurnCommand cmd = new NextTurnCommand();

        Message msg = cmd.toMessage();

        assertEquals(MessageType.NEXT_TURN, msg.getType(), "消息类型应为 NEXT_TURN");
        assertEquals("sessNext", msg.getSessionId(), "sessionId 应来自 SessionManager");
        assertEquals(9, msg.getPlayerId(), "playerId 应来自 SessionManager");

        assertNotNull(msg.getPayload(), "payload 不应为 null");
        assertTrue(msg.getPayload() instanceof ObjectNode, "payload 应为 ObjectNode");
        ObjectNode node = (ObjectNode) msg.getPayload();
        assertTrue(node.isEmpty(), "payload ObjectNode 应为空");
    }

    @Test
    void testFromMessageReturnsNewInstance() throws Exception {
        Message dummy = new Message(
                MessageType.NEXT_TURN,
                "ignored",
                0,
                JsonNodeFactory.instance.objectNode()
        );

        NextTurnCommand c1 = NextTurnCommand.fromMessage(dummy);
        NextTurnCommand c2 = NextTurnCommand.fromMessage(dummy);

        assertNotNull(c1, "fromMessage 不应返回 null");
        assertNotNull(c2, "fromMessage 不应返回 null");
        assertNotSame(c1, c2, "每次调用应返回不同的新实例");
    }
}
