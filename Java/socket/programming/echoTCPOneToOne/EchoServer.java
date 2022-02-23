package com.java.socket.programming.echoTCPOneToOne;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.io.PrintWriter;

import java.net.*;

public class EchoServer
{
    public static void main(String[] args)
    {
        ServerSocket serverSocket = null;

        Socket client = null;

        PrintWriter out = null;

        BufferedReader in = null;

        try
        {
            if (args.length != 1)
            {
                System.err.println("Wrong syntax");

                System.exit(1);
            }
            int portNumber = Integer.parseInt(args[0]);

            // Way 1
//            serverSocket = new ServerSocket(portNumber);

            // Way 2
            SocketAddress socketAddress = new InetSocketAddress(portNumber);

            serverSocket = new ServerSocket();

            serverSocket.bind(socketAddress);           //binding the  socket with the inetAddress and port number

            System.out.println("IP address: "+serverSocket.getInetAddress());

            System.out.println("Port: "+serverSocket.getLocalPort());

            client = serverSocket.accept();

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            out = new PrintWriter(client.getOutputStream(), true);

            String inputLine;

            while ((inputLine = in.readLine()) != null)
            {
                out.println(inputLine);
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

                if (out != null)
                {
                    out.close();
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
}
