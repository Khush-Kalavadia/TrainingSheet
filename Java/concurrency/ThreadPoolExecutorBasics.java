package com.java.concurrency;

import java.util.concurrent.*;

//This program is just to understand the constructor of ThreadPoolExecutor and ScheduledThreadPoolExecutor.

public class ThreadPoolExecutorBasics
{
    public static void main(String[] args)
    {
        try
        {
            int corePoolSize = 10;

            int maxPoolSize = 20;

            long keepAliveTime = 3000;

            ExecutorService threadPoolExecutor = new
                    ThreadPoolExecutor(corePoolSize,        //starts with pool size
                    maxPoolSize,                            //extend to max this much size
                    keepAliveTime,     //will be alive for this much idle time before terminating
                    TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<Runnable>(128));   //queue in which tasks are stored

            threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);

            ExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(corePoolSize);  //

            scheduledExecutorService = Executors.newScheduledThreadPool(10);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
