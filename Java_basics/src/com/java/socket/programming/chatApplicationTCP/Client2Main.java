package com.java.socket.programming.chatApplicationTCP;

import java.net.InetAddress;

public class Client2Main
{
    public static void main(String[] args)
    {
        try
        {
            Client client2 = new Client("client2",InetAddress.getLocalHost(), 9000);

            client2.startClient();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
