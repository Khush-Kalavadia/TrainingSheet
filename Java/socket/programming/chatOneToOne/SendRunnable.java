package com.java.socket.programming.chatOneToOne;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class SendRunnable implements Runnable
{
    private DatagramSocket datagramSocket = null;

    private InetAddress address = null;

    private int port = -1;

    public SendRunnable(DatagramSocket datagramSocket, InetAddress address, int port)
    {
        this.datagramSocket = datagramSocket;

        this.address = address;

        this.port = port;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
//                System.out.println("Enter text: ");

                Scanner in = new Scanner(System.in);

                byte[] sendText = in.nextLine().getBytes();

                DatagramPacket datagramPacket = new DatagramPacket(sendText, sendText.length, address, port);

                datagramSocket.send(datagramPacket);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            datagramSocket.close();
        }
    }
}
