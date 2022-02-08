package com.java.datastructures;

public class LearnQueueUsingArray
{
    public static void main(String[] args)
    {
        try
        {
//            QueueUsingArray q = new QueueUsingArray();        //use default data of size as 10

            QueueUsingArray q = new QueueUsingArray(5);

            q.enqueue(23);

            q.enqueue(89);

            q.enqueue(3);

            q.enqueue(9);

            q.enqueue(30);

            System.out.println("Front: "+q.getFront());

            System.out.println("Rear: "+q.getRear());

            System.out.println("Item count: "+q.getItemCount());

            System.out.println(q);

            System.out.println("-------Dequeue: "+q.dequeue()+"-------");

            System.out.println("Front: "+q.getFront());

            System.out.println("Rear: "+q.getRear());

            System.out.println("Item count: "+q.getItemCount());

            System.out.println(q);

            System.out.println("-------Enqueue: 10-------");

            q.enqueue(10);          //throws exception that rear reached end of array

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
