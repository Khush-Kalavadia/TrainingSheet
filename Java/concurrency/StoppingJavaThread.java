package com.java.concurrency;

public class StoppingJavaThread
{
    public static void main(String[] args)
    {
        MyRunnableWithStop stoppableRunnable = new MyRunnableWithStop();

        Thread t1 = new Thread(stoppableRunnable, "myThread ");

        try
        {
            t1.start();

            System.out.println(Thread.currentThread().getName() + " is pausing for 5 sec.");

            Thread.sleep(5000);

            System.out.println(Thread.currentThread().getName() + "'s pause finished.");


            System.out.println("\nRequesting stop");

            stoppableRunnable.requestStop();

            System.out.println("Stop requested\n");

        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
