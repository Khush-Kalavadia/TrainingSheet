package com.java.data.structures;

public class SinglyLinkedList
{
    private class Node
    {
        int data;

        Node next;

    }

    Node head;

    public void insert(int data)
    {
        try
        {
            Node node = new Node();

            node.data = data;

            node.next = null;

            if(this.head == null)
            {
                this.head = node;
            }
            else
            {
                Node n = this.head;

                while (n.next != null)
                {
                    n = n.next;
                }

                n.next = node;

            }
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
            Node n = this.head;     //this.head can be null in case of empty linked list

            while(n != null)
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

    public void insertAtStart(int data)
    {
        try
        {
            Node node = new Node();

            node.data = data;

            node.next = null;

            node.next = this.head;

            this.head = node;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void insertAt(int index, int data)
            //4 conditions exist
            //When linkedlist is empty -> insertAtStart
            //When linkedlist is not empty & insert at 0 index -> insertAtStart
            //When linkedlist is not empty & insert within range -> given logic
            //When linkedlist is not empty & insert at higher indexes -> add n.next != null
    {
        try
        {
            Node node = new Node();

            node.data = data;

            Node n = this.head;

            if(index==0 || this.head == null)
            {
                insertAtStart(data);
            }
            else
            {
                for (int i=0; i<index-1 && n.next!=null ; i++)
                //this n.next!=null is present to make sure there is no error
                // even in case the index is greated than inserted nodes.
                {
                    n = n.next;
                }

                node.next = n.next;

                n.next = node;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void deleteAt(int index)
            //3 conditions exist
            //when index 0
            //when index within range
            //when index greater than total nodes
    {
        try
        {
            if(index == 0)                                  //to deal with empty linked list
            {
                this.head = this.head.next;
            }
            else
            {
                Node n = this.head;

                for (int i = 0; i < index - 1; i++)
                {
                    if(n.next.next != null)                 //to deal with index values >total
                    {
                        n = n.next;
                    }
                    else
                    {
                        System.out.format("Index %d position out of bound.", index);

                        return;
                    }
                }

                System.out.println("Deleted element at index "+index+ ": "+n.next.data);

                n.next = n.next.next;
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
