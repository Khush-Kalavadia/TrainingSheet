package com.java.socket.programming.basics;

public class MyInfiniteRunnable implements Runnable
{
    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                System.out.print(".");

//                Thread.sleep(100);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
