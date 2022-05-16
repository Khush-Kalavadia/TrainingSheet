package com.java.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinException
{
    public static void main(String[] args)
    {
        int counter = 0;

        ForkJoinPool forkJoinPool = new ForkJoinPool(1);

        try
        {
            while (true)
            {
                forkJoinPool.execute(new WaitTask());

                forkJoinPool.getQueuedTaskCount();

                System.out.println(counter++);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}

class WaitTask extends RecursiveAction
{
    @Override
    protected void compute()
    {
        try
        {
            Thread.sleep(1000000);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
