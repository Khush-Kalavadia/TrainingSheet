package com.java.concurrency;

public class RunnableConsumerBlockingQueue implements Runnable
{
    @Override
    public void run()
    {
        try
        {
            String element = null;

            while (true)
            {
                Thread.sleep(5000);

                element = ProducerConsumerUtil.takeValue();

                System.out.println(element + " consumed by "+Thread.currentThread().getName());
            }
        }
        catch (Exception ex)
        {
            System.out.println("Consumer was interrupted");
        }
    }
}
