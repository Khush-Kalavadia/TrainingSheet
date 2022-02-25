package com.java.socket.programming.echoObjectUDPManyToOne;

import java.net.DatagramPacket;

import java.net.DatagramSocket;

public class Server
{
    public static void main(String[] args)
    {
        DatagramSocket datagramSocket = null;

        int port = 9002;

        DatagramPacket packet;

        try
        {
            datagramSocket = new DatagramSocket(port);

            byte[] data = new byte[1024];

            System.out.println("Server Created");

            while (true)
            {
                packet = new DatagramPacket(data, data.length);

                datagramSocket.receive(packet);

                ClientHandler clientHandler = new ClientHandler(datagramSocket, packet);

                (new Thread(clientHandler)).start();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
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
