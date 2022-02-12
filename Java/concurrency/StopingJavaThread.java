package com.java.concurrency;

public class StopingJavaThread
{
    public static class MyRunnable implements Runnable
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

    public static void main(String[] args)
    {
        MyRunnable stopableRunnable = new MyRunnable();

        Thread t1 = new Thread(stopableRunnable, "The thread ");

        t1.start();

        try
        {
            System.out.println(Thread.currentThread().getName() + " is pausing for 5 sec.");

            Thread.sleep(5000);

            System.out.println(Thread.currentThread().getName() + "'s pause finished.");
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("\nRequesting stop");

        stopableRunnable.requestStop();

        System.out.println("Stop requested\n");
    }
}
