// src/main/java/edu/bdic/forbiddenisland/network/handler/impl/GiveHandler.java
package edu.bdic.forbiddenisland.network.handler.impl;

import edu.bdic.forbiddenisland.controller.CommandManager;
import edu.bdic.forbiddenisland.controller.commands.GiveCommand;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.handler.MessageHandler;

public class GiveHandler implements MessageHandler {
    @Override
    public void handle(Message message) {
        GiveCommand cmd = GiveCommand.fromMessage(message);
        CommandManager.getInstance().executeLocal(cmd);
    }
}
