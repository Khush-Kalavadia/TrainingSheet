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
//        SinglyLinkedList using LIST

//        List<Integer> ll = new SinglyLinkedList<>();            //if we use parent datatype List then some methods might not myMethod

            LinkedList<Integer> linkedList = new LinkedList<>();

            linkedList.add(1);

            linkedList.add(4);

            linkedList.add(3);

            linkedList.add(2);

            linkedList.add(5);

            System.out.println(linkedList);

            Iterator<Integer> it = linkedList.descendingIterator();     //works from END

//        Iterator<Integer> it = ll.listIterator();         //this also works

            while (it.hasNext())
            {
                System.out.println(it.next());
            }

            linkedList.remove();                //removes first element

            System.out.println(linkedList);

            linkedList.remove(2);           //this is index to be removed

            System.out.println(linkedList);

            linkedList.remove(new Integer(2));           //this is value to be removed

            System.out.println(linkedList);

            linkedList.addFirst(0);             //linkedList specific not present in List class

            System.out.println(linkedList);

            linkedList.sort(Comparator.naturalOrder());

            System.out.println("Sorting in natural order:"+linkedList);

            linkedList.sort(Comparator.reverseOrder());

            System.out.println("Sorting in reverse order:"+linkedList);


            System.out.println("-------USING QUEUE-------");

            /////////////////Linked list using QUEUE. SinglyLinkedList implements queue.

            Queue<Integer> linkedList1 = new LinkedList<>();

            linkedList1.offer(10);          //only throws true or false on adding

            linkedList1.offer(20);          //while putValue throws exception if not added

            linkedList1.offer(30);

            linkedList1.offer(40);

            linkedList1.offer(50);

            System.out.println(linkedList1);

            System.out.println(linkedList1.poll());     //remove first element

            System.out.println(linkedList1);

            System.out.println(linkedList1.peek());     //view first element

            System.out.println(linkedList1);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
