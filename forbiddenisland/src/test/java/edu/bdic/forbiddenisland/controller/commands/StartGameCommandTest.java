package edu.bdic.forbiddenisland.controller.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class StartGameCommandTest {

    @BeforeEach
    void setUp() {
        // 每次测试前重置 SessionManager
        SessionManager.getInstance().clear();
    }

    @Test
    void testExecuteInvokesGameModelInitializeWithSeed() {
        long seed = 123456789L;
        StartGameCommand cmd = new StartGameCommand(seed);

        // 静态 Mock GameModel.getInstance()
        GameModel mockModel = mock(GameModel.class);
        try (MockedStatic<GameModel> modelStatic = mockStatic(GameModel.class)) {
            modelStatic.when(GameModel::getInstance).thenReturn(mockModel);

            // 调用 execute，不应抛异常
            assertDoesNotThrow(cmd::execute);

            // 验证 GameModel.initializeGame(seed) 被调用一次
            modelStatic.verify(GameModel::getInstance, times(1));
            verify(mockModel, times(1)).initializeGame(eq(seed));
        }
    }

    @Test
    void testToMessageIncludesSeedAndSessionManagerValues() {
        long seed = ThreadLocalRandom.current().nextLong();
        // 设置 SessionManager
        SessionManager.getInstance().setSession("sessStart", 15, true);

        StartGameCommand cmd = new StartGameCommand(seed);
        Message msg = cmd.toMessage();

        assertEquals(MessageType.START, msg.getType(), "消息类型应为 START");
        assertEquals("sessStart", msg.getSessionId(), "sessionId 应来自 SessionManager");
        assertEquals(15, msg.getPlayerId(), "playerId 应来自 SessionManager");

        assertNotNull(msg.getPayload(), "payload 不应为 null");
        assertTrue(msg.getPayload() instanceof ObjectNode, "payload 应为 ObjectNode");
        ObjectNode node = (ObjectNode) msg.getPayload();
        assertTrue(node.has("seed"), "payload 应包含 seed 字段");
        assertEquals(seed, node.get("seed").asLong(), "payload.seed 值应与构造时 seed 一致");
    }

    @Test
    void testFromMessageParsesSeedCorrectly() throws Exception {
        long expectedSeed = 987654321L;
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("seed", expectedSeed);

        Message fakeMsg = new Message(
                MessageType.START,
                "ignored",
                0,
                payload
        );

        StartGameCommand cmd = StartGameCommand.fromMessage(fakeMsg);

        // 通过反射读取私有字段 seed
        Field seedField = StartGameCommand.class.getDeclaredField("seed");
        seedField.setAccessible(true);
        long actualSeed = seedField.getLong(cmd);
        assertEquals(expectedSeed, actualSeed, "反射读取的 seed 应与 payload 中的 seed 一致");
    }
}
