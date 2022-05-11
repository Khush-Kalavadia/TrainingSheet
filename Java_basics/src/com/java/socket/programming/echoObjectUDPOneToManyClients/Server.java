package com.java.socket.programming.echoObjectUDPOneToManyClients;

import com.java.concurrency.ReadWriteLockMain;

import java.net.DatagramPacket;

import java.net.DatagramSocket;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Server
{
    public static void main(String[] args)
    {
        DatagramSocket datagramSocket = null;

        int port = 9002;

        DatagramPacket packet;

        try
        {
            datagramSocket = new DatagramSocket(port);

            byte[] data = new byte[1024];

            System.out.println("Server Created");

            ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

            while (true)
            {
                packet = new DatagramPacket(data, data.length);

                datagramSocket.receive(packet);

                ClientHandler clientHandler = new ClientHandler(datagramSocket, packet);

                //over here i am creating separate thread for each client and hence the resources are not shared
                //hence, we do not require to use a lock
                //ClientHandler clientHandler = new ClientHandler(datagramSocket, packet, readWriteLock);

                (new Thread(clientHandler)).start();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (datagramSocket != null)
            {
                datagramSocket.close();
            }
        }
    }
}
