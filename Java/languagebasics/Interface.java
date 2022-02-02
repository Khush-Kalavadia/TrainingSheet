package com.java.languagebasics;

public class Interface {

    public static void main(String args[])
    {
        try
        {
            A6 obj = new A6();

            obj.print();

            System.out.println(A6.price);

//            System.out.println(A6.new_price);    //can't access as variable in this class is not static bydefault

            System.out.println(obj.getPrice());

            obj.myFunction();

            printable.newFunction();

//            A6.newFunction();           //not possible
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}

interface printable
{
    int price = 10;     //bydefault public

    void print();       //bydefault public, static, final

    default void myFunction()
    {
        System.out.println("Implemented default method");
    }

    static void newFunction()
    {
        System.out.println("Implemented static method");
    }
}

class A6 implements printable
{
    protected int new_price;

    public void print()
    {
        System.out.println("Hello");
    }

    A6()
    {
        this.new_price = price*6;
    }

    int getPrice()
    {
        return this.new_price;
    }
}
