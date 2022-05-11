package com.java.concurrency;

import java.util.concurrent.locks.Lock;

import java.util.concurrent.locks.ReentrantLock;

public class DeadlockPreventionTimeout
{
    public static void main(String[] args)
    {
        try
        {
            Lock lock1 = new ReentrantLock();

            Lock lock2 = new ReentrantLock();

            RunnableTimeoutA runnableTimeoutA = new RunnableTimeoutA(lock1,lock2);

            RunnableTimeoutB runnableTimeoutB = new RunnableTimeoutB(lock1,lock2);

            Thread thread1 = new Thread(runnableTimeoutA);

            Thread thread2 = new Thread(runnableTimeoutB);

            thread1.start();

            thread2.start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
