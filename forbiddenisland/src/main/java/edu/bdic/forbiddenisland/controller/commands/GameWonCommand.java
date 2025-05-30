// src/main/java/edu/bdic/forbiddenisland/controller/commands/GameWonCommand.java
package edu.bdic.forbiddenisland.controller.commands;

import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 游戏胜利命令
 */
public class GameWonCommand implements GameCommand {
    @Override
    public void execute() {
        // TODO: 在UI层面显示胜利弹窗或跳转
        System.out.println("Game Won! 显示胜利界面");
    }

    @Override
    public Message toMessage() {
        // 空 payload
        return new Message(
                MessageType.GAME_WON,
                SessionManager.getInstance().getSessionId(),
                SessionManager.getInstance().getPlayerId(),
                JsonNodeFactory.instance.objectNode()
        );
    }

    public static GameWonCommand fromMessage(Message msg) {
        return new GameWonCommand();
    }
}
