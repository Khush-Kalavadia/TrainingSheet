package com.java.concurrency;

import java.util.concurrent.ArrayBlockingQueue;

import java.util.concurrent.BlockingQueue;

public class ProducerConsumerMain
{
    public static void main(String[] args)
    {
        try
        {
            //BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
            //GOOD PRACTICE: Create a util class rather than passing a blockingQueue and putting/taking separately.
            // As capacity is set to 3. Producer will wait for any consumer to
            // consume and free space for producer to insert.

            RunnableProducerBlockingQueue producer = new RunnableProducerBlockingQueue();

            RunnableConsumerBlockingQueue consumer1 = new RunnableConsumerBlockingQueue();

            RunnableConsumerBlockingQueue consumer2 = new RunnableConsumerBlockingQueue();

            Thread thread1 = new Thread(producer);

            Thread thread2 = new Thread(consumer1, "Consumer1");

            Thread thread3 = new Thread(consumer2, "Consumer2");

            thread1.start();

            thread2.start();

            thread3.start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
