package edu.bdic.forbiddenisland.network;

import com.fasterxml.jackson.databind.JsonNode;

public class Message {
    private MessageType type;
    private String sessionId;
    private Integer playerId;
    private JsonNode payload;

    public Message() {}
    public Message(MessageType type, String sessionId, Integer playerId, JsonNode payload) {
        this.type = type;
        this.sessionId = sessionId;
        this.playerId = playerId;
        this.payload = payload;
    }

    public MessageType getType() {
        return type;
    }

    public String getSessionId() {
        return sessionId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public JsonNode getPayload() {
        return payload;
    }
}
