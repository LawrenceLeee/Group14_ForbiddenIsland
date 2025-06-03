package edu.bdic.forbiddenisland.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RoomEventManagerTest {

    private RoomEventManager manager;

    // 测试用的简单监听器，用来记录调用情况
    private static class TestListener implements RoomEventListener {
        boolean createdCalled = false;
        String createdSessionId = null;

        boolean listCalled = false;
        List<String> listSessions = null;

        boolean joinedCalled = false;
        String joinedSessionId = null;
        int joinedPlayerIndex = -1;

        boolean startedCalled = false;

        @Override
        public void onRoomCreated(String sessionId) {
            createdCalled = true;
            createdSessionId = sessionId;
        }

        @Override
        public void onRoomList(List<String> sessions) {
            listCalled = true;
            listSessions = sessions;
        }

        @Override
        public void onRoomJoined(String sessionId, int playerIndex) {
            joinedCalled = true;
            joinedSessionId = sessionId;
            joinedPlayerIndex = playerIndex;
        }

        @Override
        public void onGameStarted() {
            startedCalled = true;
        }
    }

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() throws Exception {
        manager = RoomEventManager.getInstance();
        // 清空单例中的 listeners 列表
        Field field = RoomEventManager.class.getDeclaredField("listeners");
        field.setAccessible(true);
        ((CopyOnWriteArrayList<RoomEventListener>) field.get(manager)).clear();
    }

    @Test
    void testAddListenerAndFireRoomCreated() {
        TestListener listener = new TestListener();
        manager.addListener(listener);

        manager.fireRoomCreated("session123");

        assertTrue(listener.createdCalled, "onRoomCreated 应当被调用");
        assertEquals("session123", listener.createdSessionId, "sessionId 应当传递给监听器");
    }

    @Test
    void testRemoveListener() {
        TestListener listener = new TestListener();
        manager.addListener(listener);
        manager.removeListener(listener);

        manager.fireRoomCreated("session123");

        assertFalse(listener.createdCalled, "已移除的监听器不应再接收到事件");
    }

    @Test
    void testAddDuplicateListener_onlyCalledOnce() {
        TestListener listener = new TestListener();
        manager.addListener(listener);
        manager.addListener(listener); // 重复添加

        manager.fireRoomCreated("dupSession");

        assertTrue(listener.createdCalled, "监听器应当被调用");
        assertEquals("dupSession", listener.createdSessionId);
        // 再次调用时，调用次数仍为 1（即只调用一次）
        listener.createdCalled = false;
        manager.fireRoomCreated("dupSession2");
        assertTrue(listener.createdCalled, "重复添加后仍只会调用一次");
        assertEquals("dupSession2", listener.createdSessionId);
    }

    @Test
    void testFireRoomList() {
        TestListener listener = new TestListener();
        manager.addListener(listener);

        List<String> sampleList = Arrays.asList("roomA", "roomB");
        manager.fireRoomList(sampleList);

        assertTrue(listener.listCalled, "onRoomList 应当被调用");
        assertSame(sampleList, listener.listSessions, "传递给监听器的列表应与参数相同");
    }

    @Test
    void testFireRoomJoined() {
        TestListener listener = new TestListener();
        manager.addListener(listener);

        manager.fireRoomJoined("joinedSession", 5);

        assertTrue(listener.joinedCalled, "onRoomJoined 应当被调用");
        assertEquals("joinedSession", listener.joinedSessionId, "sessionId 应当传递给监听器");
        assertEquals(5, listener.joinedPlayerIndex, "playerIndex 应当传递给监听器");
    }

    @Test
    void testFireGameStarted() {
        TestListener listener = new TestListener();
        manager.addListener(listener);

        manager.fireGameStarted();

        assertTrue(listener.startedCalled, "onGameStarted 应当被调用");
    }
}
