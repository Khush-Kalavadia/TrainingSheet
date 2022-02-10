package com.java.data.structures;

import java.util.concurrent.ArrayBlockingQueue;

import java.util.concurrent.BlockingQueue;

public class LearnArrayBlockingQueue
{
    public static void main(String[] args)
    {
        try
        {
            BlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);

//            System.out.println(arrayBlockingQueue.take());      //will wait for values in the queue

            System.out.println("Poll: "+arrayBlockingQueue.poll());         //will return null

            System.out.println("Remove Object 4: "+arrayBlockingQueue.remove(4));    //return true or false

//            arrayBlockingQueue.remove();            //throws exception. This method is from parent class AbstractQueue.

            arrayBlockingQueue.put(23);

            arrayBlockingQueue.put(4);

            arrayBlockingQueue.put(9);

//            arrayBlockingQueue.put(5);      //will WAIT if we use put because capacity is full

//            arrayBlockingQueue.add(5);     //will throw an EXCEPTION

            System.out.println("Offer: "+ arrayBlockingQueue.offer(5));        //results true or false

            System.out.println(arrayBlockingQueue.take());

            arrayBlockingQueue.put(3);

            System.out.println(arrayBlockingQueue.take());

            System.out.println(arrayBlockingQueue.take());

            System.out.println(arrayBlockingQueue.take());

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
