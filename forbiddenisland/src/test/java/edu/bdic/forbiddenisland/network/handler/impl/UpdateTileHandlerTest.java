package edu.bdic.forbiddenisland.network.handler.impl;

import edu.bdic.forbiddenisland.controller.CommandManager;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class UpdateTileHandlerTest {

    @Test
    void testHandleInvokesHandleRemoteMessage() {
        // 构造一个假的 UPDATE_TILE 消息
        Message fakeMsg = new Message(
                MessageType.UPDATE_TILE,
                "sessionABC",
                2,
                null
        );

        // Mock CommandManager.getInstance() 返回一个 mockManager
        CommandManager mockManager = mock(CommandManager.class);

        try (MockedStatic<CommandManager> cmStatic = mockStatic(CommandManager.class)) {
            cmStatic.when(CommandManager::getInstance).thenReturn(mockManager);

            // 调用 handler.handle(fakeMsg)
            UpdateTileHandler handler = new UpdateTileHandler();
            handler.handle(fakeMsg);

            // 验证 CommandManager.getInstance().handleRemoteMessage(fakeMsg) 被调用一次
            verify(mockManager, times(1)).handleRemoteMessage(eq(fakeMsg));
        }
    }
}
