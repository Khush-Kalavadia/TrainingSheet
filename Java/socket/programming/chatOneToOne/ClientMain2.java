package com.java.socket.programming.chatOneToOne;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientMain2
{
    public static void main(String[] args)
    {
        try
        {
            Client client = new Client(new DatagramSocket(9112), InetAddress.getLocalHost(), 9111);

            client.startClient();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
