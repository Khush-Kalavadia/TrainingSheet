package com.java.socket.programming.chatApplicationUDP;

import java.net.DatagramPacket;

import java.net.DatagramSocket;

import java.net.InetAddress;

public class Server
{
    private int serverPort;

    private InetAddress serverAddress;

    public Server(InetAddress address, int port)
    {
        this.serverPort = port;

        this.serverAddress = address;
    }

    public void start()
    {
        DatagramSocket server = null;

        DatagramPacket packet = null;

        DatagramPacket sendPacket = null;

        try
        {
            server = new DatagramSocket(serverPort, serverAddress);

            new Thread(new ServerWorkerRunnable(server)).start();

            byte[] receive;

            String receivedData;

            while (true)
            {
                receive = new byte[1024];

                packet = new DatagramPacket(receive, receive.length);

                server.receive(packet);

                receivedData = new String(packet.getData());

                ClientListUtil.add(MessageUtil.getSenderName(receivedData), packet);

                String str = MessageUtil.getReceiverName(receivedData);

                if (!str.equals(""))
                {
                    TaskHandlerUtil.addTask(receivedData);
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        try
        {
            new Server(InetAddress.getLocalHost(), 9000).start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
