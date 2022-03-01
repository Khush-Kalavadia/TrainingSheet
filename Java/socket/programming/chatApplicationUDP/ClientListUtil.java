package com.java.socket.programming.chatApplicationUDP;

import com.sun.xml.internal.ws.api.message.Packet;

import java.net.DatagramPacket;

import java.util.concurrent.ConcurrentHashMap;

public class ClientListUtil
{
    public static final ConcurrentHashMap<String, DatagramPacket> clientList = new ConcurrentHashMap<>();

    public static void add(String username, DatagramPacket packet)
    {
        try
        {
            clientList.put(username, packet);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static boolean containsUsername(String username)
    {
        try
        {
            return clientList.containsKey(username);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    public static DatagramPacket getPacket(String username)
    {
        try
        {
            if (ClientListUtil.containsUsername(username))
            {
                return clientList.get(username);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
