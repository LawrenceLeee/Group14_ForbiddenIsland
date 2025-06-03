package edu.bdic.forbiddenisland.network.handler;

import edu.bdic.forbiddenisland.network.handler.impl.*;
import edu.bdic.forbiddenisland.network.MessageType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MessageHandlerFactoryTest {

    private final PrintStream originalErr = System.err;
    private ByteArrayOutputStream errContent;

    @BeforeEach
    void setUpStreams() {
        errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setErr(originalErr);
    }

    @Test
    void testRegisteredHandlersReturnCorrectInstance() {
        // For each MessageType that should be registered, verify the handler type
        assertTrue(MessageHandlerFactory.getHandler(MessageType.JOIN) instanceof JoinHandler);
        assertTrue(MessageHandlerFactory.getHandler(MessageType.MOVE) instanceof MoveHandler);
        assertTrue(MessageHandlerFactory.getHandler(MessageType.SHORE_UP) instanceof ShoreUpHandler);
        assertTrue(MessageHandlerFactory.getHandler(MessageType.UPDATE_TILE) instanceof UpdateTileHandler);
        assertTrue(MessageHandlerFactory.getHandler(MessageType.START) instanceof StartGameHandler);
        assertTrue(MessageHandlerFactory.getHandler(MessageType.GIVE) instanceof GiveHandler);
        assertTrue(MessageHandlerFactory.getHandler(MessageType.NEXT_TURN) instanceof NextTurnHandler);
        assertTrue(MessageHandlerFactory.getHandler(MessageType.CAPTURE) instanceof CaptureHandler);
        assertTrue(MessageHandlerFactory.getHandler(MessageType.DISCARD) instanceof DiscardHandler);
        assertTrue(MessageHandlerFactory.getHandler(MessageType.GAME_WON) instanceof GameWonHandler);
        assertTrue(MessageHandlerFactory.getHandler(MessageType.GAME_LOST) instanceof GameLostHandler);
        assertTrue(MessageHandlerFactory.getHandler(MessageType.ROOM_CREATED) instanceof RoomSetupHandler);
        assertTrue(MessageHandlerFactory.getHandler(MessageType.ROOM_JOINED) instanceof RoomSetupHandler);
    }

    @Test
    void testUnregisteredHandlerPrintsWarning() {
        // Pick a MessageType that exists but isn’t explicitly registered (e.g., UPDATE_TILE_STATUS)
        MessageType unregisteredType = MessageType.UPDATE_TILE_STATUS;

        var handler = MessageHandlerFactory.getHandler(unregisteredType);
        assertNotNull(handler, "getHandler shouldn’t return null even for unregistered types");

        // Invoke handle(...) and capture stderr
        handler.handle(null);
        String output = errContent.toString().trim();
        assertTrue(output.contains("No handler registered for message type: " + unregisteredType),
                "Should print warning for unregistered type: " + unregisteredType);
    }
}
