package edu.bdic.forbiddenisland.server;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.model.GameModel.GameState;
import edu.bdic.forbiddenisland.model.GameModel.TileState;
import edu.bdic.forbiddenisland.model.Profession;
import edu.bdic.forbiddenisland.network.JsonUtil;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ServerHandlerTest {

    @Test
    void testHandleCreateRoom() throws Exception {
        // 1. 构造 CREATE_ROOM 消息 JSON
        Message createMsg = new Message(MessageType.CREATE_ROOM, "", 0, null);
        String json = JsonUtil.toJson(createMsg) + "\n";

        // 2. 准备 mockChannel & mockCtx
        Channel mockChannel = mock(Channel.class);
        ChannelHandlerContext mockCtx = mock(ChannelHandlerContext.class);
        when(mockCtx.channel()).thenReturn(mockChannel);

        // 3. 准备 mockRoomMgr 的行为
        GameRoomManager mockRoomMgr = mock(GameRoomManager.class);
        String sessionId = "session123";
        List<Integer> layout = new ArrayList<>();
        for (int i = 0; i < 24; i++) layout.add(i);
        Profession prof0 = Profession.EXPLORER;

        when(mockRoomMgr.createRoom()).thenReturn(sessionId);
        when(mockRoomMgr.getLayoutForSession(eq(sessionId))).thenReturn(layout);
        when(mockRoomMgr.assignNextProfession(eq(sessionId))).thenReturn(prof0);

        // 4. 在静态存根上下文里 new 出 handler
        try (MockedStatic<GameRoomManager> rmStatic = mockStatic(GameRoomManager.class)) {
            rmStatic.when(GameRoomManager::getInstance).thenReturn(mockRoomMgr);

            ServerHandler handler = new ServerHandler();

            // 5. 调用 channelRead0
            handler.channelRead0(mockCtx, json);

            // 6. 验证 GameRoomManager.createRoom、join、getLayoutForSession、assignNextProfession、recordProfession 调用
            verify(mockRoomMgr, times(1)).createRoom();
            verify(mockRoomMgr, times(1)).join(eq(sessionId), eq(mockChannel));
            verify(mockRoomMgr, times(1)).getLayoutForSession(eq(sessionId));
            verify(mockRoomMgr, times(1)).assignNextProfession(eq(sessionId));
            verify(mockRoomMgr, times(1)).recordProfession(eq(sessionId), eq(0), eq(prof0));

            // 7. 验证向客户端 writeAndFlush 了 ROOM_CREATED
            ObjectNode expectedPayload = JsonNodeFactory.instance.objectNode();
            expectedPayload.put("sessionId", sessionId);
            var arrNode = expectedPayload.putArray("layout");
            layout.forEach(arrNode::add);
            var playersArr = expectedPayload.putArray("players");
            var p0 = playersArr.addObject();
            p0.put("playerIndex", 0);
            p0.put("profession", prof0.name());

            Message expectedReply = new Message(
                    MessageType.ROOM_CREATED,
                    sessionId,
                    0,
                    expectedPayload
            );
            String expectedJson = JsonUtil.toJson(expectedReply) + "\n";
            verify(mockChannel, times(1)).writeAndFlush(eq(expectedJson));
        }
    }

    @Test
    void testHandleJoin() throws Exception {
        // 1. 构造 JOIN 消息 JSON
        String sessionId = "sessJoin";
        ObjectNode payloadJoin = JsonNodeFactory.instance.objectNode();
        payloadJoin.put("playerIndex", 1);
        Message joinMsg = new Message(MessageType.JOIN, sessionId, 1, payloadJoin);
        String json = JsonUtil.toJson(joinMsg) + "\n";

        // 2. 准备 mockChannel & mockCtx
        Channel mockChannel = mock(Channel.class);
        ChannelHandlerContext mockCtx = mock(ChannelHandlerContext.class);
        when(mockCtx.channel()).thenReturn(mockChannel);

        // 3. 准备 mockRoomMgr
        GameRoomManager mockRoomMgr = mock(GameRoomManager.class);
        when(mockRoomMgr.getNextPlayerIndex(eq(sessionId))).thenReturn(2);
        Profession prof = Profession.DIVER;
        when(mockRoomMgr.assignNextProfession(eq(sessionId))).thenReturn(prof);
        List<Integer> layout = new ArrayList<>();
        for (int i = 0; i < 24; i++) layout.add(i);
        when(mockRoomMgr.getLayoutForSession(eq(sessionId))).thenReturn(layout);
        Map<Integer, Profession> profMap = new HashMap<>();
        profMap.put(0, Profession.EXPLORER);
        profMap.put(1, prof);
        when(mockRoomMgr.getSessionProfessions(eq(sessionId))).thenReturn(profMap);

        // 4. 静态存根后再 new handler
        try (MockedStatic<GameRoomManager> rmStatic = mockStatic(GameRoomManager.class)) {
            rmStatic.when(GameRoomManager::getInstance).thenReturn(mockRoomMgr);

            ServerHandler handler = new ServerHandler();
            handler.channelRead0(mockCtx, json);

            verify(mockRoomMgr, times(1)).getNextPlayerIndex(eq(sessionId));
            verify(mockRoomMgr, times(1)).join(eq(sessionId), eq(mockChannel));
            verify(mockRoomMgr, times(1)).assignNextProfession(eq(sessionId));
            verify(mockRoomMgr, times(1)).recordProfession(eq(sessionId), eq(2), eq(prof));

            ObjectNode expectedPayload = JsonNodeFactory.instance.objectNode();
            expectedPayload.put("sessionId", sessionId);
            var arrNode = expectedPayload.putArray("layout");
            layout.forEach(arrNode::add);
            var playersArr = expectedPayload.putArray("players");
            profMap.forEach((idx, p) -> {
                var o = playersArr.addObject();
                o.put("playerIndex", idx);
                o.put("profession", p.name());
            });
            Message expectedReply = new Message(
                    MessageType.ROOM_JOINED,
                    sessionId,
                    2,
                    expectedPayload
            );
            String expectedJson = JsonUtil.toJson(expectedReply) + "\n";
            verify(mockRoomMgr, times(1)).broadcastToRoom(eq(sessionId), eq(expectedJson));
        }
    }

    @Test
    void testHandleStartGame() throws Exception {
        // 1. 构造 START 消息 JSON
        String sessionId = "sessStart";
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("dummy", 0);
        Message startMsg = new Message(MessageType.START, sessionId, 5, payload);
        String json = JsonUtil.toJson(startMsg) + "\n";

        // 2. 准备 mockChannel & mockCtx
        Channel mockChannel = mock(Channel.class);
        ChannelHandlerContext mockCtx = mock(ChannelHandlerContext.class);
        when(mockCtx.channel()).thenReturn(mockChannel);

        // 3. 准备 mockRoomMgr 和 mockModel
        GameRoomManager mockRoomMgr = mock(GameRoomManager.class);
        GameModel mockModel = mock(GameModel.class);
        when(mockRoomMgr.getSessionFor(eq(mockChannel))).thenReturn(sessionId);

        // 4. 静态存根后再 new handler
        try (MockedStatic<GameRoomManager> rmStatic = mockStatic(GameRoomManager.class);
             MockedStatic<GameModel>      gmStatic = mockStatic(GameModel.class)) {

            rmStatic.when(GameRoomManager::getInstance).thenReturn(mockRoomMgr);
            gmStatic.when(GameModel::getInstance).thenReturn(mockModel);

            ServerHandler handler = new ServerHandler();
            handler.channelRead0(mockCtx, json);

            verify(mockModel, times(1)).initializeGame(anyLong());
            verify(mockRoomMgr, times(1)).broadcastToRoom(
                    eq(sessionId),
                    argThat(arg ->
                            arg.contains("\"type\":\"START\"") &&
                                    arg.contains("\"sessionId\":\"" + sessionId + "\"")
                    )
            );
        }
    }

    @Test
    void testHandleMoveBroadcastsToOtherChannels() throws Exception {
        // 1. 构造 MOVE 消息 JSON
        String sessionId = "sessMove";
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("to", 4);
        Message moveMsg = new Message(MessageType.MOVE, sessionId, 3, payload);
        String json = JsonUtil.toJson(moveMsg) + "\n";

        // 2. 准备 mockChannel & mockCtx
        Channel ch1 = mock(Channel.class);
        Channel ch2 = mock(Channel.class);
        ChannelHandlerContext mockCtx = mock(ChannelHandlerContext.class);
        when(mockCtx.channel()).thenReturn(ch1);

        // 3. 准备 mockRoomMgr
        GameRoomManager mockRoomMgr = mock(GameRoomManager.class);
        Set<Channel> channelSet = new HashSet<>(Arrays.asList(ch1, ch2));
        when(mockRoomMgr.getChannels(eq(sessionId))).thenReturn(channelSet);

        // 4. 静态存根后再 new handler
        try (MockedStatic<GameRoomManager> rmStatic = mockStatic(GameRoomManager.class)) {
            rmStatic.when(GameRoomManager::getInstance).thenReturn(mockRoomMgr);

            ServerHandler handler = new ServerHandler();
            handler.channelRead0(mockCtx, json);

            verify(ch2, times(1)).writeAndFlush(eq(json));
            verify(ch1, never()).writeAndFlush(anyString());
        }
    }

    @Test
    void testHandleShoreUpBroadcastsToOtherChannels() throws Exception {
        // 1. 构造 SHORE_UP 消息 JSON
        String sessionId = "sessShore";
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("tile", 8);
        Message shoreMsg = new Message(MessageType.SHORE_UP, sessionId, 2, payload);
        String json = JsonUtil.toJson(shoreMsg) + "\n";

        // 2. 准备 mockChannel & mockCtx
        Channel ch1 = mock(Channel.class);
        Channel ch2 = mock(Channel.class);
        ChannelHandlerContext mockCtx = mock(ChannelHandlerContext.class);
        when(mockCtx.channel()).thenReturn(ch1);

        // 3. 准备 mockRoomMgr
        GameRoomManager mockRoomMgr = mock(GameRoomManager.class);
        Set<Channel> channelSet = new HashSet<>(Arrays.asList(ch1, ch2));
        when(mockRoomMgr.getChannels(eq(sessionId))).thenReturn(channelSet);

        // 4. 静态存根后再 new handler
        try (MockedStatic<GameRoomManager> rmStatic = mockStatic(GameRoomManager.class)) {
            rmStatic.when(GameRoomManager::getInstance).thenReturn(mockRoomMgr);

            ServerHandler handler = new ServerHandler();
            handler.channelRead0(mockCtx, json);

            verify(ch2, times(1)).writeAndFlush(eq(json));
            verify(ch1, never()).writeAndFlush(anyString());
        }
    }

    @Test
    void testHandleNextTurnOngoingBroadcastsUpdatesOnly() throws Exception {
        // 1. 构造 NEXT_TURN 消息 JSON
        String sessionId = "sessNext";
        Message nextMsg = new Message(MessageType.NEXT_TURN, sessionId, 1, null);
        String jsonNext = JsonUtil.toJson(nextMsg) + "\n";

        // 2. 准备 mockChannel & mockCtx
        Channel mockChannel = mock(Channel.class);
        ChannelHandlerContext mockCtx = mock(ChannelHandlerContext.class);
        when(mockCtx.channel()).thenReturn(mockChannel);

        // 3. 准备 mockRoomMgr 和 mockModel
        GameRoomManager mockRoomMgr = mock(GameRoomManager.class);
        GameModel mockModel = mock(GameModel.class);
        when(mockRoomMgr.getChannels(eq(sessionId))).thenReturn(Collections.singleton(mockChannel));
        when(mockModel.getTileState(anyInt())).thenReturn(TileState.DRY);
        when(mockModel.evaluateGameState()).thenReturn(GameState.ONGOING);

        // 4. 静态存根后再 new handler
        try (MockedStatic<GameRoomManager> rmStatic = mockStatic(GameRoomManager.class);
             MockedStatic<GameModel>      gmStatic = mockStatic(GameModel.class)) {

            rmStatic.when(GameRoomManager::getInstance).thenReturn(mockRoomMgr);
            gmStatic.when(GameModel::getInstance).thenReturn(mockModel);

            ServerHandler handler = new ServerHandler();
            handler.channelRead0(mockCtx, jsonNext);

            verify(mockRoomMgr, times(1)).broadcastToRoom(eq(sessionId), eq(jsonNext));
            verify(mockModel, times(1)).endTurn();
            // 只校验“共调用 24 次 UPDATE_TILE_STATUS 广播”，不检查 tileIndex
            verify(mockRoomMgr, times(24)).broadcastToRoom(
                    eq(sessionId),
                    argThat(arg -> arg.contains("\"type\":\"UPDATE_TILE_STATUS\""))
            );
            verify(mockRoomMgr, never()).broadcastToRoom(
                    eq(sessionId),
                    argThat(arg -> arg.contains("\"type\":\"GAME_WON\"") || arg.contains("\"type\":\"GAME_LOST\""))
            );
        }
    }

    @Test
    void testHandleNextTurnGameWonBroadcastsEndMessage() throws Exception {
        // 1. 构造 NEXT_TURN 消息 JSON
        String sessionId = "sessNext2";
        Message nextMsg = new Message(MessageType.NEXT_TURN, sessionId, 1, null);
        String jsonNext = JsonUtil.toJson(nextMsg) + "\n";

        // 2. 准备 mockChannel & mockCtx
        Channel mockChannel = mock(Channel.class);
        ChannelHandlerContext mockCtx = mock(ChannelHandlerContext.class);
        when(mockCtx.channel()).thenReturn(mockChannel);

        // 3. 准备 mockRoomMgr 和 mockModel
        GameRoomManager mockRoomMgr = mock(GameRoomManager.class);
        GameModel mockModel = mock(GameModel.class);
        when(mockRoomMgr.getChannels(eq(sessionId))).thenReturn(Collections.singleton(mockChannel));
        when(mockModel.getTileState(anyInt())).thenReturn(TileState.DRY);
        when(mockModel.evaluateGameState()).thenReturn(GameState.WON);

        // 4. 静态存根后再 new handler
        try (MockedStatic<GameRoomManager> rmStatic = mockStatic(GameRoomManager.class);
             MockedStatic<GameModel>      gmStatic = mockStatic(GameModel.class)) {

            rmStatic.when(GameRoomManager::getInstance).thenReturn(mockRoomMgr);
            gmStatic.when(GameModel::getInstance).thenReturn(mockModel);

            ServerHandler handler = new ServerHandler();
            handler.channelRead0(mockCtx, jsonNext);

            verify(mockRoomMgr, times(1)).broadcastToRoom(eq(sessionId), eq(jsonNext));
            verify(mockModel, times(1)).endTurn();
            // 只校验“共调用 24 次 UPDATE_TILE_STATUS 广播”，不检查 tileIndex
            verify(mockRoomMgr, times(24)).broadcastToRoom(
                    eq(sessionId),
                    argThat(arg -> arg.contains("\"type\":\"UPDATE_TILE_STATUS\""))
            );
            verify(mockRoomMgr, times(1)).broadcastToRoom(
                    eq(sessionId),
                    argThat(arg -> arg.contains("\"type\":\"GAME_WON\""))
            );
            verify(mockRoomMgr, never()).broadcastToRoom(
                    eq(sessionId),
                    argThat(arg -> arg.contains("\"type\":\"GAME_LOST\""))
            );
        }
    }
}
