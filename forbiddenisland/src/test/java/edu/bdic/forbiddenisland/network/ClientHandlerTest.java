package edu.bdic.forbiddenisland.network;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.bdic.forbiddenisland.controller.CommandManager;
import edu.bdic.forbiddenisland.model.GameModel;
import io.netty.channel.ChannelHandlerContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ClientHandlerTest {

    private ClientHandler handler;
    private Consumer<Message> mockCallback;
    private ChannelHandlerContext mockCtx;

    @BeforeEach
    void setUp() {
        mockCallback = mock(Consumer.class);
        mockCtx = mock(ChannelHandlerContext.class);
        handler = new ClientHandler(mockCallback);
    }

    @Test
    void testRoomCreatedInvokesCallback() throws Exception {
        Message fakeMsg = new Message(
                MessageType.ROOM_CREATED,
                "sess1",
                0,
                null
        );

        try (MockedStatic<JsonUtil> jsonStatic = mockStatic(JsonUtil.class)) {
            // Stub fromJson to return our fakeMsg
            jsonStatic.when(() -> JsonUtil.fromJson(eq("ignoredJson"))).thenReturn(fakeMsg);

            handler.channelRead0(mockCtx, "ignoredJson");

            verify(mockCallback, times(1)).accept(eq(fakeMsg));
            // Ensure no interaction with GameModel or CommandManager
            try (MockedStatic<GameModel> gmStatic = mockStatic(GameModel.class);
                 MockedStatic<CommandManager> cmStatic = mockStatic(CommandManager.class)) {
                gmStatic.verifyNoInteractions();
                cmStatic.verifyNoInteractions();
            }
        }
    }

    @Test
    void testUpdateTileStatusUpdatesGameModelWithoutCallback() throws Exception {
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("tileIndex", 7);
        payload.put("status", "SUNK");

        Message fakeMsg = new Message(
                MessageType.UPDATE_TILE_STATUS,
                "sess2",
                0,
                payload
        );

        GameModel mockModel = mock(GameModel.class);
        try (MockedStatic<JsonUtil> jsonStatic = mockStatic(JsonUtil.class);
             MockedStatic<GameModel> gmStatic = mockStatic(GameModel.class)) {
            jsonStatic.when(() -> JsonUtil.fromJson(eq("ignoredJson2"))).thenReturn(fakeMsg);
            gmStatic.when(GameModel::getInstance).thenReturn(mockModel);

            handler.channelRead0(mockCtx, "ignoredJson2");

            // Verify updateTileStatus called with correct arguments
            verify(mockModel, times(1)).updateTileStatus(eq(7), eq("SUNK"));
            // Callback should not be invoked
            verify(mockCallback, never()).accept(any());
            // CommandManager should not be invoked
            try (MockedStatic<CommandManager> cmStatic = mockStatic(CommandManager.class)) {
                cmStatic.verifyNoInteractions();
            }
        }
    }

    @Test
    void testGameWonInvokesCallback() throws Exception {
        Message fakeMsg = new Message(
                MessageType.GAME_WON,
                "sess3",
                0,
                null
        );

        try (MockedStatic<JsonUtil> jsonStatic = mockStatic(JsonUtil.class)) {
            jsonStatic.when(() -> JsonUtil.fromJson(eq("ignoredJson3"))).thenReturn(fakeMsg);

            handler.channelRead0(mockCtx, "ignoredJson3");

            verify(mockCallback, times(1)).accept(eq(fakeMsg));
            // No GameModel or CommandManager interactions
            try (MockedStatic<GameModel> gmStatic = mockStatic(GameModel.class);
                 MockedStatic<CommandManager> cmStatic = mockStatic(CommandManager.class)) {
                gmStatic.verifyNoInteractions();
                cmStatic.verifyNoInteractions();
            }
        }
    }

    @Test
    void testCommandTypeInvokesCommandManagerWithoutCallback() throws Exception {
        // Using MOVE as representative command type
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("dummy", 0);

        Message fakeMsg = new Message(
                MessageType.MOVE,
                "sess4",
                5,
                payload
        );

        CommandManager mockCm = mock(CommandManager.class);
        try (MockedStatic<JsonUtil> jsonStatic = mockStatic(JsonUtil.class);
             MockedStatic<CommandManager> cmStatic = mockStatic(CommandManager.class)) {
            jsonStatic.when(() -> JsonUtil.fromJson(eq("ignoredJson4"))).thenReturn(fakeMsg);
            cmStatic.when(CommandManager::getInstance).thenReturn(mockCm);

            handler.channelRead0(mockCtx, "ignoredJson4");

            verify(mockCm, times(1)).handleRemoteMessage(eq(fakeMsg));
            verify(mockCallback, never()).accept(any());
            // GameModel should not be invoked
            try (MockedStatic<GameModel> gmStatic = mockStatic(GameModel.class)) {
                gmStatic.verifyNoInteractions();
            }
        }
    }

    @Test
    void testDefaultInvokesCallback() throws Exception {
        // Using UPDATE_TILE (not UPDATE_TILE_STATUS) as default case
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("tile", 2);
        payload.put("status", "DRY");

        Message fakeMsg = new Message(
                MessageType.UPDATE_TILE,
                "sess5",
                0,
                payload
        );

        try (MockedStatic<JsonUtil> jsonStatic = mockStatic(JsonUtil.class)) {
            jsonStatic.when(() -> JsonUtil.fromJson(eq("ignoredJson5"))).thenReturn(fakeMsg);

            handler.channelRead0(mockCtx, "ignoredJson5");

            verify(mockCallback, times(1)).accept(eq(fakeMsg));
            // No GameModel or CommandManager interactions
            try (MockedStatic<GameModel> gmStatic = mockStatic(GameModel.class);
                 MockedStatic<CommandManager> cmStatic = mockStatic(CommandManager.class)) {
                gmStatic.verifyNoInteractions();
                cmStatic.verifyNoInteractions();
            }
        }
    }
}
