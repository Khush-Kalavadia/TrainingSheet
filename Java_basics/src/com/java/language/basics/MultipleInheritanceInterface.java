package com.java.language.basics;

interface Interface1
{
    int cont = 10;

    void methodA();

    static void methodStatic()
    {
        System.out.println("The static method in Interface1");
    }

    default void sameMethod()
    {
        System.out.println("Same Method implemented from Interface1");
    }
}

interface Interface2
{
//    int cont = 20;          //can't keep this else shows error in obj.cont

    void methodB();

    default void sameMethod()
    {
        System.out.println("Same Method implemented from Interface2");
    }
}

public class MultipleInheritanceInterface implements Interface1, Interface2
{
    @Override
    public void methodA()
    {
        System.out.println("Method A");
    }

    @Override
    public void methodB()
    {
        System.out.println("Method B");
    }

    //same method present in both class so need to be implemented
    // else default methods are not required to do so.
    @Override
    public void sameMethod()
    {
        Interface1 int1 = new Interface1()      //to implement default method
        {
            @Override
            public void methodA()
            {
                System.out.println("Method A in anonymous class");
            }
        };

        Interface2 int2 = new Interface2()      //to implement default method
        {
            @Override
            public void methodB()
            {
                System.out.println("Method B in anonymous class");
            }
        };

        int1.sameMethod();

        int2.sameMethod();
    }


    public static void main(String[] args)
    {
        try
        {
            MultipleInheritanceInterface obj = new MultipleInheritanceInterface();

            System.out.println(cont);              //this class inherits Interface1 and hence it is constant value

            System.out.println(Interface1.cont);   //cont is public,static,final

            obj.methodA();               //method overridden in class

            obj.methodB();               //method overridden in class

            obj.sameMethod();

            Interface1 newObj = obj;

            newObj.sameMethod();              //MultipleInheritanceInterface overrides method in interface1

            Interface1.methodStatic();     //static can be directly called

//            obj.methodStatic();             //child class does not inherit static methods from interface

        }

        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
