package com.java.concurrency;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RunnableProducerWriteLock implements Runnable
{
    ReentrantReadWriteLock readWriteLock = null;

    RunnableProducerWriteLock(ReentrantReadWriteLock readWriteLock)
    {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void run()
    {
        try
        {
            if (!readWriteLock.writeLock().tryLock())
            {
                System.err.println(Thread.currentThread().getName() + " is Waiting for lock to write");
            }
            else
            {
                System.out.println(Thread.currentThread().getName() + " has lock to write");

                readWriteLock.writeLock().unlock();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
