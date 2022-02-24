package com.java.socket.programming.echoUDPManyToOne;

import java.net.DatagramPacket;

import java.net.DatagramSocket;

public class ClientHandler implements Runnable
{
    private DatagramSocket datagramSocket = null;

    private DatagramPacket packet = null;

    public ClientHandler(DatagramSocket datagramSocket, DatagramPacket packet)
    {
        this.datagramSocket = datagramSocket;

        this.packet = packet;
    }

    @Override
    public void run()
    {
        try
        {
            datagramSocket.send(packet);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
