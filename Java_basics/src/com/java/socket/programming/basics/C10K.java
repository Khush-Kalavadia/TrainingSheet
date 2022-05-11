package com.java.socket.programming.basics;

import com.java.concurrency.MyRunnable;

import java.math.BigInteger;

public class C10K
{
    public static void main(String[] args)
    {
        try
        {
            BigInteger count = new BigInteger("0"), big1;

            while (true)
            {
                Thread thread = new Thread(new MyInfiniteRunnable());

                thread.start();

                big1 = new BigInteger("1");

                count = count.add(big1);

                System.out.println(count);
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
