package com.java.language.basics;

class localInner
{
    private int data = 30;//instance variable

    void display()
    {
        class Local
        {
            void msg()
            {
                System.out.println(data);
            }
        }

        Local l = new Local();

        l.msg();
    }
}

abstract class Person
{
    abstract void eat();
}


public class LocalAndAnonymousInnerClass
{
    public static void main(String[] args)
    {
        try
        {
            localInner obj = new localInner();  //local inner class

            obj.display();

            Person p=new Person()               //anonymous inner class
            {
                void eat()
                {
                    System.out.println("nice fruits");
                }
            };

            p.eat();
        }

        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
