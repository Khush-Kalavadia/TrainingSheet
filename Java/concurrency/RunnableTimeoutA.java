package com.java.concurrency;

import java.util.concurrent.TimeUnit;

import java.util.concurrent.locks.Lock;

public class RunnableTimeoutA implements Runnable
{
    private Lock lock1 = null;

    private Lock lock2 = null;

    public RunnableTimeoutA(Lock lock1, Lock lock2)
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

                while (!lockBothLocks())
                {
                    failureCount++;

                    System.err.println(threadName + " failed to lock both Locks. Waiting a bit before retrying [" + failureCount + " tries]");

                    Thread.sleep(100L * ((long) Math.random()));
                }
                if (failureCount > 0)
                {
                    System.out.println(threadName +" succeeded in locking both locks - after " + failureCount + " failures.");
                }

                //locking succeed while(!true) hence reach here
                //do the work

                //unlock as all work is done
                lock2.unlock();

                lock1.unlock();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private boolean lockBothLocks()
    {
        String threadName = Thread.currentThread().getName();

        try
        {
            boolean lock1Succeeded = lock1.tryLock(1000, TimeUnit.MILLISECONDS);

            if (!lock1Succeeded)
            {
                return false;
            }
        }
        catch (Exception e)
        {
            System.out.println(threadName + " interrupted trying to lock Lock 1");

            return false;
        }

        try
        {
            boolean lock2Succeeded = lock2.tryLock(1000, TimeUnit.MILLISECONDS);

            if (!lock2Succeeded)
            {
                lock1.unlock();

                return false;
            }
        }
        catch (Exception e)
        {
            lock1.unlock();

            System.out.println(threadName + " interrupted trying to lock Lock 2");

            return false;
        }

        return true;
    }
}
