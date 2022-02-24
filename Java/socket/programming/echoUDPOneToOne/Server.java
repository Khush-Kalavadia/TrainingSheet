package com.java.socket.programming.echoUDPOneToOne;

import java.net.DatagramPacket;

import java.net.DatagramSocket;

import java.net.InetAddress;
import java.util.Arrays;

public class Server
{
    public static void main(String[] args)
    {
        DatagramSocket datagramSocket = null;

        DatagramPacket receivedPacket = null;

        DatagramPacket sentPacket = null;

        int port = 9999;

        try
        {
            datagramSocket = new DatagramSocket(port);

            byte[] receivedData = new byte[1024];

            receivedPacket = new DatagramPacket(receivedData, receivedData.length);

            System.out.println("Server started");

            datagramSocket.receive(receivedPacket);

            System.out.println("Packet received");

            String receivedString = new String(receivedPacket.getData());

            System.out.println("receivedString: " + receivedString);

            byte[] sentData = receivedString.getBytes();

            //Once we receive the packet we can use it's address and port to send the data
            sentPacket = new DatagramPacket(sentData, sentData.length, receivedPacket.getAddress(), receivedPacket.getPort());

            datagramSocket.send(sentPacket);

            System.out.println("Data sent");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (datagramSocket != null)
            {
                datagramSocket.close();
            }
        }
    }
}
