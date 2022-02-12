package com.java.concurrency;

public class ClassImplementsRunnable
{
    static class MyRunnable implements Runnable    //runnable is interface so implements
    {
        @Override
        public void run()
        {
            System.out.println("MyRunnable running");

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

    public static void main(String[] args)
    {
        try
        {
            Thread t1 = new Thread(new MyRunnable());

            t1.start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
