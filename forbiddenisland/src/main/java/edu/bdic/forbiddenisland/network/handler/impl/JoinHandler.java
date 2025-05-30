package edu.bdic.forbiddenisland.network.handler.impl;

import edu.bdic.forbiddenisland.controller.CommandManager;
import edu.bdic.forbiddenisland.controller.commands.JoinCommand;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.handler.MessageHandler;
import edu.bdic.forbiddenisland.view.GameController;

/**
 * 服务器下发 JOIN 消息，反序列化成 JoinGameCommand
 */
public class JoinHandler implements MessageHandler {
    @Override
    public void handle(Message message) {
        // 把消息转成命令，执行本地逻辑（addPlayer）并不再需要 SessionManager 操作
        JoinCommand cmd = JoinCommand.fromMessage(message);
        CommandManager.getInstance().executeLocal(cmd);
    }
}