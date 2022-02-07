package com.java.datastructures;

public class LearnCircularLinkedList
{
    public static void main(String[] args)
    {
        try
        {
            CircularLinkedList cll = new CircularLinkedList();

            cll.insert(1);

            cll.insert(2);

            cll.insert(3);

            cll.show();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
