// src/main/java/edu/bdic/forbiddenisland/controller/commands/GameLostCommand.java
package edu.bdic.forbiddenisland.controller.commands;

import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.bdic.forbiddenisland.util.GameDialogUtil;
import edu.bdic.forbiddenisland.view.GameController;

/**
 * 游戏失败命令
 */
public class GameLostCommand implements GameCommand {
    @Override
    public void execute() {
        // TODO: 在UI层面显示失败弹窗或跳转
        System.out.println("Game Lost! 显示失败界面");
    }

    @Override
    public Message toMessage() {
        return new Message(
                MessageType.GAME_LOST,
                SessionManager.getInstance().getSessionId(),
                SessionManager.getInstance().getPlayerId(),
                JsonNodeFactory.instance.objectNode()
        );
    }

    public static GameLostCommand fromMessage(Message msg) {
        return new GameLostCommand();
    }
}
