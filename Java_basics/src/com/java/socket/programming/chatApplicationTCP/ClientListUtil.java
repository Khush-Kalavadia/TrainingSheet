package com.java.socket.programming.chatApplicationTCP;

import java.net.Socket;

import java.util.concurrent.ConcurrentHashMap;

public class ClientListUtil
{
    public static final ConcurrentHashMap<String, Socket> clientList = new ConcurrentHashMap<>();

    public static void add(String username, Socket socket)
    {
        try
        {
            clientList.put(username, socket);
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

    public static Socket getSocket(String username)
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

    static void printClientList()
    {
        try
        {
            System.out.println(clientList.entrySet());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args)      //for check
    {
        try
        {
            Socket socket = new Socket();

            ClientListUtil.add("khush", socket);

            ClientListUtil.add("khush1", socket);

            System.out.println(clientList.entrySet());


        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


    }
}
