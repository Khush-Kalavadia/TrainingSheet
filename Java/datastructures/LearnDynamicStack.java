package com.java.datastructures;

public class LearnDynamicStack
{
    public static void main(String[] args)
    {
        try
        {
            DynamicStack ds = new DynamicStack();

            ds.push(2);

            ds.push(4);

            ds.push(3);

            ds.push(9);

            ds.push(5);

            ds.push(1);

            ds.push(10);

            ds.popAllShow();

            System.out.println("Size of dynamic stack: "+ds.dynamicSize);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
