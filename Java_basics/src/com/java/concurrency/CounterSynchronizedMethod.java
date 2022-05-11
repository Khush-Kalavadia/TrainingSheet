package com.java.concurrency;

import java.text.SimpleDateFormat;

import java.util.Calendar;

public class CounterSynchronizedMethod
{
    private long count = 0;

    public synchronized long incAndGet()
    {
        try
        {
            System.out.println(TimeUtil.showTime() + " incAndGet (Method). Thread: " + Thread.currentThread().getName() + " Start");

            Thread.sleep(5000);

            System.out.println(TimeUtil.showTime() + " incAndGet (Method). Thread: " + Thread.currentThread().getName() + " Pause Over");

            this.count++;

            return this.count;

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return -1;
    }

    public synchronized long getCount()
    {
        try
        {
            System.out.println("getCount (Method). Thread: " + Thread.currentThread().getName());

            return this.count;

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return -1;
    }
}
