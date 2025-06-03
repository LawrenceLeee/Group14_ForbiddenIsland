package edu.bdic.forbiddenisland.controller.commands;

import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.model.TreasureCardType;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

/**
 * 弃牌命令
 */
public class DiscardCommand implements GameCommand {
    private final int playerIndex;
    private final TreasureCardType card;

    public DiscardCommand(int playerIndex, TreasureCardType card) {
        this.playerIndex = playerIndex;
        this.card = card;
    }

    @Override
    public void execute() {
        System.out.printf("[LOG][DiscardCommand] execute(): player=%d 准备弃 %s%n", playerIndex, card);
        GameModel.getInstance().discardTreasure(playerIndex, card);
    }


    @Override
    public Message toMessage() {
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("playerIndex", playerIndex);
        payload.put("card", card.name());
        return new Message(
                MessageType.DISCARD,
                SessionManager.getInstance().getSessionId(),
                SessionManager.getInstance().getPlayerId(),
                payload
        );
    }

    public static DiscardCommand fromMessage(Message msg) {
        int pid = msg.getPayload().get("playerIndex").asInt();
        TreasureCardType card = TreasureCardType.valueOf(msg.getPayload().get("card").asText());
        return new DiscardCommand(pid, card);
    }
}
