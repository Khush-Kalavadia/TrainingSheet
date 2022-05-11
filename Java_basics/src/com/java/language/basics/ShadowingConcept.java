package com.java.language.basics;

public class ShadowingConcept
{
    int x = 0;

    class FirstLevel
    {
        int x = 1;

        void methodInFirstLevel(int x)
        {
            System.out.println("x = " + x);

            System.out.println("this.x = " + this.x);

            System.out.println("ShadowConcept.this.x = " + ShadowingConcept.this.x);
        }
    }

    public static void main(String... args)
    {
        try
        {
            ShadowingConcept st = new ShadowingConcept();

            ShadowingConcept.FirstLevel fl = st.new FirstLevel();

            fl.methodInFirstLevel(20);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }



    }
}
