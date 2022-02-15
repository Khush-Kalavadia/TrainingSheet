package com.java.concurrency;

public class Reentrance
{
    private int count = 0;

    //Both methods are synchronized on the same object.
    //It is the same thread that calls both object.
    // Monitor Object holding incAndGet is allowed to enter a new synchornized block which is
    // synchronized on the same monitor object.

    public synchronized void inc()
    {
        try
        {
            this.count++;

            Thread.sleep(1000);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public synchronized int incAndGet()
    {
        try
        {
            inc();

            return this.count;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    public synchronized int getCount()
    {
        try
        {
            return this.count;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return -1;
    }
}
