package com.java.concurrency;

public class SynchronizeThisVsClass
{
    public static void main(String[] Args)
    {
        // In this program we are creating 3 threads which access the same method of counter class which runs for 3 sec.
        // thread0 starts at time 0, thread1 at time 500millisec and thread2 at time 1sec.

        try
        {
            // synchronize(this) means there is exactly one thread per instance.
            // Different instances of counter are passed so "this" considers each one as different and allows them to run parallely.
            CounterSynchronizedThis counter = new CounterSynchronizedThis();

            CounterSynchronizedThis counter1 = new CounterSynchronizedThis();

            CounterSynchronizedThis counter2 = new CounterSynchronizedThis();


            // When same instance of counter is passed then here other threads need to wait because you can't access
            // the same counter instance at the same time.
//            CounterSynchronizedThis counter = new CounterSynchronizedThis();
//
//            CounterSynchronizedThis counter1 = counter;
//
//            CounterSynchronizedThis counter2 = counter;


            // synchronize(X.class) means only one thread in the block.
            // Hence, when different instances of counter were created, they were not able to access and others had to pause.
            // During first 5 sec only thread0 works. thread1 and thread2 wait and after 5sec anyone of 1 or 2 starts by OS.
//            CounterSynchronizedClass counter = new CounterSynchronizedClass();
//
//            CounterSynchronizedClass counter1 = new CounterSynchronizedClass();
//
//            CounterSynchronizedClass counter2 = new CounterSynchronizedClass();


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

                        System.out.println(counter1.incAndGet());
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

                        System.out.println(counter2.incAndGet());
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
