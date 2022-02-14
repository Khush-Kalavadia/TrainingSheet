package com.java.concurrency;

public class SubclassJavaThread
{
    public static void main(String[] args)
    {
        try
        {
            // -> Every time you run this program the sequence of execution of method
            // and the thread number would be different. The order in which the CPU and OS
            // are switching between the methods is not sure as they are running parallelly.

            // -> Will have 2 main as RUN will not create new thread.

            MyThread t1 = new MyThread();

            t1.start();               //it will execute the run method

            MyThread t2 = new MyThread();

            t2.run();

            MyThread t3 = new MyThread();

            t3.start();               //it will execute the run method

            MyThread t4 = new MyThread();

            t4.start();               //it will execute the run method

            MyThread t5 = new MyThread();

            t5.start();               //it will execute the run method

            MyThread t8 = new MyThread();

            t8.run();

        /*
        MyThread t = new MyThread();       //will give an error

        t.start();

        t.start();

        MyThread r = new MyThread();        // will not give error

        r.run();

        r.run();
        */

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}

