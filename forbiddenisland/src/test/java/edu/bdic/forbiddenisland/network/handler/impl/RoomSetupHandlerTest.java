package edu.bdic.forbiddenisland.network.handler.impl;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.controller.RoomEventManager;
import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.model.Profession;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class RoomSetupHandlerTest {

    @Test
    void testHandleRoomCreatedInitializesModelAndSessionAndFiresEvent() {
        // — 构造 payload —
        ObjectNode payload = JsonNodeFactory.instance.objectNode();

        // 1) layout: 24 ints
        ArrayNode layoutArray = JsonNodeFactory.instance.arrayNode();
        for (int i = 0; i < 24; i++) layoutArray.add(i);
        payload.set("layout", layoutArray);

        // 2) players: 两个玩家对象
        ArrayNode playersArray = JsonNodeFactory.instance.arrayNode();
        ObjectNode p1 = JsonNodeFactory.instance.objectNode();
        p1.put("playerIndex", 0);
        p1.put("profession", Profession.EXPLORER.name());
        playersArray.add(p1);
        ObjectNode p2 = JsonNodeFactory.instance.objectNode();
        p2.put("playerIndex", 1);
        p2.put("profession", Profession.DIVER.name());
        playersArray.add(p2);
        payload.set("players", playersArray);

        // — 构造 Message (类型 ROOM_CREATED, sessionId "SID", playerId=0) —
        Message msg = new Message(
                MessageType.ROOM_CREATED,
                "SID",
                0,
                payload
        );

        // Mock 静态方法
        GameModel mockModel = mock(GameModel.class);
        try (MockedStatic<GameModel> gmStatic = mockStatic(GameModel.class);
             MockedStatic<SessionManager> smStatic = mockStatic(SessionManager.class);
             MockedStatic<RoomEventManager> remStatic = mockStatic(RoomEventManager.class)) {

            gmStatic.when(GameModel::getInstance).thenReturn(mockModel);

            // SessionManager.getInstance().getPlayerId() 首次返回 <0，再返回 0
            SessionManager mockSession = mock(SessionManager.class);
            smStatic.when(SessionManager::getInstance).thenReturn(mockSession);
            when(mockSession.getPlayerId()).thenReturn(-1, 0);
            when(mockSession.getSessionId()).thenReturn("SID");
            doNothing().when(mockSession).setSession(eq("SID"), eq(0), eq(true));

            RoomEventManager mockREM = mock(RoomEventManager.class);
            remStatic.when(RoomEventManager::getInstance).thenReturn(mockREM);

            // 调用 handler
            RoomSetupHandler handler = new RoomSetupHandler();
            handler.handle(msg);

            // 验证 GameModel.setIslandLayout 用正确列表调用一次
            verify(mockModel, times(1)).setIslandLayout(argThat(list ->
                    list.size() == 24 && list.get(0) == 0 && list.get(23) == 23
            ));

            // 验证 addPlayer 分别被调用两次
            verify(mockModel, times(1)).addPlayer(eq(0), eq(Profession.EXPLORER));
            verify(mockModel, times(1)).addPlayer(eq(1), eq(Profession.DIVER));

            // 验证 SessionManager.setSession 只在首次调用一次
            verify(mockSession, times(1)).setSession(eq("SID"), eq(0), eq(true));

            // 验证 RoomEventManager.fireRoomJoined 被调用一次，参数 ("SID", 0)
            verify(mockREM, times(1)).fireRoomJoined(eq("SID"), eq(0));
        }
    }

    @Test
    void testHandleRoomJoinedWhenSessionAlreadySetOnlyFiresEvent() {
        // 构造 payload 同上一测试
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        ArrayNode layoutArray = JsonNodeFactory.instance.arrayNode();
        for (int i = 0; i < 24; i++) layoutArray.add(i);
        payload.set("layout", layoutArray);
        ArrayNode playersArray = JsonNodeFactory.instance.arrayNode();
        ObjectNode p1 = JsonNodeFactory.instance.objectNode();
        p1.put("playerIndex", 0);
        p1.put("profession", Profession.EXPLORER.name());
        playersArray.add(p1);
        payload.set("players", playersArray);

        Message msg = new Message(
                MessageType.ROOM_JOINED,
                "SID2",
                1,
                payload
        );

        GameModel mockModel = mock(GameModel.class);
        try (MockedStatic<GameModel> gmStatic = mockStatic(GameModel.class);
             MockedStatic<SessionManager> smStatic = mockStatic(SessionManager.class);
             MockedStatic<RoomEventManager> remStatic = mockStatic(RoomEventManager.class)) {

            gmStatic.when(GameModel::getInstance).thenReturn(mockModel);

            SessionManager mockSession = mock(SessionManager.class);
            smStatic.when(SessionManager::getInstance).thenReturn(mockSession);
            // getPlayerId 已设置为 >=0
            when(mockSession.getPlayerId()).thenReturn(1);
            when(mockSession.getSessionId()).thenReturn("SID2");

            RoomEventManager mockREM = mock(RoomEventManager.class);
            remStatic.when(RoomEventManager::getInstance).thenReturn(mockREM);

            RoomSetupHandler handler = new RoomSetupHandler();
            handler.handle(msg);

            // 验证不调用 setSession
            verify(mockSession, never()).setSession(anyString(), anyInt(), anyBoolean());

            // 依然会 fireRoomJoined
            verify(mockREM, times(1)).fireRoomJoined(eq("SID2"), eq(1));
        }
    }
}
