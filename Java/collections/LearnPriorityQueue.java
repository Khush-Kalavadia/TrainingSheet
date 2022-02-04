package com.java.collections;

import java.util.Comparator;

import java.util.PriorityQueue;

import java.util.Queue;

public class LearnPriorityQueue
{
    public static void main(String[] args)
    {
        try
        {
//          normal queue can be done using following code because linkedlist implements queue

            Queue<Integer> pq = new PriorityQueue<>();

            pq.offer(40);

            pq.offer(12);

            pq.offer(24);

            pq.offer(36);

            System.out.println(pq);   //minHeap is implemented over here so the order is not maintained

            // if we poll the element the smallest element will be polled
            System.out.println(pq.poll());

            System.out.println(pq);               //See again smallest term is at the TOP.

            System.out.println(pq.peek());

            ///////////////////////////// descending order using comparator
            Queue<Integer> pq1 = new PriorityQueue<>(Comparator.reverseOrder());

            pq1.offer(40);

            pq1.offer(12);

            pq1.offer(2);

            pq1.offer(36);

            System.out.println(pq1);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

}
