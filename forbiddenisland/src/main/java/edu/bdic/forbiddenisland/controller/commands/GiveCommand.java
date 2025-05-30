package edu.bdic.forbiddenisland.controller.commands;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.model.TreasureCardType;
import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;

public class GiveCommand implements GameCommand {
    private final int fromPlayer;
    private final int toPlayer;
    private final TreasureCardType card;

    public GiveCommand(int fromPlayer, int toPlayer, TreasureCardType card) {
        this.fromPlayer = fromPlayer;
        this.toPlayer   = toPlayer;
        this.card       = card;
    }

    @Override
    public void execute() {
        GameModel.getInstance().giveCard(fromPlayer, toPlayer, card);
    }

    @Override
    public Message toMessage() {
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("from", fromPlayer);
        payload.put("to",   toPlayer);
        payload.put("card", card.name());
        return new Message(
                MessageType.GIVE,
                SessionManager.getInstance().getSessionId(),
                SessionManager.getInstance().getPlayerId(),
                payload
        );
    }

    public static GiveCommand fromMessage(Message msg) {
        int from = msg.getPayload().get("from").asInt();
        int to   = msg.getPayload().get("to").asInt();
        TreasureCardType card = TreasureCardType.valueOf(
                msg.getPayload().get("card").asText()
        );
        return new GiveCommand(from, to, card);
    }
}
