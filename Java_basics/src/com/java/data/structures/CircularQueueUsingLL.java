package com.java.data.structures;

public class CircularQueueUsingLL
{
    private class Node
    {
        int data;

        Node next;

        public Node(int data)
        {
            this.data = data;
        }
    }

    Node front, rear;

    void insertLast(int value)
    {
        try
        {
            Node node = new Node(value);

            if (isEmpty())
            {
                rear = node;

                front = node;
            }
            else
            {
                rear.next = node;

                node.next = front;

                rear = node;
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    void insertFirst(int value)
    {
        try
        {
            Node node = new Node(value);

            if (isEmpty())
            {
                front = node;

                rear = node;
            }
            else
            {
                rear.next = node;

                node.next = front;

                front = node;
            }


        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    int deleteFirst()
    {
        int value = -1;

        try
        {
            if (isEmpty())
            {
                throw new Exception("Can't perform delete first in empty Circular Queue");
            }
            else if (rear == front)
            {
                value = front.data;

                front = null;

                rear = null;
            }
            else
            {
                value = front.data;

                rear.next = front.next;

                front = rear.next;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();


        }
        return value;
    }

    int deleteLast()throws Exception
    {
        int value = -1;

//        try
//        {
            if (isEmpty())
            {
                throw new Exception("Can't perform delete last in empty Circular Queue");
            }
            else if (rear == front)
            {
                value = front.data;

                front = null;

                rear = null;
            }
            else
            {
                value = rear.data;

                Node n = front;

                while (n.next != rear)
                {
                    n = n.next;
                }

                n.next = front;

                rear = n;
            }
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
        return value;
    }

    void show()
    {
        try
        {
            Node n = front;

            if (n == null)
            {
                return;
            }

            System.out.println(n.data);

            n = n.next;

            while (n != front)
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

    boolean isEmpty()
    {
        return front == null;
    }

    public static void main(String[] args)
    {
        try
        {
            CircularQueueUsingLL circularQueue = new CircularQueueUsingLL();

            circularQueue.insertLast(10);

            circularQueue.insertLast(3);

            circularQueue.insertLast(8);

            circularQueue.insertLast(5);

            circularQueue.insertLast(14);

            circularQueue.insertFirst(6);

            circularQueue.insertFirst(1);

            circularQueue.show();

//            cq = new CircularQueueUsingLL();

            System.out.println("Deleting first: " + circularQueue.deleteFirst());      //exception is catched in the function only

            circularQueue.show();

            System.out.println("Deleting last: " + circularQueue.deleteLast());        //here we are catching the exception in main

            circularQueue.show();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
