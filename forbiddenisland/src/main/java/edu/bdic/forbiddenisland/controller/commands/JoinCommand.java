package edu.bdic.forbiddenisland.controller.commands;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;

/**
 * 加入房间命令：只负责向服务器发送 JOIN 请求，
 * 真正的将玩家添加到本地模型（包括职业）由 RoomSetupHandler 完成。
 */
public class JoinCommand implements GameCommand {
    private final int playerIndex;

    public JoinCommand(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    @Override
    public void execute() {
        // NO-OP：客户端不在这里更新模型，等 ROOM_JOINED 事件中由 RoomSetupHandler 统一 addPlayer(idx, prof)
    }

    @Override
    public Message toMessage() {
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("playerIndex", playerIndex);
        return new Message(
                MessageType.JOIN,
                SessionManager.getInstance().getSessionId(),
                playerIndex,
                payload
        );
    }

    public static JoinCommand fromMessage(Message msg) {
        int idx = msg.getPayload().get("playerIndex").asInt();
        return new JoinCommand(idx);
    }
}
