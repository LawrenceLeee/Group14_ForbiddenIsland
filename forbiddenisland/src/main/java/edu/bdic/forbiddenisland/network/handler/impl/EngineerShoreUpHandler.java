package edu.bdic.forbiddenisland.network.handler.impl;

import edu.bdic.forbiddenisland.controller.CommandManager;
import edu.bdic.forbiddenisland.controller.commands.EngineerShoreUpCommand;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.handler.MessageHandler;

public class EngineerShoreUpHandler implements MessageHandler {
    @Override
    public void handle(Message message) {
        EngineerShoreUpCommand cmd = EngineerShoreUpCommand.fromMessage(message);
        CommandManager.getInstance().executeLocal(cmd);
    }
}
