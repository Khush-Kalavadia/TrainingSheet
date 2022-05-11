package com.java.language.basics;

public class OrderExecutionBlocks {

    //constructor
    OrderExecutionBlocks()
    {
        System.out.println("Constructor block");
    }

    //this is instance initializer block
    {
        System.out.println("IIB block");
    }

    static
    {
        System.out.println("Static block");
    }

    public static void main(String[] args)
    {
        try
        {
            System.out.println("Working in main\n");

            OrderExecutionBlocks object1 = new OrderExecutionBlocks();

            OrderExecutionBlocks object2 = new OrderExecutionBlocks();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
