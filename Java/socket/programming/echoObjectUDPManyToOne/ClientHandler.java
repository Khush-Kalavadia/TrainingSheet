package com.java.socket.programming.echoObjectUDPManyToOne;

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
            //Over here, this is taking place parallelly so you can also have a delay to send your
            //packet back. Even during the time of delay the server would be accepting packets.

            datagramSocket.send(packet);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
