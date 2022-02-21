package com.java.concurrency;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

public class ThreadPoolExecutorServiceBasics
{
    public static void main(String[] args)
    {
        try
        {
//            ExecutorService executorService1 = Executors.newSingleThreadExecutor();  //this and the one below are same

//            ExecutorService executorService1 = Executors.newFixedThreadPool(1);

            ExecutorService executorService = Executors.newFixedThreadPool(3);

            executorService.execute(newRunnable("Task 1"));

            executorService.execute(newRunnable("Task 2"));

            executorService.execute(newRunnable("Task 3"));

            executorService.execute(newRunnable("Task 4"));

            executorService.execute(newRunnable("Task 5"));

            ExecutorService executorService1 = Executors.newFixedThreadPool(5);

            executorService1.execute(newRunnable("Task 1"));

            executorService1.execute(newRunnable("Task 2"));

            executorService1.execute(newRunnable("Task 3"));

            executorService1.execute(newRunnable("Task 4"));

            executorService1.execute(newRunnable("Task 5"));

            executorService.shutdown();         //if not done then program would continue executing

            executorService1.shutdown();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private static Runnable newRunnable(String message)
    {
        try
        {
            return new Runnable()
            {
                @Override
                public void run()
                {
                    System.out.println(Thread.currentThread().getName() + ": " + message);
                }
            };
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
