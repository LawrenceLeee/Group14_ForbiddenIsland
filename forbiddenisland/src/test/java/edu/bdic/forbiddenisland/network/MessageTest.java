package edu.bdic.forbiddenisland.network;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void testParameterizedConstructorAndGetters() {
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("foo", "bar");
        Message msg = new Message(
                MessageType.MOVE,
                "session123",
                7,
                payload
        );

        assertEquals(MessageType.MOVE, msg.getType(), "getType 应返回构造时传入的类型");
        assertEquals("session123", msg.getSessionId(), "getSessionId 应返回构造时传入的 sessionId");
        assertEquals(7, msg.getPlayerId(), "getPlayerId 应返回构造时传入的 playerId");
        assertNotNull(msg.getPayload(), "getPayload 不应返回 null");
        assertEquals("bar", msg.getPayload().get("foo").asText(), "payload 中的 foo 字段应为 \"bar\"");
    }

    @Test
    void testPayloadCanBeNull() {
        Message msg = new Message(
                MessageType.CREATE_ROOM,
                "sessA",
                0,
                null
        );

        assertEquals(MessageType.CREATE_ROOM, msg.getType());
        assertEquals("sessA", msg.getSessionId());
        assertEquals(0, msg.getPlayerId());
        assertNull(msg.getPayload(), "当构造时 payload 为 null，getPayload 应返回 null");
    }

    @Test
    void testDefaultConstructorCreatesNullFields() {
        Message msg = new Message();
        assertNull(msg.getType(), "默认构造后 type 应为 null");
        assertNull(msg.getSessionId(), "默认构造后 sessionId 应为 null");
        // playerId 为 Integer 装箱类型，但 getPlayerId() 返回 int，会抛 NullPointerException
        assertThrows(NullPointerException.class, msg::getPlayerId,
                "默认构造后 playerId 为 null，调用 getPlayerId 应抛 NullPointerException");
        assertNull(msg.getPayload(), "默认构造后 payload 应为 null");
    }
}
