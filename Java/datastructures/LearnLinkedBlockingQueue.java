package com.java.datastructures;

import java.util.concurrent.BlockingQueue;

import java.util.concurrent.LinkedBlockingQueue;

public class LearnLinkedBlockingQueue
{
    public static void main(String[] args)
    {
        try
        {
            BlockingQueue<Integer> LinkedBlockingQueue = new LinkedBlockingQueue<>();

            System.out.println("Capacity not specified LinkedBlockingQueue");

            System.out.println(LinkedBlockingQueue.offer(4));

            System.out.println(LinkedBlockingQueue.offer(10));

            System.out.println(LinkedBlockingQueue.offer(15));

            System.out.println(LinkedBlockingQueue);

            System.out.println("Remainging capacity: " + LinkedBlockingQueue.remainingCapacity() + "\n");

            BlockingQueue<Integer> LinkedBlockingQueue1 = new LinkedBlockingQueue<>(2);

            System.out.println("Capacity 2 specified LinkedBlockingQueue");

            System.out.println(LinkedBlockingQueue1.offer(4));

            System.out.println(LinkedBlockingQueue1.offer(10));

            System.out.println(LinkedBlockingQueue1.offer(15));

            System.out.println(LinkedBlockingQueue1);

            System.out.println("Remainging capacity: " + LinkedBlockingQueue1.remainingCapacity());

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
