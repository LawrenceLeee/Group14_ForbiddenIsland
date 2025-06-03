package edu.bdic.forbiddenisland.network.handler.impl;

import edu.bdic.forbiddenisland.controller.CommandManager;
import edu.bdic.forbiddenisland.controller.commands.GameWonCommand;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class GameWonHandlerTest {

    @Test
    void testHandleDeserializesAndExecutesLocal() {
        // 1. 构造一个假的 Message，类型为 GAME_WON
        Message fakeMsg = new Message(
                MessageType.GAME_WON,
                "sessionWin",
                2,
                null
        );

        // 2. Mock GameWonCommand.fromMessage 返回一个 mockCmd
        GameWonCommand mockCmd = mock(GameWonCommand.class);

        // 3. Mock CommandManager.getInstance() 返回一个 mockManager
        CommandManager mockManager = mock(CommandManager.class);

        try (MockedStatic<GameWonCommand> gwStatic = mockStatic(GameWonCommand.class);
             MockedStatic<CommandManager> cmStatic = mockStatic(CommandManager.class)) {

            gwStatic.when(() -> GameWonCommand.fromMessage(eq(fakeMsg))).thenReturn(mockCmd);
            cmStatic.when(CommandManager::getInstance).thenReturn(mockManager);

            // 4. 调用 handler.handle(fakeMsg)
            GameWonHandler handler = new GameWonHandler();
            handler.handle(fakeMsg);

            // 5. 验证 GameWonCommand.fromMessage 调用一次
            gwStatic.verify(() -> GameWonCommand.fromMessage(eq(fakeMsg)), times(1));

            // 6. 验证 CommandManager.getInstance().executeLocal(mockCmd) 被调用
            verify(mockManager, times(1)).executeLocal(eq(mockCmd));
        }
    }
}
