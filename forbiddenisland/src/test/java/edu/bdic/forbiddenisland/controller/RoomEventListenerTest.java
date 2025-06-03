package edu.bdic.forbiddenisland.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 针对 RoomEventListener 接口的 “调用方法 + 参数” 验证测试。
 */
public class RoomEventListenerTest {

    private TestListener listener;

    // 用来记录监听器方法是否被调用，以及调用时的参数
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
            // 为了防止外部修改，复制一份
            listSessions = List.copyOf(sessions);
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
    void setUp() {
        listener = new TestListener();
    }

    @Test
    void testOnRoomCreated() {
        // 在调用之前，所有 flag 都应为 false
        assertFalse(listener.createdCalled);
        assertNull(listener.createdSessionId);

        // 模拟触发 onRoomCreated
        String sampleSessionId = "ABC123";
        listener.onRoomCreated(sampleSessionId);

        // 验证 listener 内部状态被正确更新
        assertTrue(listener.createdCalled, "onRoomCreated 应该被调用");
        assertEquals(sampleSessionId, listener.createdSessionId,
                "传入的 sessionId 应当与记录的一致");
    }

    @Test
    void testOnRoomList() {
        // 在调用之前，flag 都为 false
        assertFalse(listener.listCalled);
        assertNull(listener.listSessions);

        // 模拟触发 onRoomList
        List<String> sampleList = Arrays.asList("room1", "room2", "room3");
        listener.onRoomList(sampleList);

        // 验证 listener 内部状态
        assertTrue(listener.listCalled, "onRoomList 应该被调用");
        // 复制了一份，所以 value 相等即可
        assertEquals(sampleList, listener.listSessions,
                "传入的房间列表应当与记录的一致");
        // 原列表修改后，不影响 listener 内部记录（测试 copyOf 行为）
        sampleList.set(0, "modified");
        assertNotEquals(sampleList.get(0),
                listener.listSessions.get(0),
                "原列表修改后，listener 内部 listSessions 不应受影响");
    }

    @Test
    void testOnRoomJoined() {
        assertFalse(listener.joinedCalled);
        assertNull(listener.joinedSessionId);
        assertEquals(-1, listener.joinedPlayerIndex);

        String sampleSessionId = "XYZ789";
        int sampleIndex = 2;
        listener.onRoomJoined(sampleSessionId, sampleIndex);

        assertTrue(listener.joinedCalled, "onRoomJoined 应该被调用");
        assertEquals(sampleSessionId, listener.joinedSessionId,
                "传入的 sessionId 应当与记录的一致");
        assertEquals(sampleIndex, listener.joinedPlayerIndex,
                "传入的 playerIndex 应当与记录的一致");
    }

    @Test
    void testOnGameStarted() {
        assertFalse(listener.startedCalled, "调用前，startedCalled 应为 false");

        listener.onGameStarted();

        assertTrue(listener.startedCalled, "onGameStarted 应该被调用后，startedCalled 为 true");
    }
}
