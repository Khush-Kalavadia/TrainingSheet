package com.java.socket.programming.chatOneToOne;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client
{
    private DatagramSocket datagramSocket = null;

    InetAddress address = null;

    private int port = -1;

    public Client(DatagramSocket datagramSocket, InetAddress address, int port)
    {
        this.datagramSocket = datagramSocket;

        this.address = address;

        this.port = port;
    }

    public void startClient()
    {
        try
        {
            SendRunnable sendRunnable = new SendRunnable(datagramSocket, address, port);

            new Thread(sendRunnable).start();

            ReceiveRunnable receiveRunnable = new ReceiveRunnable(datagramSocket);

            new Thread(receiveRunnable).start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
