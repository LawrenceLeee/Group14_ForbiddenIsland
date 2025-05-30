package edu.bdic.forbiddenisland.network.handler.impl;

import edu.bdic.forbiddenisland.controller.commands.StartGameCommand;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.handler.MessageHandler;
import javafx.application.Platform;

public class StartGameHandler implements MessageHandler {
    @Override
    public void handle(Message message) {
        // 1) 从消息反序列化出命令
        StartGameCommand cmd = StartGameCommand.fromMessage(message);
        // 2) 在 FX 线程上执行本地初始化
        Platform.runLater(cmd::execute);
    }
}
