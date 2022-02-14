package com.java.concurrency;

public class SynchronizedExchanger
{
    protected Object object = null;

    //All the blocks mentioned over here are set on the same object
    //Hence, at a time only one of the method can be accessed by a thread

    public synchronized void setObject(Object object)
    {
        this.object = object;
    }

    public synchronized Object getObject()
    {
        return object;
    }

    public void setObj(Object obj)
    {
        synchronized (this)
        {
            this.object = obj;
        }
    }

    public Object getObj()
    {
        synchronized (this)
        {
            return this.object;
        }
    }
}
