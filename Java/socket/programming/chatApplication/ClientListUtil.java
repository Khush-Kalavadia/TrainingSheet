package com.java.socket.programming.chatApplication;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ClientListUtil
{
    public static final ConcurrentHashMap<String, Socket> clientList = new ConcurrentHashMap<>();

    public static void add(String username, Socket socket)
    {
        clientList.put(username, socket);
    }

    public static boolean containsUsername(String username)
    {
        return clientList.containsKey(username);
    }

    public static Socket getSocket(String username)
    {
        if (ClientListUtil.containsUsername(username))
        {
            try
            {
                return ClientListUtil.getSocket(username);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }


}
