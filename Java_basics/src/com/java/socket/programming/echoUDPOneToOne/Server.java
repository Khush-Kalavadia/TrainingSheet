package com.java.socket.programming.echoUDPOneToOne;

import java.net.DatagramPacket;

import java.net.DatagramSocket;

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

            System.out.println("Server started");

            System.out.println("Socket is connected to " + datagramSocket.getInetAddress());

            System.out.println("Socket is bound on address " + datagramSocket.getLocalAddress());

            System.out.println("Port number on local host to which socket is bound " + datagramSocket.getLocalPort());

            byte[] receivedData = new byte[1024];

            receivedPacket = new DatagramPacket(receivedData, receivedData.length);

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
