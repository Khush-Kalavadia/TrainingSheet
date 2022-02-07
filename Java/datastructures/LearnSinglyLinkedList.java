package com.java.datastructures;

public class LearnSinglyLinkedList
{
    public static void main(String[] args)
    {
        try
        {
            SinglyLinkedList sll = new SinglyLinkedList();

            System.out.println(sll.head);     //gives null as it is initialized if not then gives null pointer exception

            sll.insert(1);

            sll.insert(2);

            sll.insert(3);

            sll.insertAtStart(4);

            sll.insert(5);

            sll.insertAt(2, 6);

            sll.show();

            sll.deleteAt(2);

            sll.show();

            sll.deleteAt(6);

        }

        catch (Exception ex)
        {
            ex.printStackTrace();

        }



    }
}
