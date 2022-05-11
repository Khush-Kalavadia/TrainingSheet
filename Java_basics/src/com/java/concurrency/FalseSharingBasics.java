package com.java.concurrency;

import sun.java2d.loops.TransformHelper;

public class FalseSharingBasics
{
    public static void main(String[] args)
    {
        try
        {
            CounterVolatile counter1 = new CounterVolatile();

//            CounterVolatile counter2 = counter1;

            CounterVolatile counter2 = new CounterVolatile();

            long iterations = 50_000_000;

            Thread thread1 = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        long startTime = System.currentTimeMillis();

                        for (long i = 0; i < iterations; i++)
                        {
                            counter1.value1++;
                        }
                        long endTime = System.currentTimeMillis();

                        System.out.println("Thread1 time: " + (endTime - startTime));
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
            });

            Thread thread2 = new Thread(() ->
            {
                try
                {
                    long startTime = System.currentTimeMillis();

                    for (long i = 0; i < iterations; i++)
                    {
                        counter2.value2++;
                    }
                    long endTime = System.currentTimeMillis();

                    System.out.println("Thread2 time: " + (endTime - startTime));

                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            });

            thread1.start();

            thread2.start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
