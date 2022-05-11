package com.java.socket.programming.echoObjectUDPOneToManyClients;

import java.net.DatagramPacket;

import java.net.DatagramSocket;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

public class ClientHandler implements Runnable
{
    private DatagramSocket datagramSocket = null;

    private DatagramPacket packet = null;

//    private ReadWriteLock readWriteLock;

    public ClientHandler(DatagramSocket datagramSocket, DatagramPacket packet)
    {
        this.datagramSocket = datagramSocket;

        this.packet = packet;

//        this.readWriteLock = readWriteLock;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
//                if (readWriteLock.writeLock().tryLock())        //should we use trylock(100, TimeUnit.MILLISECONDS)
//                {
                    datagramSocket.send(packet);

//                    readWriteLock.writeLock().unlock();

//                    break;
//                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
