package edu.bdic.forbiddenisland.network.handler;

import edu.bdic.forbiddenisland.network.Message;

public interface MessageHandler {
    void handle(Message message);
}