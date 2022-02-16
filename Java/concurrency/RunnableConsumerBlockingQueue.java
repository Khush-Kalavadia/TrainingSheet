package com.java.concurrency;

import java.util.concurrent.BlockingQueue;

public class RunnableConsumerBlockingQueue implements Runnable
{
    BlockingQueue<String> blockingQueue = null;

    public RunnableConsumerBlockingQueue(BlockingQueue blockingQueue)
    {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run()
    {
        try
        {
            String element = null;

            while (true)
            {
                element = this.blockingQueue.take();

                System.out.println("consumed: " + element);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Consumer was interrupted");
        }
    }
}
