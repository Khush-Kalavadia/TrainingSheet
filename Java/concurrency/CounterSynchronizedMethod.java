package com.java.concurrency;

import java.text.SimpleDateFormat;

import java.util.Calendar;

public class CounterSynchronizedMethod
{
    private long count = 0;

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    Calendar cal;

    public synchronized long incAndGet()
    {
        try
        {
            cal = Calendar.getInstance();

            System.out.println(sdf.format(cal.getTime()) + " incAndGet (this). Thread: " + Thread.currentThread().getName() + " Start");

            Thread.sleep(5000);

            cal = Calendar.getInstance();

            System.out.println(sdf.format(cal.getTime()) + " incAndGet (this). Thread: " + Thread.currentThread().getName() + " Pause Over");

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
            System.out.println("getCount (this). Thread: " + Thread.currentThread().getName());

            return this.count;

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return -1;
    }
}
