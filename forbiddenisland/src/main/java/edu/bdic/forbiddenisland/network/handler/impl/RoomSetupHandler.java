package edu.bdic.forbiddenisland.network.handler.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.controller.RoomEventManager;
import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.model.Profession;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import edu.bdic.forbiddenisland.network.handler.MessageHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理服务器下发的 ROOM_CREATED 与 ROOM_JOINED，
 * 初始化岛屿布局 & 全部玩家（含职业），并通知 UI 进入游戏。
 */
public class RoomSetupHandler implements MessageHandler {
    @Override
    public void handle(Message message) {
        String sid = message.getSessionId();
        JsonNode payload = message.getPayload();

        // —— 1) 解析并设置岛屿布局 ——
        JsonNode layoutNode = payload.get("layout");
        if (layoutNode == null || !layoutNode.isArray()) {
            throw new IllegalStateException(
                    "SERVER: missing 'layout' in " + message.getType());
        }
        List<Integer> layout = new ArrayList<>();
        layoutNode.forEach(n -> layout.add(n.asInt()));
        GameModel.getInstance().setIslandLayout(layout);

        // —— 2) 解析并注册所有玩家 ——
        JsonNode playersNode = payload.get("players");
        if (playersNode == null || !playersNode.isArray()) {
            throw new IllegalStateException(
                    "SERVER: missing 'players' in " + message.getType());
        }
        for (JsonNode p : playersNode) {
            int idx = p.get("playerIndex").asInt();
            Profession prof = Profession.valueOf(p.get("profession").asText());
            GameModel.getInstance().addPlayer(idx, prof);
        }

        // —— 3) 更新 SessionManager ——
        //    直接用 message.getPlayerId()，不要再 payload.get("playerIndex")
        boolean isHost = message.getType() == MessageType.ROOM_CREATED;
        int myIdx    = message.getPlayerId();
        // 只在第一次设置时调用，避免后续其他人加入重写
        if (SessionManager.getInstance().getPlayerId() < 0) {
            SessionManager.getInstance().setSession(sid, myIdx, isHost);
        }

        // —— 4) 通知 UI 切换到游戏界面 ——
        //    只有当本地 sessionId 和 playerId 设置完毕后才触发
        if (SessionManager.getInstance().getSessionId().equals(sid)
                && SessionManager.getInstance().getPlayerId() == myIdx) {
            RoomEventManager.getInstance().fireRoomJoined(sid, myIdx);
        }
    }
}