package com.java.concurrency;

import java.util.concurrent.Semaphore;

public class SemaphoreMain
{
    public static void main(String[] args)
    {
        try
        {
            Semaphore semaphore = new Semaphore(2);

//            Counter counter = new Counter();
//
//            Counter counter1 = new Counter();
//
//            Counter counter2 = new Counter();

            Counter counter = new Counter();        //this case as well as above blocks the third thread
                                                    // as limit is 2 and same SEMAPHORE is passed.

            Counter counter1 = counter;

            Counter counter2 = counter;

            RunnableSemaphore runnableSemaphore = new RunnableSemaphore(semaphore, counter);

            RunnableSemaphore runnableSemaphore1 = new RunnableSemaphore(semaphore, counter1);

            RunnableSemaphore runnableSemaphore2 = new RunnableSemaphore(semaphore, counter2);

            Thread thread1 = new Thread(runnableSemaphore);

            Thread thread2 = new Thread(runnableSemaphore1);

            Thread thread3 = new Thread(runnableSemaphore2);

            thread1.start();

            Thread.sleep(500);

            thread2.start();

            Thread.sleep(500);

            thread3.start();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
