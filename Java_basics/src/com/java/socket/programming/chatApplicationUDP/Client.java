package com.java.socket.programming.chatApplicationUDP;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.net.DatagramPacket;

import java.net.DatagramSocket;

import java.net.InetAddress;

public class Client
{
    private String clientName;

    private InetAddress serverAddress;

    private int serverPort;

    public Client(String clientName, InetAddress serverAddress, int serverPort)
    {
        this.serverPort = serverPort;

        this.clientName = clientName;

        this.serverAddress = serverAddress;
    }

    public void start()
    {
        DatagramSocket clientSocket = null;

        DatagramPacket packet = null;

        BufferedReader enter = null;

        try
        {
            clientSocket = new DatagramSocket();

            String newUser = clientName + ">#";

            byte[] sendBytes = newUser.getBytes();

            packet = new DatagramPacket(sendBytes, sendBytes.length, serverAddress, serverPort);

            clientSocket.send(packet);

            new Thread(new ClientReceiverRunnable(clientSocket)).start();

            System.out.println(clientName + " started on port " + clientSocket.getLocalPort());

            System.out.println("Enter in format: <username> > <message>");

            System.out.println("---------------------------------------");

            while (true)
            {
                enter = new BufferedReader(new InputStreamReader(System.in));

                String stringSend = MessageUtil.getDataToSend(clientName, enter.readLine());

                sendBytes = stringSend.getBytes();

                packet = new DatagramPacket(sendBytes, sendBytes.length, serverAddress, serverPort);

                clientSocket.send(packet);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}

