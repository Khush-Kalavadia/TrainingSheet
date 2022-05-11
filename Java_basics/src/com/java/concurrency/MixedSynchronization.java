package com.java.concurrency;

public class MixedSynchronization
{
    public Object instanceObject = null;

    public static Object staticObject = null;

    public synchronized void setInstanceObject(Object object)
    {
        this.instanceObject = object;
    }

    public synchronized Object getInstanceObject()
    {
        return this.instanceObject;
    }

    public static synchronized void setStaticObject(Object object)
    {
        staticObject = object;
    }

    public static synchronized Object getStaticObject()
    {
        return staticObject;
    }
}
