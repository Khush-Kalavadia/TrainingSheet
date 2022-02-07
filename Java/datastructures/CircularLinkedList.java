package com.java.datastructures;

public class CircularLinkedList
{
    private class Node
    {
        int data;

        Node next;

        Node(int value)
        {
            data = value;
        }
    }

    private Node head;

    private Node tail;

    public void insert(int value)
    {
        try
        {
            Node node = new Node(value);

            if (head == null)
            {
                head = node;

                tail = node;

                return;
            }

            node.next = head;

            tail.next = node;

            tail = node;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void show()
    {
        try
        {
            if (head != null)
            {
                Node n = head;

                do
                {
                    System.out.println(n.data);

                    n = n.next;
                }
                while (n != head);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
