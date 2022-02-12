package com.java.concurrency;

public class ImplementRunnableLambdaExpression
{
    public static void main(String[] args)
    {
        try
        {
            Runnable MyRunnable = () ->
            {
                System.out.println("Lambda running");

                String threadName = Thread.currentThread().getName();

                System.out.println(threadName + " is running");

                System.out.println("Lambda finished");

                System.out.println("------------");
            };

            Thread t1 = new Thread(MyRunnable);   //we aren't passing any name of the thread

            t1.start();

            Thread t2 = new Thread(MyRunnable, "myThread");//passing thread name

            t2.start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
