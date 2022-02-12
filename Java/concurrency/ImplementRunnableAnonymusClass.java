package com.java.concurrency;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by khush on 12/2/22.
 */
public class ImplementRunnableAnonymusClass
{
    public static void main(String[] args)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

            Runnable myRunnable = new Runnable()

            {
                @Override
                public void run()
                {
                    Calendar cal = Calendar.getInstance();

                    System.out.println(sdf.format(cal.getTime()));

                    System.out.println("myRunnable running: "+Thread.currentThread().getName());

                    try
                    {
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                    cal = Calendar.getInstance();

                    System.out.println(sdf.format(cal.getTime()));

                    System.out.println("myRunnable finished");
                }
            };

            Thread t1 = new Thread(myRunnable);

            t1.start();

            Runnable newRunnable = new Runnable()
            {
                @Override
                public void run()
                {
                    Calendar cal = Calendar.getInstance();

                    System.out.println(sdf.format(cal.getTime()));

                    System.out.println("newRunnable running: "+Thread.currentThread().getName());

                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                    cal = Calendar.getInstance();

                    System.out.println(sdf.format(cal.getTime()));

                    System.out.println("newRunnable finished");
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
