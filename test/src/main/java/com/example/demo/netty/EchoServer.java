package com.example.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8990;    //设置端口值
        new EchoServer(port).start();   //启动服务
    }

    public void start() throws InterruptedException {
        //创建EventLoopGroup，一个线程
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(eventLoopGroup)   //创建ServerBootGroup，此处也可放入多个EventLoopGroup
                    .channel(NioServerSocketChannel.class)  //指定使用的NIO的传输channel，指定通道类型
                    .localAddress(new InetSocketAddress(port))  //设置socket地址使用的端口
                    //当有一个新的连接被接受，一个新的子channel将被创建，ChannelInitializer添加EchoServerHandler到Channel的ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            //绑定的服务器，sync等待服务器关闭，调用sync()的原因是当前线程阻塞
            ChannelFuture future = serverBootstrap.bind().sync();
            System.out.println(EchoServer.class.getName() + " started and listen on " + future.channel().localAddress());
            future.channel().closeFuture().sync();  //关闭channel和块
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully().sync(); //关闭EventLoopGroup，释放所有资源
        }
    }
}
