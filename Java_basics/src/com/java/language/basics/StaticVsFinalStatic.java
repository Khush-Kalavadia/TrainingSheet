package com.java.language.basics;

public class StaticVsFinalStatic
{
    public static void main(String[] args)
    {
        User c1 = new User();

        c1.myMethod();

        User c2 = new User();

        c2.myMethod();
    }
}

class User
{
    public void myMethod()
    {
        Service.work();
    }
}

class Service
{
    static int counter = 0;         //even if different instance are created of user COUNTER is shared

    public static void work()       //it is accessing same or different method that we can't do practically
                                    //Actually using static it would create separate method but in final it uses the same method reference
    {
        Service.counter++;

        System.out.println(Service.counter);
    }
}

