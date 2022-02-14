package com.java.concurrency;

public class CounterSynchronized
{
    private long count = 0;

    //critical section. If not done together then it would lead to race condition.
    public synchronized long incAndGet()
    {
//        synchronized (this)   //way to specify the adding and returning as one atomic operation
//        {
        this.count++;

        return this.count;
//        }
    }

    public synchronized long getCount()
    {
//        synchronized (this)
//        {
        return this.count;
//        }
    }
}


