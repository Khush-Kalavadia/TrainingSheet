package com.java.socket.programming.chatApplicationUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerWorkerRunnable implements Runnable
{
    DatagramSocket server;

    ServerWorkerRunnable(DatagramSocket server)
    {
        this.server = server;
    }

    @Override
    public void run()
    {
        DatagramPacket clientPacket = null;

        try
        {
            while (true)
            {
                String task = TaskHandlerUtil.getTask();

                String receiverName = MessageUtil.getReceiverName(task);

                clientPacket = ClientListUtil.getPacket(receiverName);

                if (ClientListUtil.containsUsername(receiverName))
                {
                    byte[] taskBytes = task.getBytes();

                    DatagramPacket packet = new DatagramPacket(taskBytes, taskBytes.length,
                            clientPacket.getAddress(), clientPacket.getPort());

                    server.send(packet);
                }
                else
                {
                    TaskHandlerUtil.addTask(task);
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
