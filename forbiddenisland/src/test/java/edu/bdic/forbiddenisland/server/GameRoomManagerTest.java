package edu.bdic.forbiddenisland.server;

import edu.bdic.forbiddenisland.model.Profession;
import io.netty.channel.Channel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameRoomManagerTest {

    private GameRoomManager manager;

    @BeforeEach
    void setUp() throws Exception {
        manager = GameRoomManager.getInstance();

        // 反射清空 GameRoomManager 内部状态
        Field roomsField = GameRoomManager.class.getDeclaredField("rooms");
        roomsField.setAccessible(true);
        ((Map<?, ?>) roomsField.get(manager)).clear();

        Field chToSessionField = GameRoomManager.class.getDeclaredField("chToSession");
        chToSessionField.setAccessible(true);
        ((Map<?, ?>) chToSessionField.get(manager)).clear();

        Field layoutsField = GameRoomManager.class.getDeclaredField("layouts");
        layoutsField.setAccessible(true);
        ((Map<?, ?>) layoutsField.get(manager)).clear();

        Field profsField = GameRoomManager.class.getDeclaredField("sessionProfessions");
        profsField.setAccessible(true);
        ((Map<?, ?>) profsField.get(manager)).clear();
    }

    @Test
    void testCreateRoomGeneratesUniqueSessionAndLayoutSize24() {
        String sessionId1 = manager.createRoom();
        String sessionId2 = manager.createRoom();

        assertNotNull(sessionId1);
        assertNotNull(sessionId2);
        assertNotEquals(sessionId1, sessionId2, "每次 createRoom 应生成不同的 sessionId");

        List<Integer> layout1 = manager.getLayoutForSession(sessionId1);
        List<Integer> layout2 = manager.getLayoutForSession(sessionId2);

        assertNotNull(layout1);
        assertNotNull(layout2);
        assertEquals(24, layout1.size(), "布局列表应为 24 个元素");
        assertEquals(24, layout2.size(), "布局列表应为 24 个元素");
        // 布局应随机无重复
        Set<Integer> set1 = new HashSet<>(layout1);
        Set<Integer> set2 = new HashSet<>(layout2);
        assertEquals(24, set1.size(), "layout 中不应有重复值");
        assertEquals(24, set2.size(), "layout 中不应有重复值");
    }

    @Test
    void testJoinAndGetNextPlayerIndexAndGetChannelsAndGetSessionFor() {
        String sessionId = manager.createRoom();
        Channel ch1 = mock(Channel.class);
        Channel ch2 = mock(Channel.class);

        // 刚创建，没有人 join，getNextPlayerIndex 返回 0
        assertEquals(0, manager.getNextPlayerIndex(sessionId));

        manager.join(sessionId, ch1);
        // join 之后 rooms.size 为 1，下次 playerIndex=1
        assertEquals(1, manager.getNextPlayerIndex(sessionId));
        assertEquals(sessionId, manager.getSessionFor(ch1), "getSessionFor 应返回对应 sessionId");

        Set<Channel> channels = manager.getChannels(sessionId);
        assertTrue(channels.contains(ch1), "getChannels 应包含已加入的 ch1");

        manager.join(sessionId, ch2);
        assertEquals(2, manager.getNextPlayerIndex(sessionId));
        assertEquals(sessionId, manager.getSessionFor(ch2));
        channels = manager.getChannels(sessionId);
        assertEquals(2, channels.size(), "getChannels 应返回两个通道");
        assertTrue(channels.contains(ch2));
    }

    @Test
    void testRecordAndGetSessionProfessions() {
        String sessionId = manager.createRoom();
        // 初始 professions 为空
        Map<Integer, Profession> empty = manager.getSessionProfessions(sessionId);
        assertTrue(empty.isEmpty(), "初始时 profession 表应为空");

        // 记录 profession
        manager.recordProfession(sessionId, 0, Profession.EXPLORER);
        manager.recordProfession(sessionId, 1, Profession.DIVER);

        Map<Integer, Profession> profMap = manager.getSessionProfessions(sessionId);
        assertEquals(2, profMap.size(), "应有两个玩家 profession 记录");
        assertEquals(Profession.EXPLORER, profMap.get(0));
        assertEquals(Profession.DIVER, profMap.get(1));
    }

    @Test
    void testAssignNextProfessionReturnsValidProfessionNotInUsedSet() {
        String sessionId = manager.createRoom();
        // 首次调用，无已用 profession，应返回任一值
        Profession first = manager.assignNextProfession(sessionId);
        assertNotNull(first);
        assertTrue(Arrays.asList(Profession.values()).contains(first),
                "assignNextProfession 应返回合法的 Profession");

        // 将该 profession 缓存到 sessionProfessions
        manager.recordProfession(sessionId, 0, first);

        // 再次调用，应返回不同于 first，直到用尽
        Profession second = manager.assignNextProfession(sessionId);
        assertNotNull(second);
        assertNotEquals(first, second, "第二次调用应避开已用的 profession");

        // 如果给定 profession map 填满所有 enums，则应任意返回一个
        for (int i = 0; i < Profession.values().length; i++) {
            manager.recordProfession(sessionId, i, Profession.values()[i]);
        }
        // 此时 used.size == all.length，因此 assignNextProfession 应可返回任意一个
        Profession any = manager.assignNextProfession(sessionId);
        assertNotNull(any);
        assertTrue(Arrays.asList(Profession.values()).contains(any));
    }

    @Test
    void testBroadcastToRoomOnlyActiveChannelsReceiveMessage() {
        String sessionId = manager.createRoom();
        Channel activeCh = mock(Channel.class);
        Channel inactiveCh = mock(Channel.class);

        when(activeCh.isActive()).thenReturn(true);
        when(inactiveCh.isActive()).thenReturn(false);

        manager.join(sessionId, activeCh);
        manager.join(sessionId, inactiveCh);

        String jsonMsg = "{\"test\":\"msg\"}";
        manager.broadcastToRoom(sessionId, jsonMsg);

        verify(activeCh, times(1)).writeAndFlush(eq(jsonMsg));
        verify(inactiveCh, never()).writeAndFlush(anyString());
    }

    @Test
    void testGetLayoutForUnknownSessionReturnsNullOrEmpty() {
        // 未创建的 sessionId，应返回 null 或 empty
        String unknown = "no_such_session";
        List<Integer> layout = manager.getLayoutForSession(unknown);
        assertNull(layout, "未知 sessionId 应返回 null");
    }

    @Test
    void testGetChannelsForUnknownSessionReturnsEmptySet() {
        Set<Channel> channels = manager.getChannels("nonexistent");
        assertNotNull(channels, "getChannels 不应返回 null");
        assertTrue(channels.isEmpty(), "未知 sessionId 应返回空 Set");
    }

    @Test
    void testGetNextPlayerIndexForUnknownSessionReturnsZero() {
        int idx = manager.getNextPlayerIndex("no_room");
        assertEquals(0, idx, "未知 sessionId nextPlayerIndex 应为 0");
    }

    @Test
    void testGetSessionProfessionsForUnknownSessionReturnsNullOrEmpty() {
        Map<Integer, Profession> profs = manager.getSessionProfessions("no_room");
        assertNull(profs, "未知 sessionId 应返回 null");
    }

    @Test
    void testGetSessionForChannelNotInMapReturnsNull() {
        Channel ch = mock(Channel.class);
        // 未 join 任何房间
        assertNull(manager.getSessionFor(ch), "不存在映射的 Channel 应返回 null");
    }
}
