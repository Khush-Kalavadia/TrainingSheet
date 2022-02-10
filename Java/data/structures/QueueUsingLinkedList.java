package com.java.data.structures;

public class QueueUsingLinkedList
{
    private class QNode
    {
        int data;

        QNode next;

        public QNode(int value)
        {
            this.data = value;
        }
    }

    QNode front, end;

    void enqueue(int val)
    {
        QNode node = new QNode(val);

        node.next = null;

        try
        {
            if (front == null)
            {
                front = node;

                end = node;
            }
            else
            {
                end.next = node;

                end = node;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    void show()
    {
        try
        {
            QNode n = front;

            if (n == null)
            {
                throw new Exception("Empty queue.");
            }

            while (n != null)
            {
                System.out.println(n.data);

                n = n.next;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    int dequeue()
    {
        int val = 0;

        try
        {
            if (front == null)
            {
                throw new Exception("Can't dequeue in an empty queue.");
            }
            else
            {
                val = front.data;

                front = front.next;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

        }
        return val;
    }

    public static void main(String[] args)
    {
        QueueUsingLinkedList que = new QueueUsingLinkedList();

        try
        {
            que.enqueue(10);

            que.enqueue(13);

            que.enqueue(5);

            que.enqueue(7);

            que.enqueue(9);

            que.show();

            System.out.println("Dequeue: " + que.dequeue());

            System.out.println("Dequeue: " + que.dequeue());

            System.out.println("Dequeue: " + que.dequeue());

            System.out.println("Dequeue: " + que.dequeue());

            System.out.println("Dequeue: " + que.dequeue());

            System.out.println("Dequeue: " + que.dequeue());        //can't dequeue empty queue and hence throws error

            que.show();                 //throw the exception of empty queue

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());

            ex.printStackTrace();
        }
    }

}
