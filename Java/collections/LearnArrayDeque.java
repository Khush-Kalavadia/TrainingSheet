package com.java.collections;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class LearnArrayDeque        //can be used in sliding window
{
    public static void main(String[] args)
    {
        try
        {
            ArrayDeque<Integer> ad = new ArrayDeque<>();

            //following also works
//        Deque<Integer> ad = new LinkedList<>();           //linkedlist implements deque
//        Deque<Integer> ad = new ArrayDeque<>();           //arrayDeque implements deque

            ad.offer(15);

            ad.offerFirst(23);

            ad.offer(11);

            ad.offerLast(9);                    //offer and offerLast are same

            ad.offerLast(19);

            System.out.println(ad);

            System.out.println(ad.poll());          //the element that is going to leave which would be at front

            System.out.println(ad);

            System.out.println(ad.pollFirst());

            System.out.println(ad);

            System.out.println(ad.pollLast());

            System.out.println(ad);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
