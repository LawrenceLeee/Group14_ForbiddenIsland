package edu.bdic.forbiddenisland.network.handler.impl;

import edu.bdic.forbiddenisland.controller.commands.StartGameCommand;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import javafx.application.Platform;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class StartGameHandlerTest {

    @Test
    void testHandleSchedulesExecuteOnFxThread() {
        // 1. 构造一个假的 START 消息
        Message fakeMsg = new Message(
                MessageType.START,
                "session123",
                7,
                null
        );

        // 2. Mock StartGameCommand.fromMessage 返回一个 mockCmd
        StartGameCommand mockCmd = mock(StartGameCommand.class);

        // 3. Mock Platform.runLater(…) 静态方法以捕获 Runnable
        try (MockedStatic<StartGameCommand> sgStatic = mockStatic(StartGameCommand.class);
             MockedStatic<Platform> platformStatic = mockStatic(Platform.class)) {

            sgStatic.when(() -> StartGameCommand.fromMessage(eq(fakeMsg))).thenReturn(mockCmd);

            // 捕获传入 runLater 的 Runnable
            final Runnable[] captured = new Runnable[1];
            platformStatic.when(() -> Platform.runLater(any(Runnable.class)))
                    .thenAnswer(invocation -> {
                        captured[0] = invocation.getArgument(0);
                        return null;
                    });

            // 4. 调用 handler.handle(fakeMsg)
            StartGameHandler handler = new StartGameHandler();
            handler.handle(fakeMsg);

            // 5. 验证 StartGameCommand.fromMessage(fakeMsg) 调用一次
            sgStatic.verify(() -> StartGameCommand.fromMessage(eq(fakeMsg)), times(1));

            // 6. 验证 Platform.runLater 被调用一次
            platformStatic.verify(() -> Platform.runLater(any(Runnable.class)), times(1));

            // 7. 执行捕获的 Runnable 并验证 mockCmd.execute() 被调用
            assert captured[0] != null;
            captured[0].run();
            verify(mockCmd, times(1)).execute();
        }
    }
}
