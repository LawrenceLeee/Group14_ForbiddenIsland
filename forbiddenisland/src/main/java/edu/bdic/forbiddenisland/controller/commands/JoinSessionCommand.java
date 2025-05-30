package edu.bdic.forbiddenisland.controller.commands;

import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;

/**
 * 请求加入指定的会话（sessionId），等待服务器分配 playerIndex
 */
public class JoinSessionCommand implements GameCommand {
    private final String sessionId;

    public JoinSessionCommand(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public void execute() {
        // 本地无需立即执行，等待 RoomJoinedHandler 回调
    }

    @Override
    public Message toMessage() {
        // 使用当前会话 ID 发送 JOIN 请求
        return new Message(
                MessageType.JOIN,
                sessionId,
                SessionManager.getInstance().getPlayerId(),
                null
        );
    }

    public static JoinSessionCommand fromMessage(Message msg) {
        return new JoinSessionCommand(msg.getSessionId());
    }
}

