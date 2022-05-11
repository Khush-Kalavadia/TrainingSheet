package com.java.collections;

import java.util.HashSet;

import java.util.Set;

public class LearnHashSet
{
    public static void main(String[] args)
    {
        try
        {
            Set<Integer> set = new HashSet<>();

//            HashSet<Integer> set = new HashSet<>();
//            both works as child class HashSet does not have new methods it just overrides except clone method

            set.add(32);

            set.add(2);

            set.add(54);

            set.add(21);

            set.add(65);

            System.out.println("Possible to putValue 54 again = "+set.add(54));    //duplicate values not present

            System.out.println(set);        //order not maintained as it is HASHSET

            set.remove(21);

            System.out.println(set);

            System.out.println(set.contains(100));

            System.out.println(set.contains(2));

            System.out.println("Empty:"+set.isEmpty());

            System.out.println("Size:"+set.size());

            set.clear();

            System.out.println(set);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

}
