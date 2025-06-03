package edu.bdic.forbiddenisland.network.handler.impl;

import edu.bdic.forbiddenisland.controller.CommandManager;
import edu.bdic.forbiddenisland.controller.commands.JoinCommand;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class JoinHandlerTest {

    @Test
    void testHandleDeserializesAndExecutesLocal() {
        // 1. 构造一个假的 Message，类型为 JOIN
        Message fakeMsg = new Message(
                MessageType.JOIN,
                "sessionJoin",
                1,
                null
        );

        // 2. Mock JoinCommand.fromMessage 返回一个 mockCmd
        JoinCommand mockCmd = mock(JoinCommand.class);

        // 3. Mock CommandManager.getInstance() 返回一个 mockManager
        CommandManager mockManager = mock(CommandManager.class);

        try (MockedStatic<JoinCommand> jcStatic = mockStatic(JoinCommand.class);
             MockedStatic<CommandManager> cmStatic = mockStatic(CommandManager.class)) {

            jcStatic.when(() -> JoinCommand.fromMessage(eq(fakeMsg))).thenReturn(mockCmd);
            cmStatic.when(CommandManager::getInstance).thenReturn(mockManager);

            // 4. 调用 handler.handle(fakeMsg)
            JoinHandler handler = new JoinHandler();
            handler.handle(fakeMsg);

            // 5. 验证 JoinCommand.fromMessage 调用一次
            jcStatic.verify(() -> JoinCommand.fromMessage(eq(fakeMsg)), times(1));

            // 6. 验证 CommandManager.getInstance().executeLocal(mockCmd) 被调用
            verify(mockManager, times(1)).executeLocal(eq(mockCmd));
        }
    }
}
