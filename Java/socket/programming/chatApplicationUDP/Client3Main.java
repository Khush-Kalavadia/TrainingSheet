package com.java.socket.programming.chatApplicationUDP;

import java.net.InetAddress;

public class Client3Main
{
        public static void main(String[] args)
        {
            try
            {
                new Client("client3", InetAddress.getLocalHost(), 9000).start();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
}
