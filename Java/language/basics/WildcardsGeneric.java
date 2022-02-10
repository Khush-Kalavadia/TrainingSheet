package com.java.language.basics;

class Host<T>{}

class Network
{
    public void add(Host<?> h1){}       //can't mention Host<T> so ? which is unbounded wildcard
}

public class WildcardsGeneric
{
    public static void main(String[] args)
    {
        try
        {
            Network n1 = new Network();

            n1.add(new Host<Object>());

//            n1.add(new Host<Integer>());    //any object works

//            n1.add(new Host<String>());     //any object works

            Host<?> newHost;                //can also be used in declaration

//            Host myHost = new Host<?>();    //over here you can't use wildcard

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
