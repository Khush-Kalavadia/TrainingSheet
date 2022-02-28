package com.java.socket.programming.chatApplication;

import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.OutputStreamWriter;

import java.net.Socket;

public class ServerInputFromClientRunnable implements Runnable
{
    BufferedReader in;

    ServerInputFromClientRunnable(BufferedReader in)
    {
        this.in = in;
    }

    @Override
    public void run()
    {
        String receivedData;

        Socket receiverSocket = null;

        BufferedWriter out = null;

        try
        {
            while (true)
            {
                receivedData = in.readLine();

                if (ClientListUtil.containsUsername(MessageUtil.getReceiverName(receivedData)))
                {
                    receiverSocket = ClientListUtil.getSocket(MessageUtil.getReceiverName(receivedData));

                    out = new BufferedWriter(new OutputStreamWriter(receiverSocket.getOutputStream()));

                    out.write(receivedData);

                    out.newLine();

                    out.flush();

//                    out.close();                      //should not close this else socket will be closed

//                    receiverSocket.close();           //should not close this
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
                if (in != null)
                {
                    in.close();
                }
                if (receiverSocket != null)
                {
                    receiverSocket.close();
                }
                if (out != null)
                {
                    out.close();
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

    }
}
