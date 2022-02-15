package com.java.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import java.util.concurrent.locks.ReentrantLock;

public class DeadlockMain
{
    public static void main(String[] args)
    {
        try
        {
            Lock lock1 = new ReentrantLock();

            Lock lock2 = new ReentrantLock();

            Runnable runnable1 = new RunnableDeadlockA(lock1, lock2);

            Runnable runnable2 = new RunnableDeadlockB(lock1, lock2);

            Thread thread1 = new Thread(runnable1, "Thread1");

            Thread thread2 = new Thread(runnable2, "Thread2");

            thread1.start();

            thread2.start();

            Lock lock = new Lock()
            {
                @Override
                public void lock()
                {

                }

                @Override
                public void lockInterruptibly() throws InterruptedException
                {

                }

                @Override
                public boolean tryLock()
                {
                    return false;
                }

                @Override
                public boolean tryLock(long time, TimeUnit unit) throws InterruptedException
                {
                    return false;
                }

                @Override
                public void unlock()
                {

                }

                @Override
                public Condition newCondition()
                {
                    return null;
                }
            };

            lock.tryLock()

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
