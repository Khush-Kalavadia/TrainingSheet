package com.java.data.structures;

import java.util.ArrayDeque;

import java.util.Deque;

import java.util.Stack;

public class StackUsingDeque
{
    public static void main(String[] args)
    {
        try
        {
            Stack<Integer> s1 = new Stack<>();

            s1.push(3);

            s1.push(2);

            s1.push(4);

            System.out.println("------Stack------");

            System.out.println(s1);

            System.out.println(s1.peek());

            System.out.println(s1.pop());

            System.out.println(s1);

            System.out.println("------Deque------");

            Deque<Integer> dq = new ArrayDeque<>();

            dq.offer(3);

            dq.offer(2);

            dq.addLast(4);                          //equivalent to push

            System.out.println(dq);

            System.out.println(dq.peekLast());        //equivalent to stack's peek

            System.out.println(dq.removeLast());       //equivalent to pop()

            System.out.println(dq);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
