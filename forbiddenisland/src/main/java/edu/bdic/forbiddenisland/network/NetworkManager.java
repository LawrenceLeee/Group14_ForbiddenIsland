package edu.bdic.forbiddenisland.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.util.function.Consumer;


public class NetworkManager {
    private static final NetworkManager INST = new NetworkManager(); // 单例模式, 网路管理员
    private Channel channel; //
    private Consumer<Message> callback; // 消费者接口, 帮助对象执行操作

    private NetworkManager() {}

    public static NetworkManager getInstance() {
        return INST;
    }

    public void connect(String host, int port, Consumer<Message> onMessage) {
        this.callback = onMessage;
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline()
                                .addLast(new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 4))
                                .addLast(new LengthFieldPrepender(4))
                                .addLast(new StringDecoder(CharsetUtil.UTF_8))
                                .addLast(new StringEncoder(CharsetUtil.UTF_8))
                                .addLast(new ClientHandler(callback));
                    }
                });
        b.connect(host, port).addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                channel = future.channel();
                System.out.println("✔ Connected");
            } else {
                System.err.println("✖ Connect failed: " + future.cause());
            }
        });
    }

    public void send(Message msg) {
        String json = JsonUtil.toJson(msg);
        System.out.println("[NetworkManager] → SENDING JSON: " + json);
        channel.writeAndFlush(json);
    }



    public void disconnect() {
        if (channel != null) channel.close();
    }
}
