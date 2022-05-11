package com.java.concurrency;

import java.util.concurrent.ArrayBlockingQueue;

import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerUtil
{
//    private static final LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();

    private static final ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
    //using ArrayBlockingQueue just to understand its working and how threads wait.

    public static void putValue(String value)
    {
        try
        {
            blockingQueue.put(value);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static String takeValue()
    {
        try
        {
            return blockingQueue.take();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
