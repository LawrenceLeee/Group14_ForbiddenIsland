package edu.bdic.forbiddenisland.controller.commands;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;

public class NextTurnCommand implements GameCommand {
    public NextTurnCommand() { }

    @Override
    public void execute() {
        // 切换到下一玩家，并发两张洪水牌
        GameModel.getInstance().endTurn();
    }

    @Override
    public Message toMessage() {
        // 发送一个没有额外 payload 的 NEXT_TURN
        return new Message(
                MessageType.NEXT_TURN,
                SessionManager.getInstance().getSessionId(),
                SessionManager.getInstance().getPlayerId(),
                JsonNodeFactory.instance.objectNode()
        );
    }

    public static NextTurnCommand fromMessage(Message msg) {
        // 无需从 payload 中解析任何额外字段
        return new NextTurnCommand();
    }
}
