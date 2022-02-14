package com.java.concurrency;

public class MyRunnableWithObject implements Runnable
{
    private int count = 0;      //stored in heap

    private Object sharedObject = new Object();

    @Override
    public void run()
    {
        try
        {
            Object myObject = new Object();       //stored in thread stack

            System.out.println(myObject);

            System.out.println("Shared object "+sharedObject);

            for (int i = 0; i < 1_00_000; i++)
            {
                this.count++;
            }

            System.out.println(Thread.currentThread().getName()+":"+this.count);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
