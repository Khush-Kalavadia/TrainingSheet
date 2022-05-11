package com.java.concurrency;

public class MyThread extends Thread
{       //thread is class so child class uses extends

    public void run()
    {
        System.out.println("Current thread name: " + Thread.currentThread().getName());

        System.out.println("Method running");

        System.out.println("Method ends");
    }
}