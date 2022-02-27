package com.java.socket.programming.dateTimeTCPManyToOne;

import java.io.*;

import java.net.InetAddress;

import java.net.Socket;

public class Client
{
    public static void main(String[] args)
    {
        Socket socket = null;

        BufferedReader enter = null;

        BufferedReader in = null;

        BufferedWriter out = null;

        try
        {
            InetAddress address = InetAddress.getLocalHost();

            int portNumber = 9001;

            socket = new Socket(address, portNumber);

//            System.out.println(socket);     //get port and localport

            enter = new BufferedReader(new InputStreamReader(System.in));

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            System.out.print("Type date, time, exit or exitServer: ");

            String str = enter.readLine();

            while (!(str.equals("exit") || str.equals("exitServer")))
            {
                out.write(str);

                out.newLine();

                out.flush();

                System.out.println("Received: " + in.readLine());       //try read instead of readLine

                System.out.print("Type date, time, exit or exitServer: ");

                str = enter.readLine();
            }
            out.write(str);             //will send exit or exitServer

            out.newLine();

            out.flush();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (socket != null)
                {
                    socket.close();
                }
                if (in != null)
                {
                    in.close();
                }
                if (out != null)
                {
                    out.close();
                }
                if (enter != null)
                {
                    enter.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
