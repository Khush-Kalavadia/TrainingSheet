package com.java.concurrency;

import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.util.concurrent.Semaphore;

public class RunnableSemaphore implements Runnable
{
    Semaphore semaphore = null;

    Counter counter = null;

    public RunnableSemaphore(Semaphore semaphore, Counter counter)
    {
        this.semaphore = semaphore;

        this.counter = counter;
    }

    @Override
    public void run()
    {
        try
        {
            System.out.println(TimeUtil.showTime()+" "+Thread.currentThread().getName() + " waiting to acquire");

            semaphore.acquire();

            System.out.println(TimeUtil.showTime()+" "+Thread.currentThread().getName() + " acquired");

            //myMethod
            for (int i = 0; i < 10; i++)
            {
                counter.incAndGet();

                Thread.sleep(300);
            }

            semaphore.release();

            System.out.println(TimeUtil.showTime()+" "+Thread.currentThread().getName() + " released");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
