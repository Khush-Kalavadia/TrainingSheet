package com.java.socket.programming.DateTimeTCPManyToOne;

import java.io.*;

import java.net.ServerSocket;

import java.net.Socket;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.Date;

public class Server
{
    public static void main(String[] args)
    {
        ServerSocket serverSocket = null;

        Socket tempSocket = null;                     //when accepting the count value might change so using temp
                                                //so that it can be assigned to the client with latest count

        Thread tempThread = null;

        Socket client[] = new Socket[100];      //setting limit on clients

        ClientHandler runnable;

        int port = 9001;

        ClientCounter counter = new ClientCounter();

        try
        {
            serverSocket = new ServerSocket(port);

            while (true)
            {
                System.out.println("Waiting for new client: ");

                tempSocket = serverSocket.accept();

                client[counter.get()] = tempSocket;

                runnable = new ClientHandler(client[counter.get()], counter);

                tempThread = new Thread(runnable);

//                tempThread.setDaemon(true);

                tempThread.start();

                System.out.println("Total clients: " + counter.incAndGetClientCount());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (serverSocket != null)
                {
                    serverSocket.close();
                }
                if (tempSocket != null)
                {
                    tempSocket.close();
                }
                for (int i = 0; i <= counter.get(); i++)
                {
                    if (client[i] != null)
                    {
                        client[i].close();
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
