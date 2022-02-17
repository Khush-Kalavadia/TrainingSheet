package com.java.concurrency;

import java.util.concurrent.Semaphore;

/**
 * Created by khush on 16/2/22.
 */
public class SemaphoreBasics
{
    public static void main(String[] args)
    {
        try
        {
            Semaphore semaphore = new Semaphore(2);

            semaphore.release();

            System.out.println("Available permits: " + semaphore.availablePermits());

            semaphore.acquire();

            semaphore.acquire();

            semaphore.acquire();

            System.out.println("Available permits: " + semaphore.availablePermits());

            semaphore.acquire();        //as all permits are acquired it will wait for one to release

            System.out.println("Done");

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
