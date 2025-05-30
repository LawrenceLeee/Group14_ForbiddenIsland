package edu.bdic.forbiddenisland.network.handler.impl;

import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.handler.MessageHandler;
import edu.bdic.forbiddenisland.controller.CommandManager;

/**
 * 处理 SHORE_UP 类型消息，将其转换为 ShoreUpCommand 并执行
 */
public class ShoreUpHandler implements MessageHandler {
    @Override
    public void handle(Message message) {
        System.out.println("[Server] broadcasting SHORE_UP from " + message.getPlayerId());
        CommandManager.getInstance().handleRemoteMessage(message);
    }
}
