package edu.bdic.forbiddenisland.network.handler.impl;

import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.handler.MessageHandler;
import edu.bdic.forbiddenisland.controller.CommandManager;

/**
 * 处理 UPDATE_TILE 类型消息，将其转换为 UpdateTileCommand 并执行
 */
public class UpdateTileHandler implements MessageHandler {
    @Override
    public void handle(Message message) {
        CommandManager.getInstance().handleRemoteMessage(message);
    }
}

