package com.java.concurrency;

public class ClassImplementsRunnable
{

    public static void main(String[] args)
    {
        try
        {
            MyRunnable r1 = new MyRunnable();

            Thread t1 = new Thread(r1);
            Thread t2 = new Thread(r1);

            t1.start();
            t2.start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
