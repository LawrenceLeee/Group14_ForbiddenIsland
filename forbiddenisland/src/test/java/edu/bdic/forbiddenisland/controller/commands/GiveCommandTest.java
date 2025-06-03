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

class GiveCommandTest {

    @BeforeEach
    void setUp() {
        // 每次测试前重置 SessionManager
        SessionManager.getInstance().clear();
    }

    @Test
    void testExecuteInvokesGameModelGiveCardWithCorrectParameters() {
        int fromPlayer = 2;
        int toPlayer = 5;
        TreasureCardType card = TreasureCardType.WIND;
        GiveCommand cmd = new GiveCommand(fromPlayer, toPlayer, card);

        // 静态 Mock GameModel.getInstance()
        GameModel mockModel = mock(GameModel.class);
        try (MockedStatic<GameModel> modelStatic = mockStatic(GameModel.class)) {
            modelStatic.when(GameModel::getInstance).thenReturn(mockModel);

            // 调用 execute，不应抛出异常
            assertDoesNotThrow(cmd::execute);

            // 验证 GameModel.giveCard(2, 5, WIND) 被调用一次
            modelStatic.verify(GameModel::getInstance, times(1));
            verify(mockModel, times(1)).giveCard(eq(fromPlayer), eq(toPlayer), eq(card));
        }
    }

    @Test
    void testToMessageConstructsProperMessage() {
        // 设置 SessionManager
        SessionManager.getInstance().setSession("sessGive", 7, false);

        int fromPlayer = 1;
        int toPlayer = 3;
        TreasureCardType card = TreasureCardType.SANDBAG;
        GiveCommand cmd = new GiveCommand(fromPlayer, toPlayer, card);

        // 调用 toMessage()
        Message msg = cmd.toMessage();

        // 验证 MessageType, sessionId, playerId
        assertEquals(MessageType.GIVE, msg.getType(), "消息类型应为 GIVE");
        assertEquals("sessGive", msg.getSessionId(), "sessionId 应来自 SessionManager");
        assertEquals(7, msg.getPlayerId(), "playerId 应来自 SessionManager");

        // 验证 payload 中的字段 from, to, card
        assertNotNull(msg.getPayload(), "payload 不应为 null");
        assertTrue(msg.getPayload().has("from"), "payload 应包含 from 字段");
        assertTrue(msg.getPayload().has("to"), "payload 应包含 to 字段");
        assertTrue(msg.getPayload().has("card"), "payload 应包含 card 字段");
        assertEquals(fromPlayer, msg.getPayload().get("from").asInt(),
                "payload.from 值应与构造时 fromPlayer 一致");
        assertEquals(toPlayer, msg.getPayload().get("to").asInt(),
                "payload.to 值应与构造时 toPlayer 一致");
        assertEquals(card.name(), msg.getPayload().get("card").asText(),
                "payload.card 值应与构造时 card.name() 一致");
    }

    @Test
    void testFromMessageParsesFieldsCorrectly() throws Exception {
        // 构建 ObjectNode payload，包含 from, to, card
        int expectedFrom = 4;
        int expectedTo = 6;
        TreasureCardType expectedCard = TreasureCardType.HELICOPTER;
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("from", expectedFrom);
        payload.put("to", expectedTo);
        payload.put("card", expectedCard.name());

        // 构造对应的 Message
        Message fakeMsg = new Message(
                MessageType.GIVE,
                "anySession",
                0,
                payload
        );

        // 调用 fromMessage()
        GiveCommand cmd = GiveCommand.fromMessage(fakeMsg);

        // 通过反射读取私有字段 fromPlayer、toPlayer 和 card
        Field fromField = GiveCommand.class.getDeclaredField("fromPlayer");
        fromField.setAccessible(true);
        int actualFrom = fromField.getInt(cmd);
        assertEquals(expectedFrom, actualFrom, "反射读取的 fromPlayer 应与 payload 中一致");

        Field toField = GiveCommand.class.getDeclaredField("toPlayer");
        toField.setAccessible(true);
        int actualTo = toField.getInt(cmd);
        assertEquals(expectedTo, actualTo, "反射读取的 toPlayer 应与 payload 中一致");

        Field cardField = GiveCommand.class.getDeclaredField("card");
        cardField.setAccessible(true);
        TreasureCardType actualCard = (TreasureCardType) cardField.get(cmd);
        assertEquals(expectedCard, actualCard, "反射读取的 card 应与 payload 中一致");
    }
}
