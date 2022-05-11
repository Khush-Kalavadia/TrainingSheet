package com.java.concurrency;

public class MyRunnableWithStop implements Runnable
{
    //////////////////////////////////// Done to stop execution of thread.

    private boolean stopRequested = false;

    public synchronized void requestStop()
    {
        this.stopRequested = true;
    }

    private synchronized boolean isStopRequested()
    {
        return this.stopRequested;
    }

    ////////////////////////////////////

    @Override
    public void run()
    {
        System.out.println("MyRunnable is Running parallelly.");

        while (!isStopRequested())
        {
            // keep doing what this thread should do.

            System.out.println("...");

            sleep(1000);
        }

        System.out.println("MyRunnable is stopped.");
    }

    public static void sleep(long millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }


}
