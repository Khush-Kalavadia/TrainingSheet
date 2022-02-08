package com.java.datastructures;


import java.util.Stack;

public class ParenthesisBalancing
{
    static boolean check(String str)
    {
        try
        {
            Stack<Character> s = new Stack<>();

            char chr;

            int i = 0;

            while (i < str.length())
            {
                chr = str.charAt(i);

                if (chr == '[' || chr == '(' || chr == '{')
                {
                    s.push(chr);
                }
                else if (chr == ')')
                {
                    if (s.isEmpty() || s.pop() != '(')
                        return false;
                }
                else if (chr == ']')
                {
                    if (s.isEmpty() || s.pop() != '[')
                        return false;
                }
                else if (chr == '}')
                {
                    if (s.isEmpty() || s.pop() != '{')
                        return false;
                }
                i++;
            }

            return s.isEmpty();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return false;
    }


    public static void main(String[] args)
    {
        String str = "[()]{}{[()()]()}";

        System.out.println(check(str));

        str = "{[()]{}{[()()]()}";

        System.out.println(check(str));

    }
}
