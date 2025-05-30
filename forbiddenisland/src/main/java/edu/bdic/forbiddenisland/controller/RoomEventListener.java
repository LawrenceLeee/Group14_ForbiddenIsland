package edu.bdic.forbiddenisland.controller;

import java.util.List;

/**
 * 监听房间事件：创建、列表、加入、开始
 */
public interface RoomEventListener {
    void onRoomCreated(String sessionId);
    void onRoomList(List<String> sessions);
    void onRoomJoined(String sessionId, int playerIndex);
    void onGameStarted();
}
