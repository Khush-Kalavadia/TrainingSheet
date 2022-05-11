package com.java.collections;

import java.util.ArrayList;

import java.util.Iterator;

import java.util.List;

class adder
{
    static void addTwo(int num)
    {
        System.out.println(num+2);
    }
}

public class LearnIterator
{
    public static void main(String[] args)
    {
        try {

            List<Integer> numbers = new ArrayList<>();

            numbers.add(5);

            numbers.add(9);

            numbers.add(8);

            numbers.add(10);

            numbers.add(4);

            numbers.add(2);

            numbers.forEach((n) ->
            {
                System.out.println(n);
            });

//            numbers.forEach(System.out::println);

            System.out.println("----- 2 added to elements after occurance of 8 ----");

            Iterator<Integer> it = numbers.iterator();

            while (it.hasNext()) {

                int n = it.next();

                if (n == 8) {

                    System.out.println(n);

                    it.forEachRemaining(adder::addTwo);   //using method reference

                    break;
                }

                System.out.println(n);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}


