package com.java.concurrency;

import java.text.SimpleDateFormat;

import java.util.Calendar;

public class CounterSynchronizedClass
{
    private long count = 0;

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    Calendar cal;

    public long incAndGet()
    {
        try
        {
            synchronized (CounterSynchronizedClass.class)   //way to specify the adding and returning as one atomic operation
            {
                cal = Calendar.getInstance();

                System.out.println(sdf.format(cal.getTime())+" incAndGet (X.class). Thread: " + Thread.currentThread().getName()+ " Start");

                Thread.sleep(5000);

                cal = Calendar.getInstance();

                System.out.println(sdf.format(cal.getTime())+" incAndGet (X.class). Thread: " + Thread.currentThread().getName()+" Pause Over");

                this.count++;

                return this.count;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return -1;
    }

    public long getCount()
    {
        try
        {
            synchronized (CounterSynchronizedClass.class)
            {
                System.out.println("getCount (X.class). Thread: " + Thread.currentThread().getName());

                return this.count;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return -1;
    }
}
