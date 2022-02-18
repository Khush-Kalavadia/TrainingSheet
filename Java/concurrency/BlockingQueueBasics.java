package com.java.concurrency;

import java.util.ArrayList;

import java.util.concurrent.ArrayBlockingQueue;

import java.util.concurrent.BlockingQueue;

import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueBasics
{
    public static void main(String[] args)
    {
        try
        {
//            LinkedBlockingQueue<Integer> arrayBlockingQueue = new LinkedBlockingQueue<>();

            BlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);

//            System.out.println(arrayBlockingQueue.take());      //will wait for values in the queue

            System.out.println("Poll: " + arrayBlockingQueue.poll());         //will return null

            System.out.println("Remove Object 4: " + arrayBlockingQueue.remove(4));    //return true or false

//            arrayBlockingQueue.remove();            //throws exception. This method is from parent class AbstractQueue.

            arrayBlockingQueue.put(23);

            arrayBlockingQueue.put(4);

            arrayBlockingQueue.put(9);

//            arrayBlockingQueue.put(5);      //will WAIT if we use put because capacity is full

//            arrayBlockingQueue.putValue(5);     //will throw an EXCEPTION

            System.out.println(arrayBlockingQueue);

            System.out.println("Offer: " + arrayBlockingQueue.offer(5));        //results true or false

            System.out.println("Take: " + arrayBlockingQueue.take());

            arrayBlockingQueue.put(3);

            System.out.println("Blocking queue: " + arrayBlockingQueue);

            ArrayList<Integer> array = new ArrayList<>();

            System.out.println("\nAfter draining:");

//            arrayBlockingQueue.drainTo(array);

            arrayBlockingQueue.drainTo(array, 2);

            System.out.println("Blocking queue: " + arrayBlockingQueue);

            System.out.println("Array: " + array);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
