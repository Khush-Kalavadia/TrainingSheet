package com.java.socket.programming.chatApplication;

import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.IOException;

import java.io.InputStreamReader;

import java.net.InetAddress;

import java.net.ServerSocket;

import java.net.Socket;

/*
server -> serversocket(port, address)

hash map <clientName, Socket>

receive client and add to hash map

client send msg

server continuously listed -> get msg

server check receiver username in hashmap
 Y - find receiver's socket -> send msg
 N - not found

client continuously listen
*/

public class Server
{
    private int port;

    private InetAddress address;

    public Server(InetAddress address, int port)
    {
        this.port = port;

        this.address = address;
    }

    void startServer()
    {
        try
        {
            ServerSocket serverSocket = null;

            Socket client = null;

            BufferedReader in = null;

            String receivedUsername;

            try
            {
                serverSocket = new ServerSocket(port, 50, address);

                System.out.println("Server created: " + serverSocket);

                while (true)
                {
                    client = serverSocket.accept();

                    in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                    receivedUsername = in.readLine();

                    System.out.println("Entered: " + receivedUsername);

                    ClientListUtil.add(receivedUsername, client);

                    new Thread(new ServerInputFromClientRunnable(in)).start();
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
                    if (client != null)
                    {
                        client.close();
                    }
                    if (in != null)
                    {
                        in.close();
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        try
        {
            Server server = new Server(InetAddress.getLocalHost(), 9000);

            server.startServer();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
