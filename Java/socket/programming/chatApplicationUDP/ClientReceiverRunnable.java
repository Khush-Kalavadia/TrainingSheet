package com.java.socket.programming.chatApplicationUDP;

import java.net.DatagramPacket;

import java.net.DatagramSocket;

public class ClientReceiverRunnable implements Runnable
{
    DatagramSocket clientSocket = null;

    ClientReceiverRunnable(DatagramSocket clientSocket)
    {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run()
    {
        DatagramPacket packet = null;

        try
        {
            byte[] receive;

            String message;

            while (true)
            {
                receive = new byte[1024];

                packet = new DatagramPacket(receive, receive.length);

                clientSocket.receive(packet);

                message = new String(packet.getData());

                System.out.println(MessageUtil.getSenderName(message) + " sends -> " + MessageUtil.getMessage(message));
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
