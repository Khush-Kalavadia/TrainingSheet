package com.java.socket.programming.chatApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

import java.net.Socket;

public class ClientReceiverRunnable implements Runnable
{
//    private InetAddress serverAddress;
//
//    private int serverPort;

    Socket socket;

    ClientReceiverRunnable(Socket socket)
    {
//        this.serverAddress = serverAddress;
//
//        this.serverPort = serverPort;
        this.socket = socket;
    }

    @Override
    public void run()
    {
        BufferedReader in = null;

        String receivedData;

        try
        {
            while (true)
            {
//                socket = new Socket(serverAddress.getHostAddress(), serverPort);

                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                receivedData = in.readLine();

                System.out.println(receivedData);

//                System.out.println(MessageUtil.getSenderName(receivedData));
//
//                System.out.println(MessageUtil.getReceiverName(receivedData));
//
//                System.out.println(MessageUtil.getMessage(receivedData));

//                System.out.println(MessageUtil.getSenderName(receivedData) + ">" +MessageUtil.getMessage(receivedData));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
                if (socket != null)
                {
                    socket.close();
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

    }
}
