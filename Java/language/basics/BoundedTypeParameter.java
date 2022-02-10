package com.java.language.basics;

class Server<T>
{
    private T data;

    public void set(T t)
    {
        this.data = t;
    }

    public T get()
    {
        return data;
    }

    public <U extends Number> void inspect(U u)
    {
        System.out.println("T: "+ data.getClass().getName());

        System.out.println("U: " + u.getClass().getName() );
    }

    public <T extends Comparable<T>> int countGreaterThan(T[] arr, T value)
    {
        int count=0;

        for (T obj: arr)
        {
            if(obj.compareTo(value) > 0)
            {
                count++;
            }
        }

        return count;
    }

//    public interface Comparable<T>
//    {
//        public int compareTo(T o);
//    }

}


public class BoundedTypeParameter
{
    public static void main(String[] args)
    {
        try
        {
            Server<Integer> s1 = new Server<>();

            s1.set(20);

            System.out.println(s1.get());

            s1.inspect(10.0);

            Integer arr1[] = {-10,10,20,40};

            int result = s1.countGreaterThan(arr1, 10);

            System.out.println(result);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
