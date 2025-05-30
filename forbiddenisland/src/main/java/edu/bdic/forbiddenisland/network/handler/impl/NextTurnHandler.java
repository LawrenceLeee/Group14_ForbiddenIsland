package edu.bdic.forbiddenisland.network.handler.impl;

import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.handler.MessageHandler;

public class NextTurnHandler implements MessageHandler {
    @Override
    public void handle(Message message) {
        // 远端发来的下一回合消息，也切换到下一个玩家
        GameModel.getInstance().endTurn();
    }
}
