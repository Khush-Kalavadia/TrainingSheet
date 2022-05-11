package com.java.socket.programming.chatOneToOne;

import java.net.DatagramSocket;

import java.net.InetAddress;

public class Client1Main
{
    public static void main(String[] args)
    {
        try
        {
            Client client = new Client(new DatagramSocket(9111), InetAddress.getLocalHost(), 9112);

            client.startClient();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
