package edu.bdic.forbiddenisland;

import edu.bdic.forbiddenisland.controller.CommandManager;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import edu.bdic.forbiddenisland.network.handler.MessageHandler;
import edu.bdic.forbiddenisland.network.handler.MessageHandlerFactory;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Method;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * MainApp 单元测试类，只针对 onServerMessage(Message msg) 方法的分支逻辑做验证
 */
public class MainAppTest {

    /**
     * 在所有测试之前，确保 JavaFX 平台已经初始化。
     * 使用 JFXPanel 即可触发 JavaFX 环境加载，从而避免在调用涉及 Font、Stage 等组件的方法时抛异常。
     */
    @BeforeAll
    public static void initJfx() {
        // 只要 new JFXPanel()，JavaFX 平台就会启动
        new JFXPanel();
    }

    /**
     * 通过反射调用 MainApp 的 private 方法 onServerMessage(Message msg)。
     */
    private void invokeOnServerMessage(MainApp app, Message msg) throws Exception {
        Method onServerMessage = MainApp.class.getDeclaredMethod("onServerMessage", Message.class);
        onServerMessage.setAccessible(true);
        onServerMessage.invoke(app, msg);
    }

    @Test
    public void test_onServerMessage_withGameCommandType_shouldCallCommandManagerAndHandler() throws Exception {
        // 1. 准备一个被测对象
        MainApp mainApp = new MainApp();

        // 2. 构造一个 Message 的 mock，设置它返回的 MessageType 为 JOIN（属于需要走 CommandManager 分支的类型）
        Message mockMsg = mock(Message.class);
        when(mockMsg.getType()).thenReturn(MessageType.JOIN);

        // 3. Mock CommandManager.getInstance() → 返回一个 spy 或 mock 对象
        CommandManager mockCmdMgr = mock(CommandManager.class);
        try (MockedStatic<CommandManager> cmStatic = Mockito.mockStatic(CommandManager.class)) {
            cmStatic.when(CommandManager::getInstance).thenReturn(mockCmdMgr);

            // 4. Mock MessageHandlerFactory.getHandler(type) → 返回一个可验证的 MessageHandler
            MessageHandler mockHandler = mock(MessageHandler.class);
            try (MockedStatic<MessageHandlerFactory> mhfStatic = Mockito.mockStatic(MessageHandlerFactory.class)) {
                mhfStatic.when(() -> MessageHandlerFactory.getHandler(eq(MessageType.JOIN)))
                        .thenReturn(mockHandler);

                // 5. 反射调用 onServerMessage
                invokeOnServerMessage(mainApp, mockMsg);

                // 6. 验证：对于 JOIN 类型，CommandManager.handleRemoteMessage(msg) 应该被调用一次
                verify(mockCmdMgr, times(1)).handleRemoteMessage(eq(mockMsg));

                // 7. 验证：无论什么类型，MessageHandlerFactory.getHandler(type).handle(msg) 都要被调用一次
                verify(mockHandler, times(1)).handle(eq(mockMsg));
            }
        }
    }

    @Test
    public void test_onServerMessage_withNonGameCommandType_shouldOnlyCallHandler() throws Exception {
        // 1. 准备被测对象
        MainApp mainApp = new MainApp();

        // 2. 构造一个 Message 的 mock，设置它返回的 MessageType 为 ROOM_CREATED（属于 default 分支，不走 CommandManager）
        Message mockMsg = mock(Message.class);
        when(mockMsg.getType()).thenReturn(MessageType.ROOM_CREATED);

        // 3. Mock CommandManager.getInstance() → 返回一个 spy 或 mock，但我们预计它不会被调用
        CommandManager mockCmdMgr = mock(CommandManager.class);
        try (MockedStatic<CommandManager> cmStatic = Mockito.mockStatic(CommandManager.class)) {
            cmStatic.when(CommandManager::getInstance).thenReturn(mockCmdMgr);

            // 4. Mock MessageHandlerFactory.getHandler(type) → 返回一个 mock Handler
            MessageHandler mockHandler = mock(MessageHandler.class);
            try (MockedStatic<MessageHandlerFactory> mhfStatic = Mockito.mockStatic(MessageHandlerFactory.class)) {
                mhfStatic.when(() -> MessageHandlerFactory.getHandler(eq(MessageType.ROOM_CREATED)))
                        .thenReturn(mockHandler);

                // 5. 反射调用 onServerMessage
                invokeOnServerMessage(mainApp, mockMsg);

                // 6. 验证：对于 ROOM_CREATED 类型，不应该调用 CommandManager.handleRemoteMessage(msg)
                verify(mockCmdMgr, never()).handleRemoteMessage(any());

                // 7. 验证：依旧会调用 handler.handle(msg) 一次
                verify(mockHandler, times(1)).handle(eq(mockMsg));
            }
        }
    }
}
