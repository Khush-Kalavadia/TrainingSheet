package com.java.collections;

import java.util.LinkedList;
import java.util.ListIterator;

import java.util.Stack;

public class LearnStack
{
    public static void main(String[] args)
    {
        try
        {
            Stack<Integer> s1 = new Stack<>();

            s1.push(1);

            s1.push(2);

            s1.push(3);

            s1.push(4);

            s1.push(5);

            System.out.println(s1);

            int receivedInt = s1.peek();        //won't delete

            System.out.println(s1);

            System.out.println("Peek:"+receivedInt);

            receivedInt = s1.pop();             //remove and return topmost element

            System.out.println(s1);

            System.out.println("Pop:"+receivedInt);

            //SEARCH IS FROM TOP. uses equals() method to search an object in the stack.
            System.out.println("Search result of 4 is "+s1.search(4));

            System.out.println("Search result of 3 is "+s1.search(3));

            System.out.println("Search result of 2 is "+s1.search(2));

            System.out.println("Index of value 2 is "+s1.indexOf(2));

            ListIterator<Integer> it = s1.listIterator();      //iterator starts from bottom

            while (it.hasNext())
            {
                System.out.println(it.next());
            }

            System.out.println("-----------");

            System.out.println(it.previous());

            System.out.println(it.previous());

            System.out.println(it.next());

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
