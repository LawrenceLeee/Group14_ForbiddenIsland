package edu.bdic.forbiddenisland.controller.commands;

import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import java.util.ArrayList;
import java.util.List;

public class EngineerShoreUpCommand implements GameCommand {
    private final int playerIndex;
    private final List<Integer> tileIndices;

    public EngineerShoreUpCommand(int playerIndex, List<Integer> tileIndices) {
        this.playerIndex = playerIndex;
        this.tileIndices = tileIndices;
    }

    @Override
    public void execute() {
        System.out.printf("[DEBUG][EngineerShoreUpCommand] player=%d tiles=%s%n", playerIndex, tileIndices);
        GameModel.getInstance().shoreUpTiles(playerIndex, tileIndices);
    }

    @Override
    public Message toMessage() {
        ArrayNode tilesArr = JsonNodeFactory.instance.arrayNode();
        for (Integer tile : tileIndices) {
            tilesArr.add(tile);
        }
        return new Message(
                MessageType.ENGINEER_SHOREUP,
                SessionManager.getInstance().getSessionId(),
                SessionManager.getInstance().getPlayerId(),
                JsonNodeFactory.instance.objectNode()
                        .put("playerIndex", playerIndex)
                        .set("tileIndices", tilesArr)
        );
    }

    public static EngineerShoreUpCommand fromMessage(Message msg) {
        int playerIndex = msg.getPayload().get("playerIndex").asInt();
        ArrayNode arr = (ArrayNode) msg.getPayload().get("tileIndices");
        List<Integer> tileIndices = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            tileIndices.add(arr.get(i).asInt());
        }
        return new EngineerShoreUpCommand(playerIndex, tileIndices);
    }
}
