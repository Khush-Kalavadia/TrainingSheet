package com.java.concurrency;

public class Counter
{
    private long count = 0;

    public long incAndGet()
    {
        this.count++;

        return this.count;
    }

    public long getCount()
    {
        return this.count;
    }
}
