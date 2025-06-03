package edu.bdic.forbiddenisland.network.handler.impl;

import edu.bdic.forbiddenisland.controller.CommandManager;
import edu.bdic.forbiddenisland.controller.commands.DiscardCommand;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class DiscardHandlerTest {

    @Test
    void testHandleDeserializesAndExecutesLocal() {
        // 1. 构造一个假的 Message，类型为 DISCARD
        Message fakeMsg = new Message(
                MessageType.DISCARD,
                "sessionY",
                3,
                null
        );

        // 2. Mock DiscardCommand.fromMessage 返回一个 mockCmd
        DiscardCommand mockCmd = mock(DiscardCommand.class);

        // 3. Mock CommandManager.getInstance() 返回一个 mockManager
        CommandManager mockManager = mock(CommandManager.class);

        try (MockedStatic<DiscardCommand> dcStatic = mockStatic(DiscardCommand.class);
             MockedStatic<CommandManager> cmStatic = mockStatic(CommandManager.class)) {

            dcStatic.when(() -> DiscardCommand.fromMessage(eq(fakeMsg))).thenReturn(mockCmd);
            cmStatic.when(CommandManager::getInstance).thenReturn(mockManager);

            // 4. 调用 handler.handle(fakeMsg)
            DiscardHandler handler = new DiscardHandler();
            handler.handle(fakeMsg);

            // 5. 验证 DiscardCommand.fromMessage 调用一次
            dcStatic.verify(() -> DiscardCommand.fromMessage(eq(fakeMsg)), times(1));

            // 6. 验证 CommandManager.getInstance().executeLocal(mockCmd) 被调用
            verify(mockManager, times(1)).executeLocal(eq(mockCmd));
        }
    }
}
