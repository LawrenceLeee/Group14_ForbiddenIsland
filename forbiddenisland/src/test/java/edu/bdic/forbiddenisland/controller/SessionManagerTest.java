package edu.bdic.forbiddenisland.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionManagerTest {

    private SessionManager mgr;

    @BeforeEach
    void setUp() {
        mgr = SessionManager.getInstance();
        // 在每次测试前重置为初始状态
        mgr.clear();
    }

    @Test
    void testDefaultState() {
        // 新实例（单例）的默认值应是空字符串、-1 和 false
        assertEquals("", mgr.getSessionId(), "默认 sessionId 应该是空字符串");
        assertEquals(-1, mgr.getPlayerId(), "默认 playerId 应该是 -1");
        assertFalse(mgr.isHost(), "默认 isHost 应该是 false");
    }

    @Test
    void testSetSessionAndGetters() {
        mgr.setSession("ABC123", 42, true);
        assertEquals("ABC123", mgr.getSessionId(), "getSessionId 应返回刚设置的 sessionId");
        assertEquals(42, mgr.getPlayerId(), "getPlayerId 应返回刚设置的 playerId");
        assertTrue(mgr.isHost(), "isHost 应返回刚设置的 true");
    }

    @Test
    void testClearResetsToDefaults() {
        mgr.setSession("XYZ", 7, true);
        // 确保先设置了非默认值
        assertEquals("XYZ", mgr.getSessionId());
        assertEquals(7, mgr.getPlayerId());
        assertTrue(mgr.isHost());

        mgr.clear();
        // 清空后应回到默认
        assertEquals("", mgr.getSessionId(), "clear 后 sessionId 应重置为空字符串");
        assertEquals(-1, mgr.getPlayerId(), "clear 后 playerId 应重置为 -1");
        assertFalse(mgr.isHost(), "clear 后 isHost 应重置为 false");
    }
}
