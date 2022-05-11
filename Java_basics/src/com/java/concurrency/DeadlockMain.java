package com.java.concurrency;

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

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
