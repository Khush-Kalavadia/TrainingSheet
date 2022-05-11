package com.java.socket.programming.multicast;

import java.net.DatagramPacket;

import java.net.InetAddress;

import java.net.MulticastSocket;

public class Client1NonMulticast     //joined on different ip address hence, won't receive multicast msg
{
    public static void main(String[] args)
    {
        try
        {
            MulticastSocket multicastSocket = new MulticastSocket(4002);

//            MulticastSocket multicastSocket = new MulticastSocket();

            System.out.println(multicastSocket);

            InetAddress address = InetAddress.getByName("230.0.0.2");       //ip of the multicast ip

            multicastSocket.joinGroup(address);

            byte[] buf = new byte[1024];

            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            while (true)
            {
                multicastSocket.receive(packet);

                String received = new String(packet.getData());

                System.out.println("Received: " + received);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
