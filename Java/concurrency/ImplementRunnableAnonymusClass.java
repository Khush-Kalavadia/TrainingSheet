package com.java.concurrency;

import java.text.SimpleDateFormat;

import java.util.Calendar;

public class ImplementRunnableAnonymusClass
{
    public static void main(String[] args)
    {
        try
        {
            Runnable myRunnable = new Runnable()

            {
                @Override
                public void run()
                {
                    System.out.println(TimeUtil.showTime()+" myRunnable running: "+Thread.currentThread().getName());

                    try
                    {
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                    System.out.println(TimeUtil.showTime()+" myRunnable finished");
                }
            };

            Thread t1 = new Thread(myRunnable);

            t1.start();

            Runnable newRunnable = new Runnable()
            {
                @Override
                public void run()
                {
                    System.out.println(TimeUtil.showTime()+" newRunnable running: "+Thread.currentThread().getName());

                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                    System.out.println(TimeUtil.showTime()+" newRunnable finished");
                }
            };

            Thread t2 = new Thread(newRunnable);

            t2.start();

            Thread t3 = new Thread(newRunnable);

            t3.start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
