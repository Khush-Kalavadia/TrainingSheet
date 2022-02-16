package com.java.concurrency;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RunnableConsumerReadLock implements Runnable
{
    ReentrantReadWriteLock readWriteLock = null;

    RunnableConsumerReadLock(ReentrantReadWriteLock readWriteLock)
    {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void run()
    {
        try
        {
            if (!readWriteLock.readLock().tryLock())
            {
                System.err.println(Thread.currentThread().getName() + " is Waiting for lock to read");
            }
            else
            {
                System.out.println(Thread.currentThread().getName() + " has lock to read");


                readWriteLock.readLock().unlock();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
