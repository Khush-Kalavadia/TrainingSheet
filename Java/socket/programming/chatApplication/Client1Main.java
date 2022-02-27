package com.java.socket.programming.chatApplication;

import java.net.InetAddress;

public class Client1Main
{
    public static void main(String[] args)
    {
        try
        {
            Client client1 = new Client(InetAddress.getLocalHost(), 9001, "client1",
                    InetAddress.getLocalHost(), 9000);

            client1.startClient();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
