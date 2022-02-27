package com.java.socket.programming.chatApplication;

import java.io.*;

import java.net.InetAddress;

import java.net.Socket;

public class ClientSenderRunnable implements Runnable
{
    private InetAddress clientAddress;

    private InetAddress serverAddress;

    private int clientPort;

    private int serverPort;

    private String clientName;

    public ClientSenderRunnable(InetAddress clientAddress, int clientPort, String clientName, InetAddress serverAddress, int serverPort)
    {
        this.clientAddress = clientAddress;

        this.clientPort = clientPort;

        this.clientName = clientName;

        this.serverAddress = serverAddress;

        this.serverPort = serverPort;
    }

    @Override
    public void run()
    {
        Socket socket = null;

        BufferedReader enter = null;

        BufferedWriter out = null;

        String receiverName;

        String message;

        try
        {
            socket = new Socket(serverAddress, serverPort);

            System.out.println("Connected to server: " + serverAddress + ", " + serverPort);

            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            out.write(MessageUtil.getNewClientData(clientName, clientAddress, clientPort));

            out.newLine();

            out.flush();

            new Thread(new ClientReceiverRunnable(socket)).start();

            while (true)
            {
                System.out.println("------Send message to------");

                System.out.print("Enter client name: ");

                enter = new BufferedReader(new InputStreamReader(System.in));

                receiverName = enter.readLine();

                System.out.print("Enter message: ");

                enter = new BufferedReader(new InputStreamReader(System.in));

                message = enter.readLine();

                out.write(MessageUtil.getDataToSend(clientName, receiverName, message));

                out.newLine();

                out.flush();

//                socket.close();
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
                if (enter != null)
                {
                    enter.close();
                }
                if (out != null)
                {
                    out.close();
                }
                if (socket != null)
                {
                    socket.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }
}
