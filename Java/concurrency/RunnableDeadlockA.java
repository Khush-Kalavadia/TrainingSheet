package com.java.concurrency;

import java.util.concurrent.locks.Lock;

public class RunnableDeadlockA implements Runnable
{

    private Lock lock1 = null;

    private Lock lock2 = null;

    public RunnableDeadlockA(Lock lock1, Lock lock2)
    {
        this.lock1 = lock1;

        this.lock2 = lock2;
    }

    @Override
    public void run()
    {
        try
        {
            String threadName = Thread.currentThread().getName();

            System.out.println(threadName + " attempting to lock lock1");

            lock1.lock();

            System.out.println(threadName + " locked lock 1");

            Thread.sleep(3000);

            System.out.println(threadName + " attempting to lock lock2");

            lock2.lock();

            System.out.println(threadName + " locked lock 2");

            //do the work

            System.out.println(threadName + " attempting to unlock lock1");

            lock1.unlock();

            System.out.println(threadName + " attempting to unlock lock2");

            lock2.unlock();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


    }
}
