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
            Map<Integer, String> stringHashMap = new HashMap<>();

            stringHashMap.put(1, "One");

            stringHashMap.put(2, "Too");

            stringHashMap.put(3, "Three");

            System.out.println(stringHashMap);    //order not maintained

            stringHashMap.put(2,"Two");           //values updated

            System.out.println("str.put(2,\"Two\") -> "+stringHashMap);

            if(!stringHashMap.containsKey(3))         //similarly there is containsValue("One")
            {
                stringHashMap.put(3, "Third");        //will not be replaced
            }

            System.out.println("containsKey(3) -> "+stringHashMap);

            stringHashMap.putIfAbsent(1, "ONE");

            System.out.println("str.putIfAbsent(1, \"ONE\") -> "+stringHashMap);

            stringHashMap.putIfAbsent(4, "Four");

            System.out.println("str.putIfAbsent(4, \"Four\") -> "+stringHashMap);     //checks key

            System.out.println("\nValue at key 3:"+stringHashMap.get(3));

            System.out.println("\nPrinting sets");

            for(Map.Entry<Integer, String> e: stringHashMap.entrySet())   //returns a set of user defined format
            {
                System.out.println(e);

                System.out.println(e.getValue());

                System.out.println(e.getKey());
            }

            System.out.println("\nPrinting keys");

            for (Integer key: stringHashMap.keySet())
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
