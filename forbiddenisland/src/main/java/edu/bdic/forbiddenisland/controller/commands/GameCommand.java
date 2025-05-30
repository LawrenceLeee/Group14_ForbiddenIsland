package edu.bdic.forbiddenisland.controller.commands;

import edu.bdic.forbiddenisland.network.Message;
/**
 * 所有游戏命令的统一接口
 */
public interface GameCommand {
    /** 在客户端执行对应的本地逻辑（修改模型、触发UI刷新等） */
    void execute();
    /** 转换成要发送到服务器的消息 */
    Message toMessage();

    /**
     * 根据收到的 Message 构造对应的 Command
     */
    static GameCommand fromMessage(Message msg) {
        switch (msg.getType()) {
            case START:
                return StartGameCommand.fromMessage(msg);
            case JOIN:
                return JoinCommand.fromMessage(msg);
            case MOVE:
                return MoveCommand.fromMessage(msg);
            case SHORE_UP:
                return ShoreUpCommand.fromMessage(msg);
            case NEXT_TURN:
                return NextTurnCommand.fromMessage(msg);
            case CAPTURE:
                return CaptureCommand.fromMessage(msg);
            case GIVE:
                return GiveCommand.fromMessage(msg);
            case DISCARD:
                return DiscardCommand.fromMessage(msg);
            case GAME_WON:
                return GameWonCommand.fromMessage(msg);
            case GAME_LOST:
                return GameLostCommand.fromMessage(msg);
            default:
                throw new IllegalArgumentException("Unsupported message type: " + msg.getType());
        }
    }
}
