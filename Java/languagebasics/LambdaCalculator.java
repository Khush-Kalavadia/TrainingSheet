package com.java.languagebasics;

public class LambdaCalculator
{
    interface IntegerMath
    {
        int opeartion(int a, int b);
    }

    public static void main(String[] args)
    {
        try
        {
            LambdaCalculator cal = new LambdaCalculator();

            IntegerMath add = new IntegerMath()         //way 1 anonymous inner class
            {
                @Override
                public int opeartion(int a, int b) {

                    return a+b;
                }
            };

            System.out.println(add.opeartion(1,2));

            IntegerMath sub = (a,b) -> a-b;         //way 2 better lambda

            System.out.println(sub.opeartion(3,1));
        }

        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
