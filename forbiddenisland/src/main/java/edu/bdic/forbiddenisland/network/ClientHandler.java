// src/main/java/edu/bdic/forbiddenisland/network/ClientHandler.java
package edu.bdic.forbiddenisland.network;

import edu.bdic.forbiddenisland.controller.CommandManager;
import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.network.MessageType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.function.Consumer;

/**
 * 客户端的 Netty Handler：接收服务器消息，将其分发给 UI 回调或 CommandManager
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> {
    private final Consumer<Message> callback;

    public ClientHandler(Consumer<Message> callback) {
        this.callback = callback;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msgJson) {
        Message m = JsonUtil.fromJson(msgJson);

        switch (m.getType()) {
            // —— UI 更新类消息 ——
            case ROOM_CREATED:
            case ROOM_JOINED:
                // 房间创建/加入只给 UI
                callback.accept(m);
                break;

            // —— 同步单格状态 ——
            case UPDATE_TILE_STATUS:
                int tileIndex = m.getPayload().get("tileIndex").asInt();
                String status  = m.getPayload().get("status").asText();
                GameModel.getInstance().updateTileStatus(tileIndex, status);
                break;

            // —— 游戏胜利/失败 ——（也给 UI 处理弹窗等）
            case GAME_WON:
            case GAME_LOST:
                callback.accept(m);
                break;

            // —— 真正的“命令”交给 CommandManager 去幂等 & 执行 ——
            case START:
            case MOVE:
            case SHORE_UP:
            case GIVE:
            case DISCARD:
            case CAPTURE:
            case NEXT_TURN:
                CommandManager.getInstance().handleRemoteMessage(m);
                break;

            // —— 其他通用 UI 消息 ——
            default:
                callback.accept(m);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
