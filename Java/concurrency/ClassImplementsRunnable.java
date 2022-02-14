package com.java.concurrency;

public class ClassImplementsRunnable
{

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
