package edu.bdic.forbiddenisland.network.handler;

import edu.bdic.forbiddenisland.network.MessageType;
import edu.bdic.forbiddenisland.network.handler.impl.*;

import java.util.EnumMap;
import java.util.Map;

public class MessageHandlerFactory {
    private static final Map<MessageType, MessageHandler> HANDLERS = new EnumMap<>(MessageType.class);

    static {
        HANDLERS.put(MessageType.JOIN,        new JoinHandler());
        HANDLERS.put(MessageType.MOVE,        new MoveHandler());
        HANDLERS.put(MessageType.SHORE_UP,    new ShoreUpHandler());
        HANDLERS.put(MessageType.UPDATE_TILE, new UpdateTileHandler());
        HANDLERS.put(MessageType.START,   new StartGameHandler());
        HANDLERS.put(MessageType.GIVE,   new GiveHandler());
        HANDLERS.put(MessageType.NEXT_TURN,   new NextTurnHandler());
        HANDLERS.put(MessageType.CAPTURE,   new CaptureHandler());
        HANDLERS.put(MessageType.DISCARD,   new DiscardHandler());
        HANDLERS.put(MessageType.GAME_WON,   new GameWonHandler());
        HANDLERS.put(MessageType.GAME_LOST,   new GameLostHandler());
        HANDLERS.put(MessageType.ENGINEER_SHOREUP,   new EngineerShoreUpHandler());

        // 房间初始化（房主创建 & 玩家加入 共用同一个逻辑）
        RoomSetupHandler roomSetup = new RoomSetupHandler();
        HANDLERS.put(MessageType.ROOM_CREATED, roomSetup);
        HANDLERS.put(MessageType.ROOM_JOINED,  roomSetup);
        // …如有其他 MessageType，也在这里注册…
    }

    /**
     * 根据消息类型返回对应的处理器，如果没有注册，则返回一个默认的空实现并打印警告。
     */
    public static MessageHandler getHandler(MessageType type) {
        return HANDLERS.getOrDefault(type, msg ->
                System.err.println("No handler registered for message type: " + type)
        );
    }
}
