// src/main/java/edu/bdic/forbiddenisland/controller/CommandManager.java
package edu.bdic.forbiddenisland.controller;

import edu.bdic.forbiddenisland.controller.commands.GameCommand;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import edu.bdic.forbiddenisland.network.NetworkManager;
import javafx.application.Platform;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class CommandManager {
    private static final CommandManager INSTANCE = new CommandManager();
    private final Deque<GameCommand> history = new ArrayDeque<>();
    private final NetworkManager network = NetworkManager.getInstance();
    private final Set<String> handledKeys = new HashSet<>();

    private CommandManager() {}

    public static CommandManager getInstance() {
        return INSTANCE;
    }

    /** 在 JavaFX 线程中执行本地命令并入栈历史 */
    public void executeLocal(GameCommand cmd) {
        Platform.runLater(() -> {
            System.out.printf("[CMD][本地执行] %s%n", cmd.getClass().getSimpleName());
            cmd.execute();
        });
        history.push(cmd);
    }

    /**
     * 点击按钮 → 先本地执行，再发给服务器
     * 除了 START，所有命令本地先执行一次
     */
    public void executeAndSend(GameCommand cmd) {
        Message msg = cmd.toMessage();
        if (msg.getType() == MessageType.START) {
            // START：仅发送，不本地执行
            System.out.printf("[CMD][仅发送] %s → %s%n", msg.getType(), msg.getPayload());
            network.send(msg);
        }
        else if (msg.getType() == MessageType.NEXT_TURN) {
            // 先发送给服务器，再本地 endTurn
            System.out.printf("[CMD][仅发送] %s → %s%n", msg.getType(), msg.getPayload());
            network.send(msg);
            // 把本地 endTurn 放到 JavaFX 线程里执行，保证 UI 先刷新
            Platform.runLater(() -> {
                System.out.printf("[CMD][本地执行] %s%n", cmd.getClass().getSimpleName());
                cmd.execute();
            });
        }
        else {
            // 其他命令：保持原来的顺序
            System.out.printf("[CMD][执行并发送] %s%n", cmd.getClass().getSimpleName());
            executeLocal(cmd);
            System.out.printf("[CMD][发送] %s → %s%n", msg.getType(), msg.getPayload());
            network.send(msg);
        }
    }


    /**
     * 由 Netty 收到服务器广播后调用，将广播消息转为本地命令执行
     */
    public void handleRemoteMessage(Message msg) {
        String uniqueKey = msg.getType()
                + "-" + msg.getPlayerId()
                + "-" + msg.getSessionId()
                + "-" + msg.getPayload();
        System.out.println("[CommandManager] ▶ 处理远程消息 key=" + uniqueKey);

        // 去重处理
        if (handledKeys.contains(uniqueKey)) {
            System.out.println("[CommandManager]    跳过重复消息 key=" + uniqueKey);
            return;
        }
        handledKeys.add(uniqueKey);

        int selfId    = SessionManager.getInstance().getPlayerId();
        String selfSid = SessionManager.getInstance().getSessionId();
        int msgPlayer = msg.getPlayerId();
        String msgSid  = msg.getSessionId();

        // START 总是执行一次
        if (msg.getType() == MessageType.START) {
            System.out.println("[CommandManager]    本地执行 START");
            executeLocal(GameCommand.fromMessage(msg));
            return;
        }

        // 如果是自己发出的 MOVE、SHORE_UP 或 NEXT_TURN，就跳过
        if ((msg.getType() == MessageType.MOVE
                || msg.getType() == MessageType.SHORE_UP
                || msg.getType() == MessageType.NEXT_TURN)
                && msgPlayer == selfId && selfSid.equals(msgSid)) {
            System.out.println("[CommandManager]    跳过自己发出的 " + msg.getType());
            return;
        }

        // 其它类型消息都要本地执行
        System.out.println("[CommandManager]    本地执行 " + msg.getType());
        executeLocal(GameCommand.fromMessage(msg));
    }

    /** 仅发送消息，不本地执行 */
    public void sendOnly(GameCommand cmd) {
        Message msg = cmd.toMessage();
        System.out.printf("[CMD][仅发送] %s → %s%n", msg.getType(), msg.getPayload());
        network.send(msg);
    }
}
