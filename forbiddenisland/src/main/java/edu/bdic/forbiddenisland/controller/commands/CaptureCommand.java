// src/main/java/edu/bdic/forbiddenisland/controller/commands/CaptureCommand.java
package edu.bdic.forbiddenisland.controller.commands;

import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

/**
 * 捕获宝藏命令：客户端点击后发送给服务器，也在本地 execute 以更新 UI 状态。
 */
public class CaptureCommand implements GameCommand {
    @Override
    public void execute() {
        int me = SessionManager.getInstance().getPlayerId();
        boolean ok = GameModel.getInstance().captureTreasure(me);
        // 如果本地执行失败，不要抛异常——UI 层会用弹窗提示
    }

    @Override
    public Message toMessage() {
        // payload 可以留空，本次只需要告诉服务器“我要捕获”
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        return new Message(
                MessageType.CAPTURE,
                SessionManager.getInstance().getSessionId(),
                SessionManager.getInstance().getPlayerId(),
                payload
        );
    }

    public static CaptureCommand fromMessage(Message msg) {
        // 服务端广播时，同样执行本地逻辑
        return new CaptureCommand();
    }
}
