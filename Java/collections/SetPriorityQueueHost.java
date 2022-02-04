package com.java.collections;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Host
{
    String domainName;

    int hostNumber;

    int priority;

    public Host(String domainName, int hostNumber, int priority)
    {
        this.domainName = domainName;

        this.hostNumber = hostNumber;

        this.priority = priority;
    }

    @Override
    public String toString()
    {
        return "Host{" +
                "domainName='" + domainName + '\'' +
                ", hostNumber=" + hostNumber +
                ", priority=" + priority +
                "}\n";
    }

    public Integer getPriority()            //here integer object is used else compareTo() won't work
    {
        return priority;
    }
}


public class SetPriorityQueueHost
{
    public static void main(String[] args)
    {
        try
        {
            Queue<Host> pq = new PriorityQueue<>(new Comparator<Host>()
            {
                @Override
                public int compare(Host o1, Host o2)
                {
//                    if(o1.priority>o2.priority)         //manually
//                    {
//                        return 1;
//                    }
//                    else if(o1.priority<o2.priority)
//                    {
//                        return -1;
//                    }
//                    return 0;

                    return o2.getPriority().compareTo(o1.getPriority());    //get highest priority host first

                }
            });

            pq.offer(new Host("geeksforgeeks.org",24, 9));

            pq.offer(new Host("google.com",2,40));

            pq.offer(new Host("wikipedia.org",36,3));

            pq.offer(new Host("motadata.com",127, 50));

            System.out.println(pq);

            System.out.println("\nHighest priority:\n"+pq.poll());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

}
