package com.java.socket.programming.chatApplicationUDP;

import java.util.concurrent.BlockingQueue;

import java.util.concurrent.LinkedBlockingDeque;

public class TaskHandlerUtil
{
    static BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<>();

    public static void addTask(String data)
    {
        try
        {
            blockingQueue.put(data);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static String getTask()
    {
        try
        {
            return blockingQueue.take();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static void print()
    {
        System.out.println(blockingQueue);
    }
}
