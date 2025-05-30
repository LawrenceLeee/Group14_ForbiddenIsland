package edu.bdic.forbiddenisland.network.handler.impl;

import edu.bdic.forbiddenisland.controller.commands.MoveCommand;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.handler.MessageHandler;
import edu.bdic.forbiddenisland.controller.CommandManager;

/**
 * 处理 MOVE 类型消息，将其转换为 MoveCommand 并执行
 */
public class MoveHandler implements MessageHandler {
    @Override
    public void handle(Message msg) {
        CommandManager.getInstance().executeLocal(MoveCommand.fromMessage(msg));
    }
}
