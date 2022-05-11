package com.java.concurrency;

import static java.lang.Thread.sleep;

public class DaemonThread
{
    public static void main(String[] args)
    {
        Runnable runnable = () ->
        {
            while (true)
            {
                try
                {
                    sleep(1000);

                    System.out.println("Thread running");
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread(runnable);

        // Setting it daemon makes it terminate as soon as all non-daemon i.e. main
        // over here terminates.

        t1.setDaemon(true);

//        t1.setDaemon(false);        //if we set this then it goes on till we do not terminate it manually

        t1.start();

        try
        {
            System.out.println("Main waiting for 3 sec");

            sleep(3100);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("Main going to terminate");
    }
}
