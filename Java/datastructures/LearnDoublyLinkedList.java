package com.java.datastructures;

public class LearnDoublyLinkedList
{
    public static void main(String[] args)
    {
        try
        {
            DoublyLinkedList dll = new DoublyLinkedList();

            dll.insertFirst(1);

            dll.insertFirst(2);

            dll.insertFirst(3);

            System.out.println("Print doubly linked list");

            dll.show();

            System.out.println("Print Reverse");

            dll.showReverse();

            dll.insertLast(4);

            System.out.println("Print doubly linked list");

            dll.show();

            dll.insertAfter(6,5);

            dll.insertAfter(4,5);

            System.out.println("After inserting 5 after node having value 4");

            dll.show();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
