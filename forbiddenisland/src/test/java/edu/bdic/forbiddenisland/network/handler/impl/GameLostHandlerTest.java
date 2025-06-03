package edu.bdic.forbiddenisland.network.handler.impl;

import edu.bdic.forbiddenisland.controller.CommandManager;
import edu.bdic.forbiddenisland.controller.commands.GameLostCommand;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class GameLostHandlerTest {

    @Test
    void testHandleDeserializesAndExecutesLocal() {
        // 1. 构造一个假的 Message，类型为 GAME_LOST
        Message fakeMsg = new Message(
                MessageType.GAME_LOST,
                "sessionZ",
                4,
                null
        );

        // 2. Mock GameLostCommand.fromMessage 返回一个 mockCmd
        GameLostCommand mockCmd = mock(GameLostCommand.class);

        // 3. Mock CommandManager.getInstance() 返回一个 mockManager
        CommandManager mockManager = mock(CommandManager.class);

        try (MockedStatic<GameLostCommand> glStatic = mockStatic(GameLostCommand.class);
             MockedStatic<CommandManager> cmStatic = mockStatic(CommandManager.class)) {

            glStatic.when(() -> GameLostCommand.fromMessage(eq(fakeMsg))).thenReturn(mockCmd);
            cmStatic.when(CommandManager::getInstance).thenReturn(mockManager);

            // 4. 调用 handler.handle(fakeMsg)
            GameLostHandler handler = new GameLostHandler();
            handler.handle(fakeMsg);

            // 5. 验证 GameLostCommand.fromMessage 调用一次
            glStatic.verify(() -> GameLostCommand.fromMessage(eq(fakeMsg)), times(1));

            // 6. 验证 CommandManager.getInstance().executeLocal(mockCmd) 被调用
            verify(mockManager, times(1)).executeLocal(eq(mockCmd));
        }
    }
}
