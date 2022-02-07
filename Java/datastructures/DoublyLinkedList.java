package com.java.datastructures;

public class DoublyLinkedList
{

    private class Node
    {
        int data;

        Node next;

        Node previous;

        public Node(int data)
        {
            this.data = data;
        }

        public Node(int data, Node next, Node previous)
        {
            this.data = data;

            this.next = next;

            this.previous = previous;
        }
    }

    Node head;

    public void insertFirst(int data)
    {
        try
        {
            Node node = new Node(data);         //bydefault fields are initialised null the compiler

            node.next = head;

            node.previous = null;

            if (head != null)
            {
                head.previous = node;
            }

            head = node;
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
            Node n = head;

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

    public void showReverse()
    {
        try
        {
            Node last = null;

            Node n = head;

            while (n != null)
            {
                last = n;

                n = n.next;
            }

            while (last != null)
            {
                System.out.println(last.data);

                last = last.previous;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void insertLast(int i)
    {
        try
        {
            Node node = new Node(i);

            node.next = null;

            if (head == null)
            {
                node.previous = null;

                head = node;
            }
            else
            {
                Node n = head;

                Node last = head;

                while (n != null)
                {
                    last = n;

                    n = n.next;
                }

                last.next = node;

                node.previous = last;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public Node find(int num)
    {
        try
        {
            if (head != null)
            {
                Node n = head;

                while (n != null)
                {
                    if (n.data == num)
                    {
                        return n;
                    }

                    n = n.next;
                }

            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


    public void insertAfter(int after, int value)
    {
        try
        {
            Node node = new Node(value);

            Node found = find(after);

            if (found == null)
            {
                System.out.format("Value %d does not exist\n", value);

                return;
            }

            node.previous = found;

            node.next = found.next;

            if(found.next != null)
            {
                found.next.previous = node;
            }

            found.next = node;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }


}
