package com.java.concurrency;

public class RaceConditionWriteRead
{
    static Runnable getIncrementingRunnable(Counter counter, String message)
    {
        return () ->
        {
            for (int i = 0; i < 1_00_000; i++)
            {
                counter.incAndGet();
            }

            System.out.println(message + " " + counter.getCount());
        };
    }

    static Runnable getReadingRunnable(Counter counter, String message)
    {
        return () ->
        {
            for (int i = 0; i < 5; i++)
            {
                try
                {
                    Thread.sleep(0, 25000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println(message + " " + counter.getCount());
            }

        };

    }

    public static void main(String[] args)
    {
        try
        {
            Counter counter = new Counter();

            Thread thread1 = new Thread(getIncrementingRunnable(counter, "Thread1"));

            Thread thread2 = new Thread((getReadingRunnable(counter, "Thread2")));

            thread1.start();

            thread2.start();        //no race condition as 2 threads are not updating same counter. There could just be visibility problem
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
