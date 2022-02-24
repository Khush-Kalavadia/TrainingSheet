package com.java.socket.programming.DateTimeTCPOneToOne;

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

        Socket client = null;

        BufferedReader in = null;

        BufferedWriter out = null;

        DateFormat forDate = new SimpleDateFormat("dd/MM/yyyy");

        DateFormat forTime = new SimpleDateFormat("hh:mm:ss");

        try
        {
            int port = 9001;

            serverSocket = new ServerSocket(port);

            System.out.println("Waiting for the client: ");

            client = serverSocket.accept();

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            String str, result;

            Date date;

            while (true)
            {
                System.out.println("Waiting for input: ");

                str = in.readLine();

                date = new Date();

                switch (str)
                {
                    case "date":
                    {
                        result = forDate.format(date);

                        break;
                    }
                    case "time":
                    {
                        result = forTime.format(date);

                        break;
                    }
                    case "CloseServerSocket":
                    {
                        serverSocket.close();

                        client.close();

                        in.close();

                        out.close();

                        System.out.println("exit\nExiting server");

                        System.exit(0);
                    }
                    default:
                    {
                        result = "Invalid Input";

                        break;
                    }
                }
                out.write(result);

                out.newLine();

                out.flush();

                System.out.println(result + " sent");
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
                if (out != null)
                {
                    out.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
