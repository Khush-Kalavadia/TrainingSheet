package com.java.collections;

import java.util.Map;

import java.util.SortedMap;
import java.util.TreeMap;

public class LearnTreeMap
{
    public static void main(String[] args)
    {
        try
        {
            //here operations take place in O(log(n)) time.
            Map<Integer, String> map = new TreeMap<>();
//            SortedMap<Integer, String> map = new TreeMap<>();     //TreeMap implements SortedMap

            map.put(2, "two");

            map.put(1,"one");

            map.put(3, "three");       //present in sorted order

            System.out.println(map);

            //same operations as we did in HASHMAP

            map.put(2, "four");     //value at the key 2 would be updated.

            System.out.println(map);

            if(!map.containsKey(3))        //it contains key so won't change value of key 3
            {
                map.put(3,"five");
            }

            System.out.println(map);

            map.putIfAbsent(3, "six");      //it is present so won't overwrite

            System.out.println(map+"\n");

            //ITERATION
            for (Map.Entry<Integer, String> m: map.entrySet())
            {
                System.out.print(m+"\t");

                System.out.println(m.getKey() +" "+m.getValue());
            }

            System.out.println();

            for(Integer i: map.keySet())
            {
                System.out.print(i+", ");
            }

            System.out.println();

            for(String s: map.values())
            {
                System.out.print(s+", ");
            }

            System.out.println("\n"+map.isEmpty());

            System.out.println(map.containsKey(12));

            System.out.println(map.containsValue("one"));

            map.remove("four");         // in remove a key is passed and the set is deleted
                                            // even key, value can be passed

            System.out.println(map);


        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
