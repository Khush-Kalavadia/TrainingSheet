package com.java.socket.programming.chatApplicationUDP;

import java.net.InetAddress;

public class Client1Main
{
    public static void main(String[] args)
    {
        try
        {
            new Client("client1", InetAddress.getLocalHost(), 9000).start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
