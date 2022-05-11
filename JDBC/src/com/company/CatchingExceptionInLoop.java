package com.company;

public class CatchingExceptionInLoop
{
    public static void main(String[] args)
    {
        try
        {
            for (int i = 0; i < 4; i++)
            {
                System.out.println(i);
                try
                {
                    if (i == 1 || i == 2)
                    {
                        int j = 10 / 0;
                    }
                }
                catch (Exception ex)
                {
                    System.out.println("inner");

                    ex.printStackTrace();
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println("outer");

            ex.printStackTrace();
        }
    }
}
