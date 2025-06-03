package edu.bdic.forbiddenisland.controller.commands;

import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateRoomCommandTest {

    @Test
    void testExecuteDoesNothing() {
        // 执行 execute 应当不抛异常，也不改变系统状态
        CreateRoomCommand cmd = new CreateRoomCommand();
        assertDoesNotThrow(cmd::execute, "execute 不应抛出任何异常");
    }

    @Test
    void testToMessageProducesCorrectMessage() {
        CreateRoomCommand cmd = new CreateRoomCommand();
        Message msg = cmd.toMessage();

        assertEquals(MessageType.CREATE_ROOM, msg.getType(), "MessageType 应为 CREATE_ROOM");
        assertEquals("", msg.getSessionId(), "sessionId 应为空字符串");
        assertEquals(0, msg.getPlayerId(), "playerId 应为 0");
        assertNull(msg.getPayload(), "payload 应为 null");
    }

    @Test
    void testFromMessageReturnsNewInstance() {
        Message dummy = new Message(MessageType.CREATE_ROOM, "irrelevant", 123, null);
        CreateRoomCommand c1 = CreateRoomCommand.fromMessage(dummy);
        CreateRoomCommand c2 = CreateRoomCommand.fromMessage(dummy);

        assertNotNull(c1, "fromMessage 不应返回 null");
        assertNotNull(c2, "fromMessage 不应返回 null");
        assertNotSame(c1, c2, "每次应返回不同的新实例");
        assertTrue(c1 instanceof CreateRoomCommand, "返回类型应为 CreateRoomCommand");
        assertTrue(c2 instanceof CreateRoomCommand, "返回类型应为 CreateRoomCommand");
    }
}
