package edu.bdic.forbiddenisland.network.handler.impl;

import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.handler.MessageHandler;
import javafx.application.Platform;

public class NextTurnHandler implements MessageHandler {
    @Override
    public void handle(Message message) {
        // 远端发来的下一回合消息，也切换到下一个玩家
        System.out.println("[LOG][NextTurnHandler] 收到 SERVER NEXT_TURN 消息，准备执行 endTurn()");
        Platform.runLater(() -> GameModel.getInstance().endTurn());
    }
}
