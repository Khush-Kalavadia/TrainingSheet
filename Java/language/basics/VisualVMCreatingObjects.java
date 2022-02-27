package com.java.language.basics;

import sun.rmi.runtime.Log;

import java.util.ArrayList;

import java.util.List;

class Device
{
    private int ip;

    private String hostName;

    public int getIp()
    {
        return ip;
    }

    public void setIp(int ip)
    {
        this.ip = ip;
    }

    public String getHostName()
    {
        return hostName;
    }

    public void setHostName(String hostName)
    {
        this.hostName = hostName;
    }
}

public class VisualVMCreatingObjects
{
    public static void main(String[] args)
    {
        try
        {
            List<Device> arr = new ArrayList<>();

            System.out.println("Start creating objects");

            for (int i = 0; i < 10000000; i++)
            {
                arr.add(new Device());
            }
            System.out.println("Done");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
