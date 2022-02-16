package com.java.concurrency;

import java.util.concurrent.ArrayBlockingQueue;

import java.util.concurrent.BlockingQueue;

public class BlockingQueueProducerConsumer
{
    public static void main(String[] args)
    {
        try
        {
            BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);

            RunnableProducerBlockingQueue producer = new RunnableProducerBlockingQueue(blockingQueue);

            RunnableConsumerBlockingQueue consumer = new RunnableConsumerBlockingQueue(blockingQueue);

            Thread thread1 = new Thread(producer);

            Thread thread2 = new Thread(consumer);

            thread1.start();

            thread2.start();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
