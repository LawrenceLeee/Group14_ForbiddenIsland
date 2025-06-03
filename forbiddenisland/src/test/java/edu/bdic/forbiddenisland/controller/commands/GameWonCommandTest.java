package edu.bdic.forbiddenisland.controller.commands;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameWonCommandTest {

    @BeforeEach
    void setUp() {
        // 每次测试前重置 SessionManager
        SessionManager.getInstance().clear();
    }

    @Test
    void testExecuteDoesNotThrow() {
        GameWonCommand cmd = new GameWonCommand();
        // execute 仅打印，不应抛异常
        assertDoesNotThrow(cmd::execute, "execute 不应抛出任何异常");
    }

    @Test
    void testToMessageIncludesSessionManagerValues() {
        // 设置 SessionManager
        SessionManager.getInstance().setSession("sess200", 20, false);

        GameWonCommand cmd = new GameWonCommand();
        Message msg = cmd.toMessage();

        assertEquals(MessageType.GAME_WON, msg.getType(), "消息类型应为 GAME_WON");
        assertEquals("sess200", msg.getSessionId(), "sessionId 应来自 SessionManager");
        assertEquals(20, msg.getPlayerId(), "playerId 应来自 SessionManager");
        assertNotNull(msg.getPayload(), "payload 不应为 null");
        assertTrue(msg.getPayload() instanceof ObjectNode, "payload 应为 ObjectNode");
        ObjectNode node = (ObjectNode) msg.getPayload();
        assertTrue(node.isEmpty(), "payload ObjectNode 应为空");
    }

    @Test
    void testFromMessageReturnsNewInstances() {
        Message dummy = new Message(
                MessageType.GAME_WON,
                "irrelevant",
                0,
                JsonNodeFactory.instance.objectNode()
        );
        GameWonCommand c1 = GameWonCommand.fromMessage(dummy);
        GameWonCommand c2 = GameWonCommand.fromMessage(dummy);

        assertNotNull(c1, "fromMessage 不应返回 null");
        assertNotNull(c2, "fromMessage 不应返回 null");
        assertNotSame(c1, c2, "每次调用应返回不同的新实例");
    }
}
