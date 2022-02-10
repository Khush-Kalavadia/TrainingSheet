package com.java.data.structures;

import java.util.Arrays;

public class CircularQueueUsingArray extends QueueUsingArray
{
    public CircularQueueUsingArray()
    {
        super();
    }

    public CircularQueueUsingArray(int size)
    {
        super(size);
    }

    @Override
    public void enqueue(int val)
    {
        try
        {
            if (rear == -1)
            {
                rear += 1;

                front += 1;

                items[rear] = val;

                itemCount++;
            }
            else if (itemCount == items.length)          //arr.length gives size of the array and not elements it has
            {
                throw new Exception("Queue is full can't enqueue.");
            }
            else
            {
                rear += 1;

                items[rear % items.length] = val;

                itemCount++;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString()
    {
        String str = "Printing the array\n";

        try
        {
            str += Arrays.toString(items);

            return str;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return str;
    }

    @Override
    public int dequeue()
    {
        int val = 0;
        try
        {
            if (rear == -1)
            {
                throw new Exception("Can't dequeue a empty queue");
            }
            else
            {
                val = items[front];

                items[front++] = 0;

                itemCount--;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return val;    }
}
