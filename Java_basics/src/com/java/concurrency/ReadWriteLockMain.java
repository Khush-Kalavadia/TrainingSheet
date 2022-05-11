package com.java.concurrency;

import java.util.concurrent.locks.ReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockMain
{
    public static void main(String[] args)
    {
        try
        {
            ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

            ReentrantReadWriteLock readWriteLock1 = new ReentrantReadWriteLock(true);

            for (int i = 0; i < 20; i++)
            {
                new Thread(new RunnableConsumerReadLock(readWriteLock1)).start();        //can't use readWriteLock as it is not reentrant

                new Thread(new RunnableProducerWriteLock(readWriteLock1)).start();
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
