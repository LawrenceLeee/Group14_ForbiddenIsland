package edu.bdic.forbiddenisland.network.handler.impl;

import edu.bdic.forbiddenisland.controller.CommandManager;
import edu.bdic.forbiddenisland.controller.commands.GameLostCommand;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.handler.MessageHandler;

/**
 * 处理服务器下发的 GAME_LOST 消息，将其转成 GameLostCommand 并执行本地逻辑
 */
public class GameLostHandler implements MessageHandler {
    @Override
    public void handle(Message message) {
        // 从消息构造命令
        GameLostCommand cmd = GameLostCommand.fromMessage(message);
        // 本地执行（更新模型、UI 会自动刷新）
        CommandManager.getInstance().executeLocal(cmd);
    }
}
