package com.java.concurrency;

import sun.misc.VM;

import java.util.concurrent.ForkJoinPool;

import java.util.concurrent.RecursiveTask;

public class ForkJoinFibonacci extends RecursiveTask<Integer>
{
    int n;

    ForkJoinFibonacci(int num)
    {
        this.n = num;
    }

    @Override
    protected Integer compute()
    {
        try
        {
            if (n <= 1)
            {
                return n;
            }

            ForkJoinFibonacci forkJoinFibonacci1 = new ForkJoinFibonacci(n - 1);

            forkJoinFibonacci1.fork();

            ForkJoinFibonacci forkJoinFibonacci2 = new ForkJoinFibonacci(n - 2);

            forkJoinFibonacci2.fork();

            return (forkJoinFibonacci1.join() + forkJoinFibonacci2.join());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args)
    {
        try
        {
            ForkJoinPool pool = new ForkJoinPool();

            int ans = pool.invoke(new ForkJoinFibonacci(6));

            System.out.println("Inside ForkJoin for number 6:  " + ans);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
