package com.java.socket.programming.serverBootstrap;

import io.netty.bootstrap.ServerBootstrap;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import io.netty.channel.ChannelInitializer;

import io.netty.channel.EventLoopGroup;

import io.netty.channel.nio.NioEventLoopGroup;

import io.netty.channel.socket.SocketChannel;

import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class Server
{
    public static void main(String[] args)
    {
        //EventLoopGroup -> eventloop -> serverbootstrap group -> channel -> localaddress -> childhandler

        EventLoopGroup group = new NioEventLoopGroup();

        try
        {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(group);

            serverBootstrap.channel(NioServerSocketChannel.class);

            serverBootstrap.localAddress(new InetSocketAddress("localhost", 9999));

            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>()
            {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception
                {
                    //how to add it as event handler as it is initilizer it would add only one
                    socketChannel.pipeline().addFirst(new StartServerHandler());

                    socketChannel.pipeline().addLast(new HelloServerHandler());
                }
            });

            ChannelFuture channelFuture = serverBootstrap.bind().sync();

            channelFuture.channel().closeFuture().sync();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                group.shutdownGracefully().sync();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }
}
