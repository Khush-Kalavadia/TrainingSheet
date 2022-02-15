package com.java.concurrency;

public class SynchronizeThisVsClass
{
    public static void main(String[] Args)
    {
        try
        {
            CounterSynchronizedThis counter = new CounterSynchronizedThis();

//            CounterSynchronizedClass counter = new CounterSynchronizedClass();

            Thread thread1 = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        System.out.println(counter.incAndGet());
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
            });

            Thread thread2 = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        Thread.sleep(500);

                        System.out.println(counter.incAndGet());
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
            });

            Thread thread3 = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        Thread.sleep(1000);

                        System.out.println(counter.incAndGet());
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
            });

            thread1.start();

            thread2.start();

            thread3.start();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
