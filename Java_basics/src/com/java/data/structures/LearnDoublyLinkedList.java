package com.java.data.structures;

public class LearnDoublyLinkedList
{
    public static void main(String[] args)
    {
        try
        {
            DoublyLinkedList dll = new DoublyLinkedList();

            dll.insertFirst(1);

            dll.insertFirst(2);

            dll.insertFirst(1);

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

            System.out.println("After inserting 5 after node having data 4");

            dll.show();

            dll.deleteValue(1);

            System.out.println("Value 1 deleted");

            dll.show();

            dll.deleteValue(3);

            System.out.println("Value 3 deleted");

            dll.show();

            dll.deleteIndex(1);

            System.out.println("Index 1 deleted");

            dll.show();

            dll.deleteIndex(0);

            System.out.println("Index 0 deleted");

            dll.show();


        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
