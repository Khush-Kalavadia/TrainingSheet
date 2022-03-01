package com.java.socket.programming.chatApplicationUDP;

import java.net.InetAddress;

public class Client2Main
{
    public static void main(String[] args)
    {
        try
        {
            new Client("client2", InetAddress.getLocalHost(), 9000).start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
