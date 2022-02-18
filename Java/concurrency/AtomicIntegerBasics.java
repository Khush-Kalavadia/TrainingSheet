package com.java.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerBasics
{
    public static void main(String[] args)
    {
        try
        {
            AtomicInteger atomicInteger = new AtomicInteger();

            System.out.println("atomicInteger: "+atomicInteger.get());        //initial 0

            System.out.println("atomicInteger.addAndGet(10): "+atomicInteger.addAndGet(10));

            AtomicInteger atomicInteger1 = new AtomicInteger(10);

            System.out.println("atomicInteger1: 10");

            System.out.println("atomicInteger.equals(atomicInteger1): "+atomicInteger.equals(atomicInteger1));       //this is why we can't use it in place of integer

            System.out.println("atomicInteger.compareAndSet(10,20): "+atomicInteger.compareAndSet(10, 20));

            System.out.println("atomicInteger: "+atomicInteger.get());

            System.out.println("atomicInteger1.compareAndSet(12,30): "+atomicInteger1.compareAndSet(12,30));

            System.out.println("atomicInteger1: "+atomicInteger1.get());

            atomicInteger.set(22);

            System.out.println("atomicInteger.set(22): "+atomicInteger.get());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
