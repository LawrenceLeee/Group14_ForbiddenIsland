package edu.bdic.forbiddenisland.controller;

import com.fasterxml.jackson.databind.JsonNode;
import edu.bdic.forbiddenisland.controller.commands.GameCommand;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import edu.bdic.forbiddenisland.network.NetworkManager;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;
import java.util.Deque;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * 完整修正后的 CommandManager 单元测试，解决了：
 * 1. JavaFX Toolkit 未初始化导致的 Toolkit not initialized 异常。
 * 2. NetworkManager.send(...) 调用真实实例导致的 NPE，将 network 字段替换为 mock。
 */
class CommandManagerTest {

    private static boolean javafxInitialized = false;

    private CommandManager realMgr;
    private NetworkManager mockNetwork;
    private MockedStatic<NetworkManager> nmStatic;

    @BeforeAll
    static void initJavaFxToolkit() throws Exception {
        if (!javafxInitialized) {
            CountDownLatch latch = new CountDownLatch(1);
            Platform.startup(latch::countDown);
            latch.await();
            javafxInitialized = true;
        }
    }

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() throws Exception {
        // 获取 CommandManager 单例，并清空其内部 state
        realMgr = CommandManager.getInstance();

        // 清空 history
        Field historyField = CommandManager.class.getDeclaredField("history");
        historyField.setAccessible(true);
        ((Deque<GameCommand>) historyField.get(realMgr)).clear();

        // 清空 handledKeys
        Field keysField = CommandManager.class.getDeclaredField("handledKeys");
        keysField.setAccessible(true);
        ((Set<String>) keysField.get(realMgr)).clear();

        // Mock NetworkManager.getInstance() 返回一个可控的 mockNetwork
        mockNetwork = mock(NetworkManager.class);
        nmStatic = mockStatic(NetworkManager.class);
        nmStatic.when(NetworkManager::getInstance).thenReturn(mockNetwork);

        // 由于 CommandManager 构造时已经将 network 字段初始化为真实实例，
        // 需要通过反射把它替换为 mockNetwork，以避免真正调用 channel 写入
        Field networkField = CommandManager.class.getDeclaredField("network");
        networkField.setAccessible(true);
        networkField.set(realMgr, mockNetwork);
    }

    @AfterEach
    void tearDown() {
        // 关闭静态 Mock，避免影响其他测试
        if (nmStatic != null) {
            nmStatic.close();
        }
    }

    @Test
    void testExecuteLocal_pushesIntoHistory() throws Exception {
        GameCommand mockCmd = mock(GameCommand.class);

        // 调用 executeLocal，会在 JavaFX 线程推送侧执行，但我们只检查 history
        realMgr.executeLocal(mockCmd);

        Field historyField = CommandManager.class.getDeclaredField("history");
        historyField.setAccessible(true);
        @SuppressWarnings("unchecked")
        Deque<GameCommand> history = (Deque<GameCommand>) historyField.get(realMgr);

        assertEquals(1, history.size(), "调用 executeLocal 后，history 大小应为 1");
        assertSame(mockCmd, history.peek(), "history 顶端应为刚刚传入的命令");
    }

    @Test
    void testExecuteAndSend_nonStart_executesLocalThenSends() throws Exception {
        GameCommand mockCmd = mock(GameCommand.class);
        Message fakeMsg = mock(Message.class);
        when(fakeMsg.getType()).thenReturn(MessageType.MOVE);
        JsonNode dummyPayload = mock(JsonNode.class);
        doReturn(dummyPayload).when(fakeMsg).getPayload();
        when(mockCmd.toMessage()).thenReturn(fakeMsg);

        CommandManager spyMgr = spy(realMgr);
        try (MockedStatic<CommandManager> cmStatic = mockStatic(CommandManager.class)) {
            cmStatic.when(CommandManager::getInstance).thenReturn(spyMgr);

            spyMgr.executeAndSend(mockCmd);

            verify(spyMgr, times(1)).executeLocal(eq(mockCmd));
            verify(mockNetwork, times(1)).send(eq(fakeMsg));

            Field historyField = CommandManager.class.getDeclaredField("history");
            historyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Deque<GameCommand> history = (Deque<GameCommand>) historyField.get(realMgr);
            assertEquals(1, history.size(), "history 应包含 1 条命令");
            assertSame(mockCmd, history.peek(), "history 顶端应是 mockCmd");
        }
    }

    @Test
    void testExecuteAndSend_startOnlySends() throws Exception {
        GameCommand mockCmd = mock(GameCommand.class);
        Message fakeMsg = mock(Message.class);
        when(fakeMsg.getType()).thenReturn(MessageType.START);
        JsonNode dummyPayload = mock(JsonNode.class);
        doReturn(dummyPayload).when(fakeMsg).getPayload();
        when(mockCmd.toMessage()).thenReturn(fakeMsg);

        CommandManager spyMgr = spy(realMgr);
        try (MockedStatic<CommandManager> cmStatic = mockStatic(CommandManager.class)) {
            cmStatic.when(CommandManager::getInstance).thenReturn(spyMgr);

            spyMgr.executeAndSend(mockCmd);

            verify(spyMgr, never()).executeLocal(any());
            verify(mockNetwork, times(1)).send(eq(fakeMsg));

            Field historyField = CommandManager.class.getDeclaredField("history");
            historyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Deque<GameCommand> history = (Deque<GameCommand>) historyField.get(realMgr);
            assertTrue(history.isEmpty(), "START 类型不应往 history push");
        }
    }

    @Test
    void testSendOnly_alwaysSends_noHistoryPush() throws Exception {
        GameCommand mockCmd = mock(GameCommand.class);
        Message fakeMsg = mock(Message.class);
        when(fakeMsg.getType()).thenReturn(MessageType.UPDATE_TILE);
        JsonNode dummyPayload = mock(JsonNode.class);
        doReturn(dummyPayload).when(fakeMsg).getPayload();
        when(mockCmd.toMessage()).thenReturn(fakeMsg);

        CommandManager spyMgr = spy(realMgr);
        try (MockedStatic<CommandManager> cmStatic = mockStatic(CommandManager.class)) {
            cmStatic.when(CommandManager::getInstance).thenReturn(spyMgr);

            spyMgr.sendOnly(mockCmd);

            verify(spyMgr, never()).executeLocal(any());
            verify(mockNetwork, times(1)).send(eq(fakeMsg));

            Field historyField = CommandManager.class.getDeclaredField("history");
            historyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Deque<GameCommand> history = (Deque<GameCommand>) historyField.get(realMgr);
            assertTrue(history.isEmpty(), "sendOnly 不应往 history push");
        }
    }

    @Test
    void testHandleRemoteMessage_start_alwaysExecutesOnce() throws Exception {
        Message fakeMsg = mock(Message.class);
        when(fakeMsg.getType()).thenReturn(MessageType.START);
        when(fakeMsg.getPlayerId()).thenReturn(5);
        when(fakeMsg.getSessionId()).thenReturn("SID1");
        JsonNode dummyPayload = mock(JsonNode.class);
        doReturn(dummyPayload).when(fakeMsg).getPayload();

        SessionManager mockSession = mock(SessionManager.class);
        when(mockSession.getPlayerId()).thenReturn(99);
        when(mockSession.getSessionId()).thenReturn("OTHER");

        try (MockedStatic<SessionManager> smStatic = mockStatic(SessionManager.class);
             MockedStatic<GameCommand> gcStatic = mockStatic(GameCommand.class);
             MockedStatic<CommandManager> cmStatic = mockStatic(CommandManager.class)) {

            smStatic.when(SessionManager::getInstance).thenReturn(mockSession);

            GameCommand mockCmd = mock(GameCommand.class);
            gcStatic.when(() -> GameCommand.fromMessage(eq(fakeMsg))).thenReturn(mockCmd);

            CommandManager spyMgr = spy(realMgr);
            cmStatic.when(CommandManager::getInstance).thenReturn(spyMgr);

            spyMgr.handleRemoteMessage(fakeMsg);

            verify(spyMgr, times(1)).executeLocal(eq(mockCmd));

            Field keysField = CommandManager.class.getDeclaredField("handledKeys");
            keysField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Set<String> keys = (Set<String>) keysField.get(realMgr);
            String expectedKey = MessageType.START + "-" + fakeMsg.getPlayerId() + "-" +
                    fakeMsg.getSessionId() + "-" + fakeMsg.getPayload();
            assertTrue(keys.contains(expectedKey),
                    "handledKeys 应包含该 START 消息生成的唯一 key");
        }
    }

    @Test
    void testHandleRemoteMessage_duplicate_isIgnored() throws Exception {
        Message fakeMsg = mock(Message.class);
        when(fakeMsg.getType()).thenReturn(MessageType.UPDATE_TILE);
        when(fakeMsg.getPlayerId()).thenReturn(1);
        when(fakeMsg.getSessionId()).thenReturn("S1");
        JsonNode dummyPayload = mock(JsonNode.class);
        doReturn(dummyPayload).when(fakeMsg).getPayload();

        GameCommand mockCmd = mock(GameCommand.class);

        SessionManager mockSession = mock(SessionManager.class);
        when(mockSession.getPlayerId()).thenReturn(2);
        when(mockSession.getSessionId()).thenReturn("OTHER");

        try (MockedStatic<SessionManager> smStatic = mockStatic(SessionManager.class);
             MockedStatic<GameCommand> gcStatic = mockStatic(GameCommand.class);
             MockedStatic<CommandManager> cmStatic = mockStatic(CommandManager.class)) {

            smStatic.when(SessionManager::getInstance).thenReturn(mockSession);
            gcStatic.when(() -> GameCommand.fromMessage(eq(fakeMsg))).thenReturn(mockCmd);

            CommandManager spyMgr = spy(realMgr);
            cmStatic.when(CommandManager::getInstance).thenReturn(spyMgr);

            spyMgr.handleRemoteMessage(fakeMsg);
            verify(spyMgr, times(1)).executeLocal(eq(mockCmd));

            spyMgr.handleRemoteMessage(fakeMsg);
            verify(spyMgr, times(1)).executeLocal(eq(mockCmd));
        }
    }

    @Test
    void testHandleRemoteMessage_skipOwnMoveOrShoreUpOrNextTurn() throws Exception {
        Message fakeMsg = mock(Message.class);
        when(fakeMsg.getType()).thenReturn(MessageType.MOVE);
        when(fakeMsg.getPlayerId()).thenReturn(10);
        when(fakeMsg.getSessionId()).thenReturn("S10");
        JsonNode dummyPayload = mock(JsonNode.class);
        doReturn(dummyPayload).when(fakeMsg).getPayload();

        SessionManager mockSession = mock(SessionManager.class);
        when(mockSession.getPlayerId()).thenReturn(10);
        when(mockSession.getSessionId()).thenReturn("S10");

        try (MockedStatic<SessionManager> smStatic = mockStatic(SessionManager.class);
             MockedStatic<GameCommand> gcStatic = mockStatic(GameCommand.class);
             MockedStatic<CommandManager> cmStatic = mockStatic(CommandManager.class)) {

            smStatic.when(SessionManager::getInstance).thenReturn(mockSession);
            gcStatic.when(() -> GameCommand.fromMessage(any()))
                    .thenThrow(new AssertionError("不应调用 fromMessage"));

            CommandManager spyMgr = spy(realMgr);
            cmStatic.when(CommandManager::getInstance).thenReturn(spyMgr);

            spyMgr.handleRemoteMessage(fakeMsg);
            verify(spyMgr, never()).executeLocal(any());
        }
    }

    @Test
    void testHandleRemoteMessage_otherTypes_executesLocal() throws Exception {
        Message fakeMsg = mock(Message.class);
        when(fakeMsg.getType()).thenReturn(MessageType.SHORE_UP);
        when(fakeMsg.getPlayerId()).thenReturn(3);
        when(fakeMsg.getSessionId()).thenReturn("ABC");
        JsonNode dummyPayload = mock(JsonNode.class);
        doReturn(dummyPayload).when(fakeMsg).getPayload();

        SessionManager mockSession = mock(SessionManager.class);
        when(mockSession.getPlayerId()).thenReturn(99);
        when(mockSession.getSessionId()).thenReturn("ZZZ");

        GameCommand mockCmd = mock(GameCommand.class);

        try (MockedStatic<SessionManager> smStatic = mockStatic(SessionManager.class);
             MockedStatic<GameCommand> gcStatic = mockStatic(GameCommand.class);
             MockedStatic<CommandManager> cmStatic = mockStatic(CommandManager.class)) {

            smStatic.when(SessionManager::getInstance).thenReturn(mockSession);
            gcStatic.when(() -> GameCommand.fromMessage(eq(fakeMsg))).thenReturn(mockCmd);

            CommandManager spyMgr = spy(realMgr);
            cmStatic.when(CommandManager::getInstance).thenReturn(spyMgr);

            spyMgr.handleRemoteMessage(fakeMsg);
            verify(spyMgr, times(1)).executeLocal(eq(mockCmd));
        }
    }
}
