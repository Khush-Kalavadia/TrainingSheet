package com.java.concurrency;

public class RunnableProducerBlockingQueue implements Runnable
{
    @Override
    public void run()
    {
        try
        {
            long timeMillis = 0;

            while (true)
            {
                timeMillis = System.currentTimeMillis();

                ProducerConsumerUtil.putValue("" + timeMillis);

                System.out.println(timeMillis + " inserted");

                Thread.sleep(500);
            }

        }
        catch (Exception ex)
        {
            System.out.println("Producer was interrupted.");
        }
    }
}
