package com.java.concurrency;

public class SynchronizedExchangerMain
{
    public static void main(String[] args)
    {
        try
        {
            SynchronizedExchanger synchronizedExchange = new SynchronizedExchanger();

            Thread thread1 = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        for (int i = 0; i < 1000; i++)
                        {
                            Thread.sleep(1);

                            synchronizedExchange.setObject(i);
                        }
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
                    try
                    {
                        for (int i = 0; i < 1000; i++)
                        {
                            Thread.sleep(1);

                            System.out.println(synchronizedExchange.getObj());
                        }
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
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
