package com.renguangli.gateway;

import com.renguangli.gateway.endpoint.HttpServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * GatewayServer
 *
 * @author renguangli 2018/11/7 14:35
 * @since JDK 1.8
 */
public class GatewayServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayServer.class);

    private int port = Configuration.intValue(ConfigConstants.GATEWAY_SERVER_PORT, 8088);

    private EventLoopGroup bossGroup = new NioEventLoopGroup();

    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    public void start() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
            .localAddress(this.port)
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast("decoder", new HttpRequestDecoder());// http 解码器
                    pipeline.addLast("encoder", new HttpResponseEncoder()); // http 编码器
                    pipeline.addLast("compressor", new HttpContentCompressor());// http 压缩 gzip，deflate
                    pipeline.addLast("aggregator", new HttpObjectAggregator(512 * 1024));
                    pipeline.addLast("service", new HttpServerHandler());
                }
            });
        try {
            ChannelFuture channelFuture = bootstrap.bind().sync();
            LOGGER.info("Http Server is started. The liston port is : http://{}:{}", Inet4Address.getLocalHost().getHostAddress(), this.port);
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException | UnknownHostException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully().sync();
            workerGroup.shutdownGracefully().sync();
        }
    }

}
