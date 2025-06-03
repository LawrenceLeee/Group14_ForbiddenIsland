package edu.bdic.forbiddenisland.network.handler.impl;

import edu.bdic.forbiddenisland.controller.CommandManager;
import edu.bdic.forbiddenisland.controller.commands.CaptureCommand;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CaptureHandlerTest {

    @Test
    void testHandleDeserializesAndExecutesLocal() {
        // 1. 构造一个假的 Message，类型 CAPTURE
        Message fakeMsg = new Message(
                MessageType.CAPTURE,
                "sessionX",
                7,
                null
        );

        // 2. Mock CaptureCommand.fromMessage 返回一个 mockCmd
        CaptureCommand mockCmd = mock(CaptureCommand.class);

        // 3. Mock CommandManager.getInstance() 返回 a mock Manager
        CommandManager mockManager = mock(CommandManager.class);

        try (MockedStatic<CaptureCommand> ccStatic = mockStatic(CaptureCommand.class);
             MockedStatic<CommandManager> cmStatic = mockStatic(CommandManager.class)) {

            ccStatic.when(() -> CaptureCommand.fromMessage(eq(fakeMsg))).thenReturn(mockCmd);
            cmStatic.when(CommandManager::getInstance).thenReturn(mockManager);

            // 4. 调用 handler.handle(fakeMsg)
            CaptureHandler handler = new CaptureHandler();
            handler.handle(fakeMsg);

            // 5. 验证 CaptureCommand.fromMessage 调用一次
            ccStatic.verify(() -> CaptureCommand.fromMessage(eq(fakeMsg)), times(1));

            // 6. 验证 CommandManager.getInstance().executeLocal(mockCmd) 被调用
            verify(mockManager, times(1)).executeLocal(eq(mockCmd));
        }
    }
}
