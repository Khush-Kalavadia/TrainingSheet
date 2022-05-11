package com.java.language.basics;

import java.util.function.Consumer;

public class InterfacePassingObject
{
    private static Object object = null;

    public static synchronized void setObject(Object o)
    {
        object = o;
    }

    public static void consumeObject(Consumer consumer)
    {
        consumer.accept(object);

        Consumer consumer1 = new Consumer()      //this consumer1 is different from consumer
                                                 // as accept method of consumer was only overidden
        {
            @Override
            public void accept(Object o)
            {
                System.out.println("My new method: " + o);
            }
        };

        consumer1.accept(12);
    }


    public static void main(String[] args)
    {
        try
        {
            Object object1 = 123;

            setObject(object1);

            consumeObject((obj) ->      //over here obj works as Consumer object.
                                        // Consumer obj = new Consumer{ @overrides accept()
                                        // {.. Implementation we have mentioned here ..} }
            {

                System.out.println("Consumer's accept method: " + obj);

            });

            object1 = 1000;

            setObject(object1);

            consumeObject((obj) ->
            {

                System.out.println("Consumer's accept method: " + obj);

            });

        }

        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
