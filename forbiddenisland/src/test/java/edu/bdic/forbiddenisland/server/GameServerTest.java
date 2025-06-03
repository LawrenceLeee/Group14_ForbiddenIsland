package edu.bdic.forbiddenisland.server;

import io.netty.channel.ChannelPipeline;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameServerTest {

    @Test
    void testPipelineInitialization() {
        // 1. 创建一个 EmbeddedChannel，不传入 ChannelInitializer
        EmbeddedChannel embedded = new EmbeddedChannel();

        // 2. 手动将与 GameServer 中相同的 handler 顺序添加到 pipeline
        ChannelPipeline p = embedded.pipeline();
        p.addLast("frameDecoder",    new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 4));
        p.addLast("framePrepender",  new LengthFieldPrepender(4));
        p.addLast("stringDecoder",   new StringDecoder(CharsetUtil.UTF_8));
        p.addLast("stringEncoder",   new StringEncoder(CharsetUtil.UTF_8));
        p.addLast("serverHandler",   new ServerHandler());

        // 3. 依次断言 pipeline 中各 handler 的存在与类型
        assertNotNull(p.get("frameDecoder"),
                "应该存在名为 frameDecoder 的 LengthFieldBasedFrameDecoder");
        assertTrue(p.get("frameDecoder") instanceof LengthFieldBasedFrameDecoder,
                "frameDecoder 应是 LengthFieldBasedFrameDecoder");

        assertNotNull(p.get("framePrepender"),
                "应该存在名为 framePrepender 的 LengthFieldPrepender");
        assertTrue(p.get("framePrepender") instanceof LengthFieldPrepender,
                "framePrepender 应是 LengthFieldPrepender");

        assertNotNull(p.get("stringDecoder"),
                "应该存在名为 stringDecoder 的 StringDecoder");
        assertTrue(p.get("stringDecoder") instanceof StringDecoder,
                "stringDecoder 应是 StringDecoder");

        assertNotNull(p.get("stringEncoder"),
                "应该存在名为 stringEncoder 的 StringEncoder");
        assertTrue(p.get("stringEncoder") instanceof StringEncoder,
                "stringEncoder 应是 StringEncoder");

        assertNotNull(p.get("serverHandler"),
                "应该存在名为 serverHandler 的 ServerHandler");
        assertTrue(p.get("serverHandler") instanceof ServerHandler,
                "serverHandler 应是 ServerHandler");

        // 4. 关闭 EmbeddedChannel
        embedded.close();
    }
}
