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
            ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

            //following also works
//            Deque<Integer> arrayDeque = new SinglyLinkedList<>();     //linkedlist implements deque
//            Deque<Integer> arrayDeque = new LinkedList<>();
//            Deque<Integer> arrayDeque = new ArrayDeque<>();           //arrayDeque implements deque

            arrayDeque.offer(15);

            arrayDeque.offerFirst(23);

            arrayDeque.offer(11);

            arrayDeque.offerLast(9);                    //offer and offerLast are same

            arrayDeque.offerLast(19);

            System.out.println(arrayDeque);

            System.out.println(arrayDeque.poll());          //the element that is going to leave which would be at front

            System.out.println(arrayDeque);

            System.out.println(arrayDeque.pollFirst());

            System.out.println(arrayDeque);

            System.out.println(arrayDeque.pollLast());

            System.out.println(arrayDeque);

            System.out.println(arrayDeque.contains(9));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
