package com.java.collections;

import java.util.Comparator;

import java.util.Iterator;

import java.util.LinkedList;

import java.util.Queue;

public class LearnLinkedList
{
    public static void main(String[] args)
    {
        try
        {
//        LinkedList using LIST

//        List<Integer> ll = new LinkedList<>();            //if we use parent datatype List then some methods might not work

            LinkedList<Integer> ll = new LinkedList<>();

            ll.add(1);

            ll.add(4);

            ll.add(3);

            ll.add(2);

            ll.add(5);

            System.out.println(ll);

            Iterator<Integer> it = ll.descendingIterator();     //works from END

//        Iterator<Integer> it = ll.listIterator();         //this also works

            while (it.hasNext())
            {
                System.out.println(it.next());
            }

            ll.remove(2);           //this is index to be removed

            System.out.println(ll);

            ll.remove(new Integer(2));           //this is value to be removed

            System.out.println(ll);

            ll.addFirst(0);             //linkedList specific not present in List class

            System.out.println(ll);

            ll.sort(Comparator.naturalOrder());

            System.out.println("Sorting in natural order:"+ll);

            ll.sort(Comparator.reverseOrder());

            System.out.println("Sorting in reverse order:"+ll);


            System.out.println("-------USING QUEUE-------");

            /////////////////Linked list using QUEUE. LinkedList implements queue.

            Queue<Integer> ll2 = new LinkedList<>();

            ll2.offer(10);          //only throws true or false on adding

            ll2.offer(20);          //while add throws exception if not added

            ll2.offer(30);

            ll2.offer(40);

            ll2.offer(50);

            System.out.println(ll2);

            System.out.println(ll2.poll());     //remove first element

            System.out.println(ll2);

            System.out.println(ll2.peek());     //view first element

            System.out.println(ll2);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
