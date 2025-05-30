package edu.bdic.forbiddenisland.controller.commands;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;

public class MoveCommand implements GameCommand {
    private final int playerIndex;
    private final int toTileIndex;
    private final boolean skipAP;

    public MoveCommand(int playerIndex, int toTileIndex) {
        this(playerIndex, toTileIndex, false);
    }

    public MoveCommand(int playerIndex, int toTileIndex, boolean skipAP) {
        this.playerIndex = playerIndex;
        this.toTileIndex = toTileIndex;
        this.skipAP      = skipAP;
    }

    @Override
    public void execute() {
        int me = this.playerIndex;
        int toTile = this.toTileIndex;
        System.out.println("[DEBUG][MoveCommand] execute() player=" + me + " → moving to tile=" + toTile);
        GameModel.getInstance().movePlayer(me, toTile);
    }

    @Override
    public Message toMessage() {
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("to", toTileIndex);
        return new Message(
                MessageType.MOVE,
                SessionManager.getInstance().getSessionId(),
                SessionManager.getInstance().getPlayerId(),
                payload
        );
    }

    /**
     * 从服务器消息反序列化出命令
     */
    public static MoveCommand fromMessage(Message msg) {
        int pid = msg.getPlayerId();
        int to  = msg.getPayload().get("to").asInt();
        // 这里始终用默认构造，不跳过行动点
        return new MoveCommand(pid, to);
    }
}
