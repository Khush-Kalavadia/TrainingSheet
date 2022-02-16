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
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

            Calendar cal;

            cal = Calendar.getInstance();

            System.out.println(sdf.format(cal.getTime())+" "+Thread.currentThread().getName() + " waiting to acquire");

            semaphore.acquire();

            cal = Calendar.getInstance();

            System.out.println(sdf.format(cal.getTime())+" "+Thread.currentThread().getName() + " acquired");

            //work
            for (int i = 0; i < 10; i++)
            {
                counter.incAndGet();

                Thread.sleep(300);
            }

            semaphore.release();

            cal = Calendar.getInstance();

            System.out.println(sdf.format(cal.getTime())+" "+Thread.currentThread().getName() + " released");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


    }
}
