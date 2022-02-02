package com.java.languagebasics;

import java.util.ArrayList;

import java.util.Collections;

import java.util.Comparator;

import java.util.List;

public class ComparatorStudentSorting
{
    public static void main(String[] args)
    {
        try {

            List<student> studentList = new ArrayList<>();

            studentList.add(new student(3, "def"));

            studentList.add(new student(1, "xyz"));

            studentList.add(new student(1, "fgh"));

            studentList.add(new student(2, "abc"));

            System.out.println("Unsorted:");

            System.out.println(studentList);

            System.out.println("Sorted based on name:");

//          WAY 1
//          Passing comparator using anonymous class

            Collections.sort(studentList, new Comparator<student>()
            {
                @Override
                public int compare(student o1, student o2)
                {
                    return o1.getName().compareTo(o2.getName());
                }
            });

//          WAY 2
//          LAMBDA makes that short in following way
//          Collections.sort(studentList, (o1, o2) -> o1.getName().compareTo(o2.getName()));

//          WAY 3
//          passing comparator with lambda which compares based on name
//          Collections.sort(studentList, Comparator.comparing(student -> student.getName()));

//          WAY 4
//          passing comparator with method reference which compares based on name
//          Collections.sort(studentList, Comparator.comparing(student::getName));

            System.out.println(studentList);

            //Compare by first name and then last name
            Comparator<student> compareByRollName = Comparator.comparing(student::getRoll).reversed()
                                                              .thenComparing(student::getName);

            Collections.sort(studentList, compareByRollName);

            System.out.println("Sorted based on reversed roll number and ascending name:");

            System.out.println(studentList);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
