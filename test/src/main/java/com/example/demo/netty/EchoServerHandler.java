package com.example.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

//标志这类的实例之间可以在channel里面共享
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext context, Object message) {
        ByteBuf byteBuf = (ByteBuf) message;
        System.out.println("server received :" + byteBuf.toString(CharsetUtil.UTF_8));
        context.write(byteBuf); //将所接收的消息返回给发送者，此时还没有冲刷数据

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext context) {
        //冲刷所有待审信息到远程节点，关闭通道后，操作完成
        context.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable throwable) {
        context.fireExceptionCaught(throwable); //打印异常堆栈跟踪
        context.close();    //关闭通道
    }
}
