package com.java.datastructures;

public class LearnCircularQueueUsingStack
{
    public static void main(String[] args)
    {
        try
        {
            CircularQueueUsingArray dq = new CircularQueueUsingArray(5);

            dq.enqueue(12);

            dq.enqueue(2);

            dq.enqueue(1);

            dq.enqueue(5);

            System.out.println(dq);

            dq.dequeue();

            System.out.println(dq);

            dq.enqueue(7);

            dq.enqueue(13);

            System.out.println(dq);

            dq.dequeue();


            System.out.println(dq);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
