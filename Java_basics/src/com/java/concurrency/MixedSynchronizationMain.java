package com.java.concurrency;

public class MixedSynchronizationMain
{
    public static void main(String[] args)
    {
        try
        {
            MixedSynchronization mixedSynchronization = new MixedSynchronization();

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

                            mixedSynchronization.setInstanceObject(i);
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

                            MixedSynchronization.setStaticObject(i);
                        }
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
            });

            Thread thread3 = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        for (int i = 0; i < 1000; i++)
                        {
                            Thread.sleep(1);

                            System.out.println("Instance object: "+mixedSynchronization.getInstanceObject());
                        }
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
            });


            Thread thread4 = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        for (int i = 0; i < 1000; i++)
                        {
                            Thread.sleep(1);

                            System.out.println("Static object: "+MixedSynchronization.getStaticObject());
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

            thread3.start();

            thread4.start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}

