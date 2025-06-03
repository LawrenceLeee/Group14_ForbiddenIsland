package edu.bdic.forbiddenisland.controller.commands;

import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class GameCommandTest {

    @Test
    void testFromMessage_startDelegatesToStartGameCommand() {
        Message fakeMsg = mock(Message.class);
        when(fakeMsg.getType()).thenReturn(MessageType.START);

        StartGameCommand fakeCmd = mock(StartGameCommand.class);

        try (MockedStatic<StartGameCommand> startStatic = mockStatic(StartGameCommand.class)) {
            startStatic.when(() -> StartGameCommand.fromMessage(eq(fakeMsg))).thenReturn(fakeCmd);

            GameCommand result = GameCommand.fromMessage(fakeMsg);

            startStatic.verify(() -> StartGameCommand.fromMessage(eq(fakeMsg)), times(1));
            assertSame(fakeCmd, result);
        }
    }

    @Test
    void testFromMessage_moveDelegatesToMoveCommand() {
        Message fakeMsg = mock(Message.class);
        when(fakeMsg.getType()).thenReturn(MessageType.MOVE);

        MoveCommand fakeCmd = mock(MoveCommand.class);

        try (MockedStatic<MoveCommand> moveStatic = mockStatic(MoveCommand.class)) {
            moveStatic.when(() -> MoveCommand.fromMessage(eq(fakeMsg))).thenReturn(fakeCmd);

            GameCommand result = GameCommand.fromMessage(fakeMsg);

            moveStatic.verify(() -> MoveCommand.fromMessage(eq(fakeMsg)), times(1));
            assertSame(fakeCmd, result);
        }
    }

    @Test
    void testFromMessage_shoreUpDelegatesToShoreUpCommand() {
        Message fakeMsg = mock(Message.class);
        when(fakeMsg.getType()).thenReturn(MessageType.SHORE_UP);

        ShoreUpCommand fakeCmd = mock(ShoreUpCommand.class);

        try (MockedStatic<ShoreUpCommand> shoreStatic = mockStatic(ShoreUpCommand.class)) {
            shoreStatic.when(() -> ShoreUpCommand.fromMessage(eq(fakeMsg))).thenReturn(fakeCmd);

            GameCommand result = GameCommand.fromMessage(fakeMsg);

            shoreStatic.verify(() -> ShoreUpCommand.fromMessage(eq(fakeMsg)), times(1));
            assertSame(fakeCmd, result);
        }
    }

    @Test
    void testFromMessage_nextTurnDelegatesToNextTurnCommand() {
        Message fakeMsg = mock(Message.class);
        when(fakeMsg.getType()).thenReturn(MessageType.NEXT_TURN);

        NextTurnCommand fakeCmd = mock(NextTurnCommand.class);

        try (MockedStatic<NextTurnCommand> nextStatic = mockStatic(NextTurnCommand.class)) {
            nextStatic.when(() -> NextTurnCommand.fromMessage(eq(fakeMsg))).thenReturn(fakeCmd);

            GameCommand result = GameCommand.fromMessage(fakeMsg);

            nextStatic.verify(() -> NextTurnCommand.fromMessage(eq(fakeMsg)), times(1));
            assertSame(fakeCmd, result);
        }
    }

    @Test
    void testFromMessage_captureDelegatesToCaptureCommand() {
        Message fakeMsg = mock(Message.class);
        when(fakeMsg.getType()).thenReturn(MessageType.CAPTURE);

        CaptureCommand fakeCmd = mock(CaptureCommand.class);

        try (MockedStatic<CaptureCommand> captureStatic = mockStatic(CaptureCommand.class)) {
            captureStatic.when(() -> CaptureCommand.fromMessage(eq(fakeMsg))).thenReturn(fakeCmd);

            GameCommand result = GameCommand.fromMessage(fakeMsg);

            captureStatic.verify(() -> CaptureCommand.fromMessage(eq(fakeMsg)), times(1));
            assertSame(fakeCmd, result);
        }
    }

    @Test
    void testFromMessage_giveDelegatesToGiveCommand() {
        Message fakeMsg = mock(Message.class);
        when(fakeMsg.getType()).thenReturn(MessageType.GIVE);

        GiveCommand fakeCmd = mock(GiveCommand.class);

        try (MockedStatic<GiveCommand> giveStatic = mockStatic(GiveCommand.class)) {
            giveStatic.when(() -> GiveCommand.fromMessage(eq(fakeMsg))).thenReturn(fakeCmd);

            GameCommand result = GameCommand.fromMessage(fakeMsg);

            giveStatic.verify(() -> GiveCommand.fromMessage(eq(fakeMsg)), times(1));
            assertSame(fakeCmd, result);
        }
    }

    @Test
    void testFromMessage_discardDelegatesToDiscardCommand() {
        Message fakeMsg = mock(Message.class);
        when(fakeMsg.getType()).thenReturn(MessageType.DISCARD);

        DiscardCommand fakeCmd = mock(DiscardCommand.class);

        try (MockedStatic<DiscardCommand> discardStatic = mockStatic(DiscardCommand.class)) {
            discardStatic.when(() -> DiscardCommand.fromMessage(eq(fakeMsg))).thenReturn(fakeCmd);

            GameCommand result = GameCommand.fromMessage(fakeMsg);

            discardStatic.verify(() -> DiscardCommand.fromMessage(eq(fakeMsg)), times(1));
            assertSame(fakeCmd, result);
        }
    }

    @Test
    void testFromMessage_gameWonDelegatesToGameWonCommand() {
        Message fakeMsg = mock(Message.class);
        when(fakeMsg.getType()).thenReturn(MessageType.GAME_WON);

        GameWonCommand fakeCmd = mock(GameWonCommand.class);

        try (MockedStatic<GameWonCommand> wonStatic = mockStatic(GameWonCommand.class)) {
            wonStatic.when(() -> GameWonCommand.fromMessage(eq(fakeMsg))).thenReturn(fakeCmd);

            GameCommand result = GameCommand.fromMessage(fakeMsg);

            wonStatic.verify(() -> GameWonCommand.fromMessage(eq(fakeMsg)), times(1));
            assertSame(fakeCmd, result);
        }
    }

    @Test
    void testFromMessage_gameLostDelegatesToGameLostCommand() {
        Message fakeMsg = mock(Message.class);
        when(fakeMsg.getType()).thenReturn(MessageType.GAME_LOST);

        GameLostCommand fakeCmd = mock(GameLostCommand.class);

        try (MockedStatic<GameLostCommand> lostStatic = mockStatic(GameLostCommand.class)) {
            lostStatic.when(() -> GameLostCommand.fromMessage(eq(fakeMsg))).thenReturn(fakeCmd);

            GameCommand result = GameCommand.fromMessage(fakeMsg);

            lostStatic.verify(() -> GameLostCommand.fromMessage(eq(fakeMsg)), times(1));
            assertSame(fakeCmd, result);
        }
    }

    @Test
    void testFromMessage_unknownTypeThrowsException() {
        Message fakeMsg = mock(Message.class);
        when(fakeMsg.getType()).thenReturn(MessageType.ROOM_CREATED);

        assertThrows(IllegalArgumentException.class, () -> GameCommand.fromMessage(fakeMsg));
    }
}
