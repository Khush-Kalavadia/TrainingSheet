package com.java.language.basics;

import java.util.ArrayList;

import java.util.Collections;

import java.util.List;

//implementing comparable interface of type student
class student implements Comparable<student>
{
    private int roll;

    private String name;

    student(int r, String s)
    {
        this.roll = r;

        this.name = s;
    }

    public String getName()
    {
        return name;
    }

    public int getRoll()
    {
        return roll;
    }

    //compareTo means comparison of object itself with ANOTHER.
    //compare in comparator means comparison of 2 different object
    @Override
    public int compareTo(student o)        //positive means this is bigger
    {
        return this.roll - o.roll;

//        return this.name.compareTo(o.name);   //ONLY ONE AT A TIME.

//        LIMITATION OF Comparable. So, comparator would be used so that both
//        roll and name can be used together to sort.
    }

    @Override
    public String toString()
    {
        return "student{" +
                "roll=" + roll +
                ", name='" + name + '\'' +
                '}';
    }
}

public class ComparableStudentSorting
{
    public static void main(String[] args)
    {
        List<student> stud = new ArrayList<>();

        try
        {
            stud.add(new student(3, "abc"));

            stud.add(new student(1, "def"));

            stud.add(new student(2, "xyz"));

            student s1 = new student(3, "abc");

            student s2 = new student(1, "def");

            System.out.println("Application 1 and 2 compareTo result: " + s1.compareTo(s2));

            System.out.println("Unsorted:");

            System.out.println(stud);

            System.out.println("Sorted:");

            Collections.sort(stud);

            System.out.println(stud);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

}
