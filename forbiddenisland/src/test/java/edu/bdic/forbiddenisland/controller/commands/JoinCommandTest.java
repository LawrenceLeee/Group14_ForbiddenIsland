package edu.bdic.forbiddenisland.controller.commands;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class JoinCommandTest {

    @BeforeEach
    void setUp() {
        // 每次测试前重置 SessionManager
        SessionManager.getInstance().clear();
    }

    @Test
    void testExecuteDoesNothing() {
        JoinCommand cmd = new JoinCommand(4);
        // execute 什么都不做，不应抛异常
        assertDoesNotThrow(cmd::execute, "execute 不应抛出异常");
    }

    @Test
    void testToMessageConstructsCorrectMessage() {
        // 设置 SessionManager
        SessionManager.getInstance().setSession("sessJoin", 8, false);

        int playerIndex = 3;
        JoinCommand cmd = new JoinCommand(playerIndex);

        // 调用 toMessage()
        Message msg = cmd.toMessage();

        assertEquals(MessageType.JOIN, msg.getType(), "消息类型应为 JOIN");
        assertEquals("sessJoin", msg.getSessionId(), "sessionId 应来自 SessionManager");
        assertEquals(playerIndex, msg.getPlayerId(), "playerId 应为构造时的 playerIndex");

        assertNotNull(msg.getPayload(), "payload 不应为 null");
        assertTrue(msg.getPayload().has("playerIndex"), "payload 应包含 playerIndex 字段");
        assertEquals(playerIndex, msg.getPayload().get("playerIndex").asInt(),
                "payload.playerIndex 值应与构造时一致");
    }

    @Test
    void testFromMessageParsesFieldCorrectly() throws Exception {
        int expectedIndex = 5;
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("playerIndex", expectedIndex);

        Message fakeMsg = new Message(
                MessageType.JOIN,
                "ignored",
                0,
                payload
        );

        JoinCommand cmd = JoinCommand.fromMessage(fakeMsg);

        Field indexField = JoinCommand.class.getDeclaredField("playerIndex");
        indexField.setAccessible(true);
        int actualIndex = indexField.getInt(cmd);
        assertEquals(expectedIndex, actualIndex,
                "反射读取的 playerIndex 应与 payload 中一致");
    }
}
