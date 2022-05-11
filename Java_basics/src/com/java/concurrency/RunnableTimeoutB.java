package com.java.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Created by khush on 16/2/22.
 */
public class RunnableTimeoutB implements Runnable
{
    private Lock lock1 = null;

    private Lock lock2 = null;

    public RunnableTimeoutB(Lock lock1, Lock lock2)
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

            while (true)
            {
                int failureCount = 0;

                while (!tryLockBothLocks())
                {
                    failureCount++;

                    System.err.println(threadName + " failed to lock both Locks. Waiting a bit before retrying [" + failureCount + " tries]");

                    Thread.sleep(100L * ((long) Math.random()));
                }
                if (failureCount > 0)
                {
                    System.out.println(threadName +" succeeded in locking both locks - after " + failureCount + " failures.");
                }

                //do the myMethod

                //unlock
                lock2.unlock();

                lock1.unlock();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private boolean tryLockBothLocks()
    {
        String threadName = Thread.currentThread().getName();

        try
        {
            boolean lock2Succeeded = lock2.tryLock(1000, TimeUnit.MILLISECONDS);

            if (!lock2Succeeded)
            {
                return false;
            }
        }
        catch (Exception e)
        {
            System.out.println(threadName + " interrupted trying to lock Lock 2");

            return false;
        }

        try
        {
            boolean lock1Succeeded = lock1.tryLock(1000, TimeUnit.MILLISECONDS);

            if (!lock1Succeeded)
            {
                lock2.unlock();

                return false;
            }
        }
        catch (Exception e)
        {
            lock2.unlock();

            System.out.println(threadName + " interrupted trying to lock Lock 1");

            return false;
        }


        return true;
    }
}
