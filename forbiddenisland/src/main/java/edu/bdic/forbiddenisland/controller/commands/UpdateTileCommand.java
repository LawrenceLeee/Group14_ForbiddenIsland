package edu.bdic.forbiddenisland.controller.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;

public class UpdateTileCommand implements GameCommand {
    private final int tileIndex;
    private final String status; // DRY/FLOODED/SUNK

    public UpdateTileCommand(int tileIndex, String status) {
        this.tileIndex = tileIndex;
        this.status    = status;
    }

    @Override
    public void execute() {
        // 正确：通过 tileIndex + status 调用 model
        GameModel.getInstance().updateTileStatus(tileIndex, status);
    }

    @Override
    public Message toMessage() {
        ObjectNode p = JsonNodeFactory.instance.objectNode();
        p.put("tile", tileIndex);
        p.put("status", status);
        return new Message(
                MessageType.UPDATE_TILE,
                SessionManager.getInstance().getSessionId(),
                SessionManager.getInstance().getPlayerId(),
                p
        );
    }

    public static UpdateTileCommand fromMessage(Message msg) {
        int idx    = msg.getPayload().get("tile").asInt();
        String sts = msg.getPayload().get("status").asText();
        return new UpdateTileCommand(idx, sts);
    }

}
