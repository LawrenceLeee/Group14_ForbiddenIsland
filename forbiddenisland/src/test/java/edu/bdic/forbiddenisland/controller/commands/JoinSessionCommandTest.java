package edu.bdic.forbiddenisland.controller.commands;

import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class JoinSessionCommandTest {

    @BeforeEach
    void setUp() {
        // 每次测试前重置 SessionManager
        SessionManager.getInstance().clear();
    }

    @Test
    void testExecuteDoesNothing() {
        JoinSessionCommand cmd = new JoinSessionCommand("anySession");
        // execute 不做任何操作，不应抛异常
        assertDoesNotThrow(cmd::execute, "execute 不应抛出异常");
    }

    @Test
    void testToMessageConstructsCorrectMessage() {
        // 设置 SessionManager 中的 playerId
        SessionManager.getInstance().setSession("ignored", 12, false);

        String targetSession = "target123";
        JoinSessionCommand cmd = new JoinSessionCommand(targetSession);

        Message msg = cmd.toMessage();

        assertEquals(MessageType.JOIN, msg.getType(), "消息类型应为 JOIN");
        assertEquals(targetSession, msg.getSessionId(), "Message.sessionId 应与构造时传入的 sessionId 一致");
        assertEquals(12, msg.getPlayerId(), "Message.playerId 应取自 SessionManager");
        assertNull(msg.getPayload(), "payload 应为 null");
    }

    @Test
    void testFromMessageReturnsNewInstanceWithCorrectSessionId() throws Exception {
        String incomingSession = "incoming456";
        Message fakeMsg = new Message(
                MessageType.JOIN,
                incomingSession,
                0,
                null
        );

        JoinSessionCommand cmd = JoinSessionCommand.fromMessage(fakeMsg);
        assertNotNull(cmd, "fromMessage 不应返回 null");

        // 通过反射获取私有 sessionId 字段，验证其值
        Field sessionField = JoinSessionCommand.class.getDeclaredField("sessionId");
        sessionField.setAccessible(true);
        String actualSessionId = (String) sessionField.get(cmd);

        assertEquals(incomingSession, actualSessionId, "反射读取的 sessionId 应与 Message 中一致");
    }
}
