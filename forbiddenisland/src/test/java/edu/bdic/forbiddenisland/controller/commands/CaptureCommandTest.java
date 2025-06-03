package edu.bdic.forbiddenisland.controller.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CaptureCommandTest {

    @BeforeEach
    void setUp() {
        // 每次测试前，清空 SessionManager
        SessionManager.getInstance().clear();
    }

    @Test
    void testExecuteInvokesGameModelCaptureWithCorrectPlayerId() {
        // 1. 给 SessionManager 设置某个 playerId
        SessionManager.getInstance().setSession("sess123", 7, false);

        // 2. 用 MockedStatic 拦截 GameModel.getInstance()
        GameModel mockModel = mock(GameModel.class);
        try (MockedStatic<GameModel> modelStatic = mockStatic(GameModel.class)) {
            modelStatic.when(GameModel::getInstance).thenReturn(mockModel);

            // 3. 调用 execute
            new CaptureCommand().execute();

            // 4. 验证 GameModel.captureTreasure(7) 被调用一次
            verify(mockModel, times(1)).captureTreasure(eq(7));
        }
    }

    @Test
    void testExecuteDoesNotThrowWhenCaptureFails() {
        // 如果 GameModel.captureTreasure 返回 false，也不应抛异常
        SessionManager.getInstance().setSession("sessXYZ", 42, false);

        GameModel mockModel = mock(GameModel.class);
        when(mockModel.captureTreasure(eq(42))).thenReturn(false);

        try (MockedStatic<GameModel> modelStatic = mockStatic(GameModel.class)) {
            modelStatic.when(GameModel::getInstance).thenReturn(mockModel);

            assertDoesNotThrow(() -> new CaptureCommand().execute());
            // 即便返回 false，也不会抛异常
        }
    }

    @Test
    void testToMessageUsesSessionManagerValues() {
        // 1. 设置 SessionManager 中的 sessionId 和 playerId
        SessionManager.getInstance().setSession("sessionABC", 99, true);

        // 2. 调用 toMessage
        Message msg = new CaptureCommand().toMessage();

        // 3. 验证 Message 各字段
        assertEquals(MessageType.CAPTURE, msg.getType(), "MessageType 应该是 CAPTURE");
        assertEquals("sessionABC", msg.getSessionId(), "sessionId 应取自 SessionManager");
        assertEquals(99, msg.getPlayerId(), "playerId 应取自 SessionManager");

        // payload 应是一个空的 ObjectNode
        assertTrue(msg.getPayload() instanceof ObjectNode, "payload 应是 ObjectNode");
        ObjectNode node = (ObjectNode) msg.getPayload();
        assertTrue(node.isEmpty(), "payload ObjectNode 应为空");
    }

    @Test
    void testFromMessageReturnsNewInstance() {
        // 传入一个 Message，不关注具体内容
        Message dummy = mock(Message.class);
        CaptureCommand cmd1 = CaptureCommand.fromMessage(dummy);
        CaptureCommand cmd2 = CaptureCommand.fromMessage(dummy);

        // 每次都应返回一个新的 CaptureCommand 实例
        assertNotNull(cmd1);
        assertNotNull(cmd2);
        assertNotSame(cmd1, cmd2, "fromMessage 应返回不同的新实例");
    }
}
