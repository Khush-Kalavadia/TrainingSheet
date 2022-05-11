package com.java.concurrency;

import java.util.concurrent.atomic.AtomicLong;

import java.util.function.Function;

public class AtomicLongBasics
{

    public static void main(String[] args)
    {
        try
        {
            AtomicLong atomicLong = new AtomicLong();       //will be common in both the functions

            Function<Long, Long> myLambda = (input) ->      //using lambda
            {
                long noOfCalls = atomicLong.incrementAndGet();

                System.out.println("Lambda called " + noOfCalls + " times.");

                return input * 2;
            };

            Function<Long, Long> newLambda = new Function<Long, Long>()
            {                                           //using anonymous class
                @Override
                public Long apply(Long input)
                {
                    long noOfCalls = atomicLong.incrementAndGet();

                    System.out.println("Lambda called " + noOfCalls + " times.");

                    return input * 2;
                }
            };

            System.out.println(myLambda.apply(1L));

            System.out.println(myLambda.apply(3L));

            System.out.println(myLambda.apply(5L));

            System.out.println(newLambda.apply(2L));

            System.out.println(newLambda.apply(4L));

            System.out.println(newLambda.apply(8L));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}