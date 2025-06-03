package edu.bdic.forbiddenisland.network;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.channel.Channel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class NetworkManagerTest {

    private NetworkManager manager;
    private Channel mockChannel;

    @BeforeEach
    void setUp() throws Exception {
        // 获取单例实例
        manager = NetworkManager.getInstance();
        // 利用反射将私有 channel 字段替换成一个 mock
        mockChannel = mock(Channel.class);
        Field channelField = NetworkManager.class.getDeclaredField("channel");
        channelField.setAccessible(true);
        channelField.set(manager, mockChannel);

        // callback 不影响 send/disconnect 测试，但避免空指针
        Field callbackField = NetworkManager.class.getDeclaredField("callback");
        callbackField.setAccessible(true);
        callbackField.set(manager, (Consumer<Message>) msg -> {});
    }

    @Test
    void testSendInvokesChannelWriteAndFlush() {
        // 构造一个带 payload 的 Message
        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("testKey", "testValue");
        Message msg = new Message(
                MessageType.MOVE,
                "sessionX",
                9,
                payload
        );

        // 调用 send
        manager.send(msg);

        // 验证 toJson 生成的字符串被写入并刷新
        String expectedJson = JsonUtil.toJson(msg);
        verify(mockChannel, times(1)).writeAndFlush(eq(expectedJson));
    }

    @Test
    void testDisconnectClosesChannel() {
        // 调用 disconnect
        manager.disconnect();

        // 验证 channel.close() 被调用
        verify(mockChannel, times(1)).close();
    }
}
