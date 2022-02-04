package com.java.collections;

import java.util.*;

public class LearnArrayList
{
    public static void main(String[] args)
    {
        try
        {
            List<Integer> list = new ArrayList<>();             //way 1

            ArrayList<Integer> list1 = new ArrayList<>();       //way 2

            list1.add(1);

            list1.add(null);

            list1.add(9);

            list1.add(6);

            System.out.println("list1 : "+list1);

            list.add(12);

            list.add(new Integer(5));         //different ways of assigning integer

            list.addAll(list1);                     //can add complete arraylist

            System.out.println("list  : "+list);

            list.set(1, 100);                      //replaces value at given INDEX

            System.out.println("list  : "+list);

            list.add(2,5);          //pushes other element index 2 onwards

            System.out.println("list  : "+list);

            list.remove(4);                 //remove at given index

            System.out.println("list  : "+list);

            list.remove(Integer.valueOf(12));     //remove at given value

            System.out.println("list  : "+list);

            int n = list.get(4);                    //at index 4

            System.out.println(n);

            System.out.println(list.contains(9));                  //returns true or false

            System.out.println(list.indexOf(101));                 //finds index of value

//            System.out.println(list.lastIndexOf(5));            //finds last index of value 5

            System.out.println("\n-----Iterations-----");

            Iterator<Integer> it = list.iterator();     //iterator. Can iterate only in forward direction

            while(it.hasNext())                     //way 1 of iteration
            {
                Object next = it.next();

                System.out.print(next+ "\t");
            }

            System.out.println("");

            for(int element: list)                  //way 2
            {
                System.out.print(element+ "\t");
            }

            System.out.println("");

            for(int i=0; i<list.size(); i++)        //way 3
            {
                System.out.print(list.get(i)+ "\t");
            }

            System.out.println("\n\n");

            ListIterator listIt = list.listIterator();      //in iterate in both direction

            System.out.println(listIt.next());

            System.out.println(listIt.next());

            System.out.println(listIt.next());

            System.out.println(listIt.previous());

            System.out.println(listIt.previous());

            System.out.println(listIt.previous());

            list.clear();                               //empty the list

            System.out.println("\n"+list);

        /*
        List<Integer> sublist = list.subList(1, 3);          //sublist

        Set<Integer> set = new HashSet<>();                  //java list to set
        set.addAll(list);

        Object[] objects = list.toArray();                  //list to normal array

        Collections.sort(list);                             //sorting

        Stream<String> stream = stringList.stream();
        stream.forEach( element -> { System.out.println(element); });       //iteration using stream
        */

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }



}
