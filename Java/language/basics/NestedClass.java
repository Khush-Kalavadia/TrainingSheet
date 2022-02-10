package com.java.language.basics;


class OuterClass
{
    int value = 10;

    static int staticValue = 100;

    class InnerClass
    {
        int innerValue = 20;

//        static int staticInnerValue = 200;        //inner class being not static this can't be used

        int innerFunction()
        {
            System.out.println("Inside inner class method");

            return value;
        }

    }


    static class StaticNestedClass
    {
        static int staticValue = 200;

        void accessMembers(OuterClass outer)
        {
            System.out.println("Inside static nested class method");

//            System.out.println(value);  //Cannot make static reference to the non-static field

            System.out.println(outer.value);

            System.out.println(staticValue);
        }

    }

    public static void main(String[] args)
    {
        try
        {
            OuterClass outerObject = new OuterClass();

            OuterClass.InnerClass innerObject = outerObject.new InnerClass();

            System.out.println(outerObject.value);

            System.out.println(OuterClass.staticValue);

            System.out.println(innerObject.innerFunction());

            System.out.println(innerObject.innerValue);

            StaticNestedClass staticNestedObject = new StaticNestedClass();

            staticNestedObject.accessMembers(outerObject);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
