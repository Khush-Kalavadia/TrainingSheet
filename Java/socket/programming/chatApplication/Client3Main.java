package com.java.socket.programming.chatApplication;

import java.net.InetAddress;

public class Client3Main
{
    public static void main(String[] args)
    {
        try
        {
            Client client3 = new Client("client3",InetAddress.getLocalHost(), 9000);

            client3.startClient();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
