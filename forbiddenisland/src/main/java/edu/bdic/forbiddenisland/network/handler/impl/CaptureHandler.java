// src/main/java/edu/bdic/forbiddenisland/network/handler/impl/CaptureHandler.java
package edu.bdic.forbiddenisland.network.handler.impl;

import edu.bdic.forbiddenisland.controller.CommandManager;
import edu.bdic.forbiddenisland.controller.commands.CaptureCommand;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.handler.MessageHandler;

public class CaptureHandler implements MessageHandler {
    @Override
    public void handle(Message message) {
        // 收到 CAPTURE 消息，反序列化并在本地执行
        CaptureCommand cmd = CaptureCommand.fromMessage(message);
        CommandManager.getInstance().executeLocal(cmd);
    }
}
