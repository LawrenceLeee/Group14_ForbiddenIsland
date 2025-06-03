package edu.bdic.forbiddenisland.controller.commands;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.model.TreasureCardType;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class DiscardCommandTest {

    @BeforeEach
    void setUp() {
        // 在每次测试前清空 SessionManager
        SessionManager.getInstance().clear();
    }

    @Test
    void testExecuteInvokesGameModelDiscardWithCorrectParameters() {
        // 1. 创建一个 DiscardCommand，指定 playerIndex 和 card
        int playerIndex = 3;
        TreasureCardType card = TreasureCardType.EARTH;
        DiscardCommand cmd = new DiscardCommand(playerIndex, card);

        // 2. 静态 Mock GameModel.getInstance()
        GameModel mockModel = mock(GameModel.class);
        try (MockedStatic<GameModel> modelStatic = mockStatic(GameModel.class)) {
            modelStatic.when(GameModel::getInstance).thenReturn(mockModel);

            // 3. 调用 execute()
            assertDoesNotThrow(cmd::execute, "execute 不应抛出异常");

            // 4. 验证 GameModel.discardTreasure(playerIndex, card) 被调用一次
            modelStatic.verify(() -> GameModel.getInstance(), times(1));
            verify(mockModel, times(1)).discardTreasure(eq(playerIndex), eq(card));
        }
    }

    @Test
    void testToMessageConstructsProperMessage() {
        // 1. 设置 SessionManager 中的 sessionId 和 playerId
        SessionManager.getInstance().setSession("sessionXYZ", 42, false);

        // 2. 创建一个 DiscardCommand
        int playerIndex = 5;
        TreasureCardType card = TreasureCardType.FIRE;
        DiscardCommand cmd = new DiscardCommand(playerIndex, card);

        // 3. 调用 toMessage()
        Message msg = cmd.toMessage();

        // 4. 验证 MessageType, sessionId, playerId
        assertEquals(MessageType.DISCARD, msg.getType(), "MessageType 应为 DISCARD");
        assertEquals("sessionXYZ", msg.getSessionId(), "sessionId 应与 SessionManager 中一致");
        assertEquals(42, msg.getPlayerId(), "playerId 应与 SessionManager 中一致");

        // 5. 验证 payload 中的字段：playerIndex 和 card
        assertNotNull(msg.getPayload(), "payload 不应为 null");
        assertTrue(msg.getPayload().has("playerIndex"), "payload 应包含 playerIndex 字段");
        assertTrue(msg.getPayload().has("card"), "payload 应包含 card 字段");
        assertEquals(playerIndex, msg.getPayload().get("playerIndex").asInt(),
                "payload.playerIndex 值应与构造时一致");
        assertEquals(card.name(), msg.getPayload().get("card").asText(),
                "payload.card 值应与构造时 card.name() 一致");
    }

    @Test
    void testFromMessageParsesFieldsCorrectly() throws Exception {
        // 1. 构建一个 ObjectNode payload，包含 playerIndex 和 card
        int expectedIndex = 7;
        TreasureCardType expectedCard = TreasureCardType.OCEAN;
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("playerIndex", expectedIndex);
        payload.put("card", expectedCard.name());

        // 2. 构造对应的 Message
        Message fakeMsg = new Message(
                MessageType.DISCARD,
                "ignoredSession",
                0,
                payload
        );

        // 3. 调用 fromMessage()
        DiscardCommand cmd = DiscardCommand.fromMessage(fakeMsg);

        // 4. 通过反射读取私有字段 playerIndex 和 card
        Field indexField = DiscardCommand.class.getDeclaredField("playerIndex");
        indexField.setAccessible(true);
        int actualIndex = indexField.getInt(cmd);
        assertEquals(expectedIndex, actualIndex, "反射读取的 playerIndex 应与 payload 中一致");

        Field cardField = DiscardCommand.class.getDeclaredField("card");
        cardField.setAccessible(true);
        TreasureCardType actualCard = (TreasureCardType) cardField.get(cmd);
        assertEquals(expectedCard, actualCard, "反射读取的 card 应与 payload 中一致");
    }
}
