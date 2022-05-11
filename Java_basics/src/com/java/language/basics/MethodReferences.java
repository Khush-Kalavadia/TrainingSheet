package com.java.language.basics;

import java.util.LinkedList;

import java.util.List;

class myClass
{
    static void someFunc(int i)     //MUST be static
    {
        System.out.println("Print from someFunc: "+i);
    }
}

public class MethodReferences {

    public static void main(String[] args)
    {
        try
        {
            //lambda                ->                  Syntax: (parameter1, parameter2) -> { code block }
            //method reference      :: (double colon)   Syntax: <Class name>::<method name>

            List<Integer> ll = new LinkedList<>();

            ll.add(1);

            ll.add(4);

            ll.add(2);

            ll.add(-2);

            System.out.println(ll);

            ll.forEach(myClass::someFunc);    //method reference

            ll.forEach( i -> System.out.println("Lambda print: "+i) );

//        TO FIND THE SUMMATION
//        Integer sum1= ll.stream().collect(Collectors.summingInt(Integer::intValue));
//        System.out.println(sum1);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}

