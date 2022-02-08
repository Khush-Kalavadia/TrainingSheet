package com.java.datastructures;

public class StackUsingArray
{
    protected int[] data;

    protected static final int DEFAULT_SIZE = 3;

    int ptr = -1;

    public StackUsingArray()
    {
        this(DEFAULT_SIZE);             //better replacement of this.items = new int[DEFAULT_SIZE]
    }

    public StackUsingArray(int size)
    {
        this.data = new int[size];
    }

    public boolean push(int val)
    {
        try
        {
            if (isFull())
            {
                System.out.println("Stack is full");

                return false;
            }
            else
            {
                ptr += 1;

                data[ptr] = val;

                return true;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    public int pop()
    {
        int value = 0;

        try
        {
            if (isEmpty())
            {
                throw new StackException("Cannot pop from empty stack.");
            }
            else
            {
                ptr -= 1;

                value = data[ptr + 1];
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            try
            {
                throw new StackException("Exception captured from pop method.");
            }
            catch (Exception ignore)
            {
            }

        }

        return value;
    }


    public int peek()
    {
        int value = 0;
        try
        {
            if (isEmpty())
            {
                throw new StackException("Cannot peek from empty stack.");
            }
            else
            {
                value = data[ptr];
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return value;
    }

    public void popAllShow()        //empties stack and prints all elements
    {
        try
        {
            while (!isEmpty())
            {
                System.out.println(pop());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    protected boolean isFull()                    //this is used internally hence private
    {
        if (ptr == (DEFAULT_SIZE - 1))            //pointer is at last index
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean isEmpty()
    {
        return ptr == -1;
    }
}
