package com.java.socket.programming.chatApplication;

import java.io.IOException;

import java.net.InetAddress;

import java.net.Socket;

public class Client
{
    private int serverPort;

    private InetAddress serverAddress;

    private int clientPort;

    private InetAddress clientAddress;

    private String clientName;

    public Client(InetAddress clientAddress, int clientPort, String clientName, InetAddress serverAddress, int serverPort)
    {
        this.serverPort = serverPort;

        this.clientPort = clientPort;

        this.clientName = clientName;

        this.serverAddress = serverAddress;

        this.clientAddress = clientAddress;
    }

    void startClient()
    {
        try
        {
            new Thread(new ClientSenderRunnable(clientAddress, clientPort, clientName, serverAddress, serverPort)).start();

//            new Thread(new ClientReceiverRunnable(serverAddress, serverPort)).start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
