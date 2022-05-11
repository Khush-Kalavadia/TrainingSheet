package com.java.concurrency;

public class MyRunnable implements Runnable    //runnable is interface so implements
{
    @Override
    public void run()
    {
        System.out.println("MyRunnable running");

        System.out.println("Pause runnable pausing for 3 sec.");

//            Thread.sleep(3000);       //give error so need to use try catch block.
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("MyRunnable finished");
    }
}
