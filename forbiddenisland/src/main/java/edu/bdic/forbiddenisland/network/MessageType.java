package edu.bdic.forbiddenisland.network;

public enum MessageType {
    CREATE_ROOM,
    ROOM_CREATED,
    JOIN,
    ROOM_JOINED,
    MOVE,
    SHORE_UP,
    UPDATE_TILE,
    START,
    NEXT_TURN,
    GIVE,
    CAPTURE,
    UPDATE_TILE_STATUS, DISCARD,
    GAME_WON,
    GAME_LOST
}
