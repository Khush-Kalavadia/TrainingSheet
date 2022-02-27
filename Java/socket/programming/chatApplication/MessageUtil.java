package com.java.socket.programming.chatApplication;

import java.net.InetAddress;

import java.net.ServerSocket;

class MessageUtil
{
    static String getDataToSend(String senderName, String receiverName, String message)
    {
        try
        {
            return senderName + ">" + receiverName + "#" + message;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


    static String getSenderName(String receivedData)
    {
        try
        {
            return receivedData.substring(0, receivedData.indexOf('>'));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    static String getReceiverName(String receivedData)
    {
        try
        {
            return receivedData.substring(receivedData.indexOf('>') + 1, receivedData.indexOf('#'));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    static String getMessage(String receivedData)
    {
        try
        {
            return receivedData.substring(receivedData.indexOf('#') + 1, receivedData.length());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    static String getNewClientData(String senderName, InetAddress senderAddress, int port)
    {
        try
        {
            return senderName + "(" + senderAddress.getHostAddress() + "," + port + ")";
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    static String getNewClientName(String newClientData)
    {
        try
        {
            return newClientData.substring(0, newClientData.indexOf('('));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    static InetAddress getNewClientAddress(String newClientData)
    {
        try
        {
            return InetAddress.getByName(newClientData.substring(newClientData.indexOf('(') + 1, newClientData.indexOf(',')));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    static int getNewClientPort(String newClientData)
    {
        try
        {
            return Integer.parseInt(newClientData.substring(newClientData.indexOf(',') + 1, newClientData.length() - 1));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args)        //to check the utility
    {
        try
        {
            String msg = MessageUtil.getDataToSend("client1", "client2", "hiii guys");

            System.out.println(msg);

            System.out.println(MessageUtil.getSenderName(msg));

            System.out.println(MessageUtil.getReceiverName(msg));

            System.out.println(MessageUtil.getMessage(msg));

            msg = MessageUtil.getNewClientData("client1", InetAddress.getLocalHost(), 9001);

            System.out.println(msg);

            System.out.println(MessageUtil.getNewClientName(msg));

            System.out.println(MessageUtil.getNewClientAddress(msg));

            System.out.println(MessageUtil.getNewClientPort(msg));

            ServerSocket socket = new ServerSocket(MessageUtil.getNewClientPort(msg), 50, MessageUtil.getNewClientAddress(msg));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
