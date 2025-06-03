package edu.bdic.forbiddenisland.network.handler;

import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class MessageHandlerTest {

    @Test
    void testHandleCalledWithCorrectMessage() {
        AtomicBoolean called = new AtomicBoolean(false);

        // 构造一个简单的 Message
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("dummy", 123);
        Message testMessage = new Message(
                MessageType.MOVE,
                "sessTest",
                5,
                payload
        );

        // 使用匿名类实现 MessageHandler，将调用标记为 true
        MessageHandler handler = new MessageHandler() {
            @Override
            public void handle(Message message) {
                assertSame(testMessage, message, "handle 方法中收到的 Message 应与传入保持同一实例");
                called.set(true);
            }
        };

        // 调用 handle
        assertFalse(called.get(), "调用前 shouldNotBeCalled");
        handler.handle(testMessage);
        assertTrue(called.get(), "调用后 called 应为 true");
    }

    @Test
    void testMultipleCallsToggleFlag() {
        AtomicBoolean firstCalled = new AtomicBoolean(false);
        AtomicBoolean secondCalled = new AtomicBoolean(false);

        // 构造两个不同的 Message
        Message msg1 = new Message(MessageType.SHORE_UP, "s1", 1, null);
        Message msg2 = new Message(MessageType.CAPTURE, "s2", 2, null);

        // 实现一个 MessageHandler，根据 MessageType 设置不同标志
        MessageHandler handler = new MessageHandler() {
            @Override
            public void handle(Message message) {
                if (message.getType() == MessageType.SHORE_UP) {
                    firstCalled.set(true);
                } else if (message.getType() == MessageType.CAPTURE) {
                    secondCalled.set(true);
                }
            }
        };

        // 调用两次
        handler.handle(msg1);
        assertTrue(firstCalled.get(), "收到 SHORE_UP 后 firstCalled 应为 true");
        assertFalse(secondCalled.get(), "尚未收到 CAPTURE，secondCalled 应为 false");

        handler.handle(msg2);
        assertTrue(secondCalled.get(), "收到 CAPTURE 后 secondCalled 应为 true");
    }
}
