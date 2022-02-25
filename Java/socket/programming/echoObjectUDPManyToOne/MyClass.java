package com.java.socket.programming.echoObjectUDPManyToOne;

import java.io.Serializable;

public class MyClass implements Serializable
{
    private static final long serialVersionUID = -5399605122490343339L;

    private int value;

    private String string;

    MyClass(int value, String string)
    {
        this.value = value;

        this.string = string;
    }

    @Override
    public String toString()
    {
        return "MyClass{" +
                "value=" + value +
                ", string='" + string + '\'' +
                '}';
    }
}
