// src/main/java/edu/bdic/forbiddenisland/controller/commands/ShoreUpCommand.java
package edu.bdic.forbiddenisland.controller.commands;

import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.network.JsonUtil;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

/**
 * 玩家加固命令
 */
public class ShoreUpCommand implements GameCommand {
    private final int tileIndex;

    public ShoreUpCommand(int tileIndex) {
        this.tileIndex = tileIndex;
    }

    @Override
    public void execute() {
        // 调用模型的加固方法
        GameModel.getInstance().shoreUpTile(tileIndex);
    }

    @Override
    public Message toMessage() {
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("tile", tileIndex);
        return new Message(
                MessageType.SHORE_UP,
                SessionManager.getInstance().getSessionId(),
                SessionManager.getInstance().getPlayerId(),
                payload
        );
    }

    public static ShoreUpCommand fromMessage(Message msg) {
        int tile = msg.getPayload().get("tile").asInt();
        return new ShoreUpCommand(tile);
    }
}
