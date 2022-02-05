package com.java.collections;

import java.util.LinkedHashSet;
import java.util.Set;

public class LearnLinkedHashSet
{
    public static void main(String[] args)
    {
        try
        {
            Set<Integer> linkedHashSet = new LinkedHashSet<>();

            linkedHashSet.add(32);

            linkedHashSet.add(2);

            linkedHashSet.add(54);

            linkedHashSet.add(21);

            linkedHashSet.add(65);

            linkedHashSet.add(65);        //again not added

            System.out.println(linkedHashSet);    //Now order maintained as it is "LINKED"hashset

            linkedHashSet.remove(54);

            System.out.println(linkedHashSet);

            System.out.println(linkedHashSet.contains(100));

            System.out.println(linkedHashSet.contains(21));

            System.out.println("Empty:" + linkedHashSet.isEmpty());

            System.out.println("Size:" + linkedHashSet.size());

            linkedHashSet.clear();

            System.out.println(linkedHashSet);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
