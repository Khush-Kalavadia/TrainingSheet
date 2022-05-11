package com.java.data.structures;

public class LearnStackUsingArray
{
    public static void main(String[] args)
    {
        try
        {
            StackUsingArray stack = new StackUsingArray();

//            stack.pop();        //check exception

            stack.push(23);

            stack.push(9);

            stack.push(15);

            stack.push(2);

            System.out.println("Value 3 pushed: " + stack.push(3));

            System.out.println("Popped data: " + stack.pop());

            System.out.println("Peeked data: " + stack.peek());

            stack.popAllShow();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
