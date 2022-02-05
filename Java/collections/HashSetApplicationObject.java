package com.java.collections;

import java.util.HashSet;

import java.util.Set;

class Application
{
    String name;
    
    int portNo;

    public Application(String name, int portNo) 
    {
        this.name = name;
    
        this.portNo = portNo;
    }

    @Override
    public String toString() 
    {
        return "Application{" +
                "name='" + name + '\'' +
                ", portNo=" + portNo +
                '}';
    }

    @Override
    public boolean equals(Object o) 
    {
        if (this == o) return true;
        
        if (o == null || getClass() != o.getClass()) return false;

        Application application = (Application) o;

        return this.portNo == application.portNo;
    }

    @Override
    public int hashCode() 
    {
        System.out.println("Hash code generated internally");
    
        return this.portNo;
    }
}

public class HashSetApplicationObject
{
    public static void main(String[] args)
    {
        try
        {
            Set<Application> appSet = new HashSet<>();

            appSet.add(new Application("chrome",1));

            appSet.add(new Application("calculator",2));

            appSet.add(new Application("teams",3));

//        //won't consider this as unique when EQUALS method is not present
//        // becuase this is a NEW object

            System.out.println("\nApplication added (folder,2):" + appSet.add(new Application("folder",2)));

            for (Application s1:appSet)
            {
                System.out.println(s1);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

}
