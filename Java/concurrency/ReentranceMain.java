package com.java.concurrency;

public class ReentranceMain
{
    public static void main(String[] args)
    {
        try
        {
            Reentrance reentrance = new Reentrance();

            Thread thread1 = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        for (int i = 0; i < 5; i++)
                        {
                            System.out.println(reentrance.incAndGet());
                        }
//                        reentrance.incAndGet();
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
            });

            Thread thread2 = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    for (int i = 0; i < 10; i++)
                    {
                        reentrance.inc();

                        System.out.println("Incremented"+i);
                    }
                    System.out.println("Final count: "+reentrance.getCount());
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
