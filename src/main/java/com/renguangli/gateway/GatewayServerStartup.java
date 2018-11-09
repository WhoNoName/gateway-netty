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
 * GatewayServerStartup
 *
 * @author renguangli 2018/11/7 14:35
 * @since JDK 1.8
 */
public class GatewayServerStartup {

    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayServerStartup.class);

    private EventLoopGroup bossGroup = new NioEventLoopGroup();

    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    public void start(int port) throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
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
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            LOGGER.info("Http Server is started. The liston port is : http://{}:{}", Inet4Address.getLocalHost().getHostAddress(), port);
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException | UnknownHostException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully().sync();
            workerGroup.shutdownGracefully().sync();
        }
    }

}
