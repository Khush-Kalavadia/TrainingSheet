package com.java.datastructures;

public class DynamicStack extends StackUsingArray
{
    int dynamicSize = DEFAULT_SIZE;       //in StackUsingArray it was fix but here it would change

    public DynamicStack()
    {
        super();            //will call StackUsingArray with default data
    }

    public DynamicStack(int size)
    {
        super(size);        //StackUsingArray of size
    }

    @Override
    public boolean push(int val)
    {
        try
        {
            if (isFull())
            {
                dynamicSize = data.length * 2;

                int temp[] = new int[dynamicSize];

                for (int i = 0; i < data.length; i++)
                {
                    temp[i] = data[i];
                }

                data = temp;

                ptr += 1;

                data[ptr] = val;
            }
            else
            {
                return super.push(val);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    protected boolean isFull()
    {
        return ptr == dynamicSize - 1;
    }
}
