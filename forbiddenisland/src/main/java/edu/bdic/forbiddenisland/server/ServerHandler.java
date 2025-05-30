package edu.bdic.forbiddenisland.server;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.model.GameModel.TileState;
import edu.bdic.forbiddenisland.model.GameModel.GameState;
import edu.bdic.forbiddenisland.model.Profession;
import edu.bdic.forbiddenisland.network.JsonUtil;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ServerHandler extends SimpleChannelInboundHandler<String> {
    private final GameRoomManager roomManager = GameRoomManager.getInstance();
    /** 服务端用的游戏模型（含牌堆、地图状态等） */
    private final GameModel model = GameModel.getInstance();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msgJson) throws Exception {
        System.out.println("[ServerHandler] ← 收到原始 JSON: " + msgJson.trim());
        Message msg = JsonUtil.fromJson(msgJson);
        Channel ch = ctx.channel();
        System.out.printf("[ServerHandler] 类型=%s, session=%s, playerId=%d, payload=%s%n",
                msg.getType(), msg.getSessionId(), msg.getPlayerId(), msg.getPayload());

        switch (msg.getType()) {
            case CREATE_ROOM:
                handleCreateRoom(ch);
                break;
            case JOIN:
                handleJoin(ch, msg);
                break;
            case START:
                handleStartGame(ch, msg);
                break;
            case MOVE:
                handleMove(ch, msg);
                break;
            case SHORE_UP:
                handleShoreUp(ch, msg);
                break;
            case NEXT_TURN:
                handleNextTurn(ch, msg);
                break;
            default:
                // 其它消息一律原样广播
                roomManager.broadcastToRoom(msg.getSessionId(), msgJson + "\n");
                break;
        }
    }

    private void handleCreateRoom(Channel ch) {
        String sessionId = roomManager.createRoom();
        roomManager.join(sessionId, ch);

        List<Integer> layout = roomManager.getLayoutForSession(sessionId);
        Profession prof0 = roomManager.assignNextProfession(sessionId);
        roomManager.recordProfession(sessionId, 0, prof0);

        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("sessionId", sessionId);
        ArrayNode arr = payload.putArray("layout");
        layout.forEach(arr::add);
        ArrayNode playersArr = payload.putArray("players");
        ObjectNode p0 = playersArr.addObject();
        p0.put("playerIndex", 0);
        p0.put("profession", prof0.name());

        Message reply = new Message(
                MessageType.ROOM_CREATED,
                sessionId,
                0,
                payload
        );
        ch.writeAndFlush(JsonUtil.toJson(reply) + "\n");
    }

    private void handleJoin(Channel ch, Message msg) {
        String sessionId = msg.getSessionId();
        int playerIndex = roomManager.getNextPlayerIndex(sessionId);
        roomManager.join(sessionId, ch);

        Profession prof = roomManager.assignNextProfession(sessionId);
        roomManager.recordProfession(sessionId, playerIndex, prof);

        List<Integer> layout = roomManager.getLayoutForSession(sessionId);
        Map<Integer, Profession> profMap = roomManager.getSessionProfessions(sessionId);

        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("sessionId", sessionId);
        ArrayNode arr = payload.putArray("layout");
        layout.forEach(arr::add);
        ArrayNode playersArr = payload.putArray("players");
        profMap.forEach((idx, pr) -> {
            ObjectNode o = playersArr.addObject();
            o.put("playerIndex", idx);
            o.put("profession", pr.name());
        });

        Message reply = new Message(
                MessageType.ROOM_JOINED,
                sessionId,
                playerIndex,
                payload
        );
        roomManager.broadcastToRoom(sessionId, JsonUtil.toJson(reply) + "\n");
    }

    private void handleStartGame(Channel ch, Message msg) {
        String sessionId = roomManager.getSessionFor(ch);
        long seed = ThreadLocalRandom.current().nextLong();

        // 服务端模型也初始化一次
        model.initializeGame(seed);

        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("sessionId", sessionId);
        payload.put("seed", seed);

        Message start = new Message(
                MessageType.START,
                sessionId,
                -1,
                payload
        );
        roomManager.broadcastToRoom(sessionId, JsonUtil.toJson(start) + "\n");
    }

    private void handleMove(Channel ch, Message msg) {
        String sid = msg.getSessionId();
        String json = JsonUtil.toJson(msg);
        roomManager.getChannels(sid).forEach(peer -> {
            if (peer != ch) {
                peer.writeAndFlush(json + "\n");
            }
        });
    }

    private void handleShoreUp(Channel ch, Message msg) {
        String sid = msg.getSessionId();
        String json = JsonUtil.toJson(msg);
        roomManager.getChannels(sid).forEach(peer -> {
            if (peer != ch) {
                peer.writeAndFlush(json + "\n");
            }
        });
    }

    /**
     * 服务端统一处理“下一回合”逻辑：
     * 1) 广播 NEXT_TURN
     * 2) 执行模型 endTurn()
     * 3) 广播所有 UPDATE_TILE_STATUS
     * 4) 评估游戏状态并广播 GAME_WON 或 GAME_LOST（如果已结束）
     */
    private void handleNextTurn(Channel ch, Message msg) {
        String sessionId = msg.getSessionId();

        // 1) 广播回合切换
        roomManager.broadcastToRoom(sessionId, JsonUtil.toJson(msg) + "\n");

        // 2) 服务端模型执行真正的回合结束／下一回合
        model.endTurn();

        // 3) 抽洪水阶段后，把所有格子状态逐一广播
        for (int tileIndex = 0; tileIndex < 24; tileIndex++) {
            TileState st = model.getTileState(tileIndex);
            ObjectNode payload = JsonNodeFactory.instance.objectNode();
            payload.put("tileIndex", tileIndex);
            payload.put("status", st.name());

            Message update = new Message(
                    MessageType.UPDATE_TILE_STATUS,
                    sessionId,
                    -1,
                    payload
            );
            roomManager.broadcastToRoom(sessionId, JsonUtil.toJson(update) + "\n");
        }

        // 4) 评估游戏是否胜利或失败
        GameState state = model.evaluateGameState();
        if (state == GameState.WON || state == GameState.LOST) {
            ObjectNode resultPayload = JsonNodeFactory.instance.objectNode();
            resultPayload.put("result", state.name());
            Message endMsg = new Message(
                    state == GameState.WON ? MessageType.GAME_WON : MessageType.GAME_LOST,
                    sessionId,
                    -1,
                    resultPayload
            );
            roomManager.broadcastToRoom(sessionId, JsonUtil.toJson(endMsg) + "\n");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
