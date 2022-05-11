package com.java.concurrency;

public class WaitForDaemonThread
{
    public static void main(String[] args)
    {
        try
        {
            Runnable runnable = new Runnable()
            {
                @Override
                public void run()
                {
                    //parameters can't be passed
                    //solution: 1.global variable
                    //          2.make a method and pass thru constructor (getRunnable of RaceConditionReadModifyWrite)

                    System.out.println("Running runnable (*)");

                    for (int i = 0; i < 3; i++)
                    {
                        try
                        {
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        System.out.println("*");
                    }
                    System.out.println("Daemon thread runnable executed.");
                }
            };

            Runnable runnable1 = new Runnable()
            {
                @Override
                public void run()
                {
                    System.out.println("Running runnable1 (#)");

                    for (int i = 0; i < 6; i++)
                    {
                        try
                        {
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        System.out.println("#");
                    }
                    System.out.println("Daemon thread runnable1 executed.");
                }
            };

            Thread thread1 = new Thread(runnable);   //it has 3 iterations

            thread1.setDaemon(true);

            thread1.start();

            Thread thread2 = new Thread(runnable1);  //it has 6 iterations though only 3 runs as thread1 is joined

            thread2.setDaemon(true);

            thread2.start();

            try
            {
                thread1.join();                  //MAIN METHOD waits till thread1 does not finishes

//            thread1.join(2100);        //waits for 2.1sec even if thread1 would take 5sec to finish

                thread2.join();                  //it would wait for 3sec only if commented
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            System.out.println("Main done!");     //won't print till the join part is not over

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


    }

}
