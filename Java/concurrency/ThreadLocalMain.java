package com.java.concurrency;

public class ThreadLocalMain
{
    public static void main(String[] args)
    {
        try
        {
            ThreadLocal<String> threadLocal = new ThreadLocal<>();

            Thread thread1 = new Thread(() ->
            {
                try
                {
                    threadLocal.set("thread1");

                    Thread.sleep(3000);

                    System.out.println(threadLocal.get());

                    threadLocal.remove();       //get just shows value it does not removes it

                    System.out.println(threadLocal.get());

                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            });

            Thread thread2 = new Thread(() ->
            {
                try
                {
                    threadLocal.set("thread2");

                    Thread.sleep(2000);

                    System.out.println(threadLocal.get());

                    Thread.sleep(2000);

                    System.out.println(threadLocal.get());      //value will be there set by thread2
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            });


            thread1.start();

            thread2.start();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
