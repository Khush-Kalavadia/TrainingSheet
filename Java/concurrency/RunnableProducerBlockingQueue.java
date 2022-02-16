package com.java.concurrency;

import java.util.concurrent.BlockingQueue;

public class RunnableProducerBlockingQueue implements Runnable
{

    BlockingQueue<String> blockingQueue = null;

    public RunnableProducerBlockingQueue(BlockingQueue<String> blockingQueue)
    {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run()
    {
        try
        {
            long timeMillis = 0;

            while (true)
            {
                timeMillis = System.currentTimeMillis();

                this.blockingQueue.put("" + timeMillis);

                Thread.sleep(1000);
            }

        }
        catch (Exception ex)
        {
            System.out.println("Producer was interrupted.");
        }
    }
}
