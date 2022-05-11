package com.java.socket.programming.multicast;

import com.java.concurrency.TimeUtil;

import java.net.*;

public class Server
{
    public static void main(String[] args)
    {
        try
        {
            try
            {
                DatagramSocket socket = new DatagramSocket(4000);

                System.out.println(socket);

                String input;

                byte[] send;

                InetAddress group = InetAddress.getByName("230.0.0.1");

                int sendPort = 4001;

                DatagramPacket packet;

                while (true)
                {
                    input = TimeUtil.showTime();

                    send = input.getBytes();

                    packet = new DatagramPacket(send, send.length, group, sendPort);

                    socket.send(packet);

                    Thread.sleep(2000);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
