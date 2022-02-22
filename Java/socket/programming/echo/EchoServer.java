package com.java.socket.programming.echo;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.io.PrintWriter;

import java.net.ServerSocket;

import java.net.Socket;

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

            serverSocket = new ServerSocket(portNumber);

            client = serverSocket.accept();

            out = new PrintWriter(client.getOutputStream(), true);

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

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
