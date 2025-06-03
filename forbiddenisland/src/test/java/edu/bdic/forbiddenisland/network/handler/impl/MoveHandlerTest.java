package edu.bdic.forbiddenisland.network.handler.impl;

import edu.bdic.forbiddenisland.controller.CommandManager;
import edu.bdic.forbiddenisland.controller.commands.MoveCommand;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class MoveHandlerTest {

    @Test
    void testHandleDeserializesAndExecutesLocal() {
        // 1. 构造一个假的 Message，类型为 MOVE
        Message fakeMsg = new Message(
                MessageType.MOVE,
                "sessionMove",
                2,
                null
        );

        // 2. Mock MoveCommand.fromMessage 返回一个 mockCmd
        MoveCommand mockCmd = mock(MoveCommand.class);

        // 3. Mock CommandManager.getInstance() 返回一个 mockManager
        CommandManager mockManager = mock(CommandManager.class);

        try (MockedStatic<MoveCommand> mcStatic = mockStatic(MoveCommand.class);
             MockedStatic<CommandManager> cmStatic = mockStatic(CommandManager.class)) {

            mcStatic.when(() -> MoveCommand.fromMessage(eq(fakeMsg))).thenReturn(mockCmd);
            cmStatic.when(CommandManager::getInstance).thenReturn(mockManager);

            // 4. 调用 handler.handle(fakeMsg)
            MoveHandler handler = new MoveHandler();
            handler.handle(fakeMsg);

            // 5. 验证 MoveCommand.fromMessage 调用一次
            mcStatic.verify(() -> MoveCommand.fromMessage(eq(fakeMsg)), times(1));

            // 6. 验证 CommandManager.getInstance().executeLocal(mockCmd) 被调用
            verify(mockManager, times(1)).executeLocal(eq(mockCmd));
        }
    }
}
