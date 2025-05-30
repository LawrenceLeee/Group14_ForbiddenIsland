package edu.bdic.forbiddenisland.network;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static String toJson(Message msg) {
        try { return MAPPER.writeValueAsString(msg); }
        catch (Exception e) { throw new RuntimeException(e); }
    }
    public static Message fromJson(String json) {
        try { return MAPPER.readValue(json, Message.class); }
        catch (Exception e) { throw new RuntimeException(e); }
    }
}
