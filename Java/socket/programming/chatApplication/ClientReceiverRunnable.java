package com.java.socket.programming.chatApplication;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.net.Socket;

public class ClientReceiverRunnable implements Runnable
{
    private Socket socket;

    ClientReceiverRunnable(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        BufferedReader in = null;

        String receivedData;

        try
        {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true)
            {
                receivedData = in.readLine();

                System.out.println(MessageUtil.getSenderName(receivedData) + " sends -> " + MessageUtil.getMessage(receivedData));
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
