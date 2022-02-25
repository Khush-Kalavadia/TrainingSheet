package com.java.socket.programming.chatOneToOne;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveRunnable implements Runnable
{
    private DatagramSocket datagramSocket = null;

    public ReceiveRunnable(DatagramSocket datagramSocket)
    {
        this.datagramSocket = datagramSocket;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                byte[] receive = new byte[1024];

                DatagramPacket datagramPacket = new DatagramPacket(receive, receive.length);

                datagramSocket.receive(datagramPacket);

                String str = new String(datagramPacket.getData());

                System.out.println(">>" + str);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
