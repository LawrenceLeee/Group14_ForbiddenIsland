package edu.bdic.forbiddenisland.network;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import org.junit.jupiter.api.Test;

class JsonUtilTest {

    @Test
    void testToJsonAndFromJsonRoundTrip() {
        // 1. 构造一个带 payload 的 Message
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("key1", "value1");
        payload.put("key2", 123);
        Message original = new Message(
                MessageType.MOVE,
                "sessionABC",
                42,
                payload
        );

        // 2. 序列化为 JSON 字符串
        String json = JsonUtil.toJson(original);
        assertNotNull(json, "toJson 不应返回 null 或空字符串");
        assertTrue(json.contains("\"type\":\"MOVE\""), "JSON 字符串应包含 type 字段");
        assertTrue(json.contains("\"sessionId\":\"sessionABC\""), "JSON 字符串应包含 sessionId 字段");
        assertTrue(json.contains("\"playerId\":42"), "JSON 字符串应包含 playerId 字段");
        assertTrue(json.contains("\"payload\""), "JSON 字符串应包含 payload 对象");

        // 3. 反序列化回 Message 对象
        Message parsed = JsonUtil.fromJson(json);
        assertNotNull(parsed, "fromJson 不应返回 null");

        // 4. 验证各字段与原始 Message 一致
        assertEquals(original.getType(), parsed.getType(), "类型应保持一致");
        assertEquals(original.getSessionId(), parsed.getSessionId(), "sessionId 应保持一致");
        assertEquals(original.getPlayerId(), parsed.getPlayerId(), "playerId 应保持一致");
        assertNotNull(parsed.getPayload(), "payload 不应为 null");
        assertEquals("value1", parsed.getPayload().get("key1").asText(), "payload.key1 值应保持一致");
        assertEquals(123, parsed.getPayload().get("key2").asInt(), "payload.key2 值应保持一致");
    }

    @Test
    void testFromJsonWithMalformedInputThrowsRuntimeException() {
        String badJson = "{ not valid json }";
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            JsonUtil.fromJson(badJson);
        }, "传入无法解析的 JSON 时应抛出 RuntimeException");
        assertNotNull(ex.getCause(), "RuntimeException 的 cause 不应为 null");
    }
}
