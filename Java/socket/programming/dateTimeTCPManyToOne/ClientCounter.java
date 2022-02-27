package com.java.socket.programming.dateTimeTCPManyToOne;

import java.util.concurrent.atomic.AtomicInteger;

public class ClientCounter
{
    private static AtomicInteger count;

    public ClientCounter()      //not mandatory
    {
        count = new AtomicInteger(0);
    }

    public int getAndIncClientCount()
    {
        return count.getAndIncrement();
    }

    public int incAndGetClientCount()
    {
        return count.incrementAndGet();
    }

    public void decClientCount()
    {
        count.decrementAndGet();
    }

    public int get()
    {
        return count.get();
    }
}
