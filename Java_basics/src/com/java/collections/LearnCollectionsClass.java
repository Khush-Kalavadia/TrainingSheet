package com.java.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LearnCollectionsClass
{
    public static void main(String[] args)
    {
        try
        {
            List<Integer> list = new ArrayList<>();

            list.add(1);

            list.add(3);

            list.add(7);

            list.add(2);

            list.add(3);

            System.out.println("Min term: "+ Collections.min(list));

            System.out.println("Max term: "+ Collections.max(list));

            System.out.println("\nFrequency of 3: "+ Collections.frequency(list, 3));

            Collections.sort(list);

            System.out.println(list);

            Collections.sort(list, Comparator.reverseOrder());

//            list.sort(Comparator.reverseOrder());             //other way

            System.out.println(list);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
