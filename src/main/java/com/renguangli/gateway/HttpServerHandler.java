package com.renguangli.gateway;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



/**
 * HttpServerHandler
 *
 * @author renguangli 2018/11/7 11:52
 * @since JDK 1.8
 */

@Service
@ChannelHandler.Sharable
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServerHandler.class);

    private List<Filter> filters = new ArrayList<>();

    public void addFilter(Filter filter) {
        LOGGER.info("Adding filter:{}", filter.getClass().getSimpleName());
        filters.add(filter);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        long start = System.currentTimeMillis();
        // 构建HTTP响应
        FullHttpResponse response = new DefaultFullHttpResponse(request.protocolVersion(), HttpResponseStatus.OK);
        // 执行过滤器链
        for (Filter filter : filters) {
            if (!filter.filter(request, response)) {
                break;
            }
        }
        System.out.println(System.currentTimeMillis() - start);
        ctx.writeAndFlush(response);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
