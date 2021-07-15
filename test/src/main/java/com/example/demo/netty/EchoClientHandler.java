package com.example.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        //打印收到的信息
        System.out.println("client received：" + byteBuf.toString(CharsetUtil.UTF_8));

    }

    @Override
    public void channelActive(ChannelHandlerContext context) {
        //当被通知该channel是活动的时候就发送消息
        context.writeAndFlush(Unpooled.copiedBuffer("Netty rocks！", CharsetUtil.UTF_8));
    }

    public void exceptionCaught(ChannelHandlerContext context, Throwable throwable) {
        //打印异常堆栈日志
        throwable.printStackTrace();
        //关闭通道
        context.close();
    }
}
