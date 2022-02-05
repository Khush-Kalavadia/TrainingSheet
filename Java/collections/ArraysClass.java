package com.java.collections;

import java.util.Arrays;

public class ArraysClass
{
    public static void main(String[] args)
    {
        try
        {
            int[] numbers = {2, 6, 3, 6, 3, 12, 3, 7, 57, 8, 43};

            for (int i : numbers)
            {
                System.out.print(i + " ");
            }

            int index = Arrays.binarySearch(numbers, 6);    //sorted array not a mandate
                                                                //any occurrence of 6

            System.out.println("\n" + index);

            Arrays.sort(numbers);

            for (int i : numbers)
            {
                System.out.print(i + " ");
            }

            index = Arrays.binarySearch(numbers, 3);    //any occurrence of 3

            System.out.println("\n" + index);

            Arrays.fill(numbers, 2, 5, 0);

            for (int i : numbers)
            {
                System.out.print(i + " ");
            }

            System.out.println();

            Arrays.fill(numbers, 1);

            for (int i : numbers)
            {
                System.out.print(i + " ");
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
