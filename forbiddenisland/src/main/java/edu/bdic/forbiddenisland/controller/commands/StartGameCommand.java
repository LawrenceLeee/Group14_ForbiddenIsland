// src/main/java/edu/bdic/forbiddenisland/controller/commands/StartGameCommand.java
package edu.bdic.forbiddenisland.controller.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import java.util.concurrent.ThreadLocalRandom;

public class StartGameCommand implements GameCommand {
    private final long seed;

    /** 本地构造：由房主发起，随机一个种子 */

    /** 反序列化：服务器广播时用 */
    public StartGameCommand(long seed) {
        this.seed = seed;
    }

    @Override
    public void execute() {
        System.out.println("[StartGameCommand] ▶ execute(): seed=" + seed
                + "  Thread=" + Thread.currentThread().getName());
        GameModel.getInstance().initializeGame(seed);
    }

    @Override
    public Message toMessage() {
        ObjectNode p = JsonNodeFactory.instance.objectNode();
        p.put("seed", seed);
        return new Message(
                MessageType.START,
                SessionManager.getInstance().getSessionId(),
                SessionManager.getInstance().getPlayerId(),
                p
        );
    }

    public static StartGameCommand fromMessage(Message msg) {
        long seed = msg.getPayload().get("seed").asLong();
        return new StartGameCommand(seed);
    }
}
