package com.java.language.basics;

public class LambdaMultipleMethodInterface
{

    public static void main(String[] Args)
    {
        try
        {
            myFunction func = (atext, btext) ->
            {
                System.out.println("Inside function");

                return atext + " " + btext;
            };

            String str = func.return_fun("Hey", "Guys");

            System.out.println(str);

            myFunction funcNew = func;

            System.out.println(funcNew.return_fun("Hi", "people"));

            func.my_fun();
        }

        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}

//functional interface = interface having only one abstract method but can have default methods.
//java lambda expression can only implement single method interface i.e. functional interface. Else it gets confused.
@FunctionalInterface
interface myFunction
{
    String return_fun(String text1, String text2);

    default void my_fun()
    {
        System.out.println("The default Method");
    }
}