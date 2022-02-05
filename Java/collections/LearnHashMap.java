package com.java.collections;

import java.util.Collections;
import java.util.HashMap;

import java.util.Map;

public class LearnHashMap
{
    public static void main(String[] args)
    {
        try
        {
//        here operations take place in O(n) time.
//        Format -> Map<key, value>
            Map<Integer, String> str = new HashMap<>();

            str.put(1, "One");

            str.put(2, "Too");

            str.put(3, "Three");

            System.out.println(str);    //order not maintained

            str.put(2,"Two");           //values updated

            System.out.println("str.put(2,\"Two\") -> "+str);

            if(!str.containsKey(3))         //similarly there is containsValue("One")
            {
                str.put(3, "Third");        //will not be replaced
            }

            System.out.println("containsKey(3) -> "+str);

            str.putIfAbsent(1, "ONE");

            System.out.println("str.putIfAbsent(1, \"ONE\") -> "+str);

            str.putIfAbsent(4, "Four");

            System.out.println("str.putIfAbsent(4, \"Four\") -> "+str);     //checks key

            System.out.println("\nPrinting sets");

            for(Map.Entry<Integer, String> e: str.entrySet())   //returns a set of user defined format
            {
                System.out.println(e);

                System.out.println(e.getValue());

                System.out.println(e.getKey());
            }

            System.out.println("\nPrinting keys");

            for (Integer key: str.keySet())
            {
                System.out.println(key);
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
