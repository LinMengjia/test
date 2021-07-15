package com.example.demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public static void main(String[] args) throws InterruptedException {
        final String host = "localhost";
        final int port = 8990;
        new EchoClient(host, port).start();
    }


    public void start() throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            //创建Bootstrap
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup) //指定EventLoopGroup来处理客户端事件，由于我们NIO传输，所以用到了NioEventLoopGroup的实现
                    .channel(NioSocketChannel.class)    //使用的channel类型是一个用于NIO传输，也可以使用和服务器不一样的类型
                    .remoteAddress(new InetSocketAddress(host, port))   //设置服务器的InetSocketAddress
                    //当建立一个连接和一个新的通道时，创建添加到EchoClientHandler实例到ChannelPipeline
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            //连接到远程，等待连接完成
            ChannelFuture channelFuture = bootstrap.connect().sync();
            //阻塞直到Channel关闭
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //调用shutdownGracefully来关闭线程池和释放所有资源
            eventLoopGroup.shutdownGracefully().sync();
        }
    }
}
