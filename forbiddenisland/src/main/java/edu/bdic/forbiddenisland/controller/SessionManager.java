package edu.bdic.forbiddenisland.controller;

/**
 * 单例会话管理：存储当前 sessionId、playerId 及主机标识
 */
public class SessionManager {
    private static final SessionManager INSTANCE = new SessionManager();

    private String sessionId = "";
    private int playerId = -1;
    private boolean isHost = false;

    private SessionManager() {}

    public static SessionManager getInstance() {
        return INSTANCE;
    }

    /**
     * 设置会话信息
     * @param sessionId 会话ID
     * @param playerId 本地玩家索引
     * @param isHost 是否为房主
     */
    public void setSession(String sessionId, int playerId, boolean isHost) {
        this.sessionId = sessionId;
        this.playerId  = playerId;
        this.isHost    = isHost;
    }

    /** 当前会话ID */
    public String getSessionId() {
        return sessionId;
    }

    /** 本地玩家索引 */
    public int getPlayerId() {
        return playerId;
    }

    /** 本地是否是房主 */
    public boolean isHost() {
        return isHost;
    }

    /** 重置（可选，用于退出重开） */
    public void clear() {
        sessionId = "";
        playerId  = -1;
        isHost    = false;
    }
}
