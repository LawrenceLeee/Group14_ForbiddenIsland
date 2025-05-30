package edu.bdic.forbiddenisland.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 发布/订阅房间事件
 */
public class RoomEventManager {
    private static final RoomEventManager INSTANCE = new RoomEventManager();
    private final CopyOnWriteArrayList<RoomEventListener> listeners = new CopyOnWriteArrayList<>();
    private RoomEventManager() {}
    public static RoomEventManager getInstance() { return INSTANCE; }
    public void addListener(RoomEventListener l) { listeners.addIfAbsent(l); }
    public void removeListener(RoomEventListener l) { listeners.remove(l); }
    public void fireRoomCreated(String sessionId) {
        System.out.println("[EventBus] fireRoomCreated " + sessionId);
        listeners.forEach(l -> l.onRoomCreated(sessionId));
    }

    public void fireRoomList(List<String> sessions) {
        listeners.forEach(l -> l.onRoomList(sessions));
    }
    public void fireRoomJoined(String sessionId, int playerIndex) {
        listeners.forEach(l -> l.onRoomJoined(sessionId, playerIndex));
    }
    public void fireGameStarted() {
        listeners.forEach(RoomEventListener::onGameStarted);
    }
}

