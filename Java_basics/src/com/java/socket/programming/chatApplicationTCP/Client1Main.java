package com.java.socket.programming.chatApplicationTCP;

import java.net.InetAddress;

public class Client1Main
{
    public static void main(String[] args)
    {
        try
        {
            Client client1 = new Client("client1", InetAddress.getLocalHost(), 9000);

            client1.startClient();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
