package com.company;

import java.util.ArrayList;
import java.util.List;

public class Trial
{
    public static void main(String[] args)
    {
        List<Object> list = new ArrayList<>();

        list.add("hello");

        list.add(10.1);

        System.out.println(list.get(0).getClass().getName());

        System.out.println(list.get(1).getClass());
    }
}
