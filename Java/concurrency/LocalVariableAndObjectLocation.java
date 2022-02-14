package com.java.concurrency;

public class LocalVariableAndObjectLocation
{
    public static void main(String[] args)
    {
        try
        {
            MyRunnableWithObject myRunnableWithObject = new MyRunnableWithObject();

            Thread thread1 = new Thread(myRunnableWithObject, "Thread1");

            Thread thread2 = new Thread(myRunnableWithObject, "Thread2");

            thread1.start();

            thread2.start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}

