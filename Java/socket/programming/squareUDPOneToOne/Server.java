package com.java.socket.programming.squareUDPOneToOne;

import java.net.*;

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

            int square = Integer.parseInt(receivedString.trim());

            square = (int) Math.pow(square, 2);

            byte[] sentData = (square + "").getBytes();

//            System.out.println(receivedPacket.getSocketAddress());

            sentPacket = new DatagramPacket(sentData, sentData.length, InetAddress.getLocalHost(), receivedPacket.getPort());

            datagramSocket.send(sentPacket);

            System.out.println("Square sent");
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
