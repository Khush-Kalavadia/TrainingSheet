package com.java.data.structures;

public class QueueUsingArray
{
    private final int DEFAULT_SIZE = 10;

    protected int[] items;

    protected int front;

    protected int rear;

    protected int itemCount = 0;

    public QueueUsingArray()
    {
        this.items = new int[DEFAULT_SIZE];

        front = -1;

        rear = -1;
    }

    public QueueUsingArray(int size)
    {
        this.items = new int[size];

        rear = -1;

        front = -1;
    }

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
            else if (rear == items.length - 1)
            {
                throw new Exception("Can't add element as it has reached end of array");
            }
            else
            {
                rear += 1;

                items[rear] = val;

                itemCount++;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    public int dequeue()
    {
        int val = 0;
        try
        {
            if (rear == -1)
            {
                throw new Exception("Can't dequeue a empty queue");
            }
            else if (rear == front)
            {
                val = items[front];

                rear = -1;

                front = -1;
            }
            else
            {
                val = items[front];

                front = front + 1;

                itemCount--;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return val;

    }

    @Override
    public String toString()
    {
        String str = "";
        try
        {
            if (rear == -1)
            {
                throw new Exception("Empty queue");
            }


            for (int i = front; i <= rear; i++)
            {
                str += items[i] + " ";
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return str;
    }

    public int getFront()
    {
        return this.front;
    }

    public int getRear()
    {
        return rear;
    }

    public int getItemCount()
    {
        return itemCount;
    }
}
