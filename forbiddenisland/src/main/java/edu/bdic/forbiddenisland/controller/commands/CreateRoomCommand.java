package edu.bdic.forbiddenisland.controller.commands;

import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;

/**
 * 创建房间：向服务器请求，并在收到 ROOM_CREATED 后在 handler 中
 * 执行本地「房主加入」逻辑（index=0）
 */
public class CreateRoomCommand implements GameCommand {
    @Override
    public void execute() {
        // 本地等待服务器分配，实际 addPlayer(0) 在 CreateRoomHandler 中做
    }

    @Override
    public Message toMessage() {
        return new Message(
                MessageType.CREATE_ROOM,
                "",   // session 未知
                0,   // playerId 字段不再使用
                null
        );
    }

    public static CreateRoomCommand fromMessage(Message msg) {
        return new CreateRoomCommand();
    }
}