package com.java.concurrency;

public class RaceConditionReadModifyWrite
{
    private static Runnable getRunnable(Counter counter, String message)
    {
        return () ->
        {
            for (int i = 0; i < 1_000_000; i++)
            {
                counter.incAndGet();
            }
            System.out.println(message + counter.getCount());
        };

    }

    private static Runnable getSyncRunnable(CounterSynchronized counter, String message)
    {
        return () ->
        {
            for (int i = 0; i < 1_000_000; i++)
            {
                counter.incAndGet();
            }
            System.out.println(message + counter.getCount());
        };

    }

    public static void main(String[] args)
    {
        try
        {
            Counter counter = new Counter();

            Thread thread1 = new Thread(getRunnable(counter, "Thread1 final count: "));

            Thread thread2 = new Thread(getRunnable(counter, "Thread2 final count: "));

            thread1.start();

            thread2.start();

            CounterSynchronized counter1 = new CounterSynchronized();

            Thread thread3 = new Thread(getSyncRunnable(counter1, "Thread3 final count: "));

            Thread thread4 = new Thread(getSyncRunnable(counter1, "Thread4 final count: "));

            thread3.start();

            thread4.start();
            //one of them would be definitely 2_00_000
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
