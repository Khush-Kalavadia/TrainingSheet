package com.java.socket.programming.chatApplication;

import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.OutputStreamWriter;

import java.net.Socket;

public class ServerClientInputRunnable implements Runnable
{
    BufferedReader in = null;

    ServerClientInputRunnable(BufferedReader in)
    {
        this.in = in;
    }

    @Override
    public void run()
    {
        try
        {
            String receivedData;

            Socket receiverSocket;

            BufferedWriter out;

            while (true)
            {
                receivedData = in.readLine();

                System.out.println(receivedData);

                if (ClientListUtil.containsUsername(MessageUtil.getReceiverName(receivedData)))
                {
                    System.out.println(MessageUtil.getReceiverName(receivedData));

                    receiverSocket = ClientListUtil.getSocket(MessageUtil.getReceiverName(receivedData));

                    System.out.println(receiverSocket);

//                    System.out.println(receiverSocket.getInetAddress());
//                    System.out.println(receiverSocket.getPort());

//                    receiverSocket = new Socket(receiverSocket.getInetAddress(), receiverSocket.getPort());

                    out = new BufferedWriter(new OutputStreamWriter(receiverSocket.getOutputStream()));

                    out.write(receivedData);

                    out.newLine();

                    out.flush();

                    out.close();

                    receiverSocket.close();
                }
                else
                {
                    System.out.println("User not found: " + MessageUtil.getReceiverName(receivedData));
                }
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
                in.close();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

    }
}
