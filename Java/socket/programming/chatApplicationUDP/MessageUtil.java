package com.java.socket.programming.chatApplicationUDP;

public class MessageUtil
{

    static String getDataToSend(String senderName, String message)
    {
        try
        {
            return senderName + ">" + message.replace('>', '#');
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
}
