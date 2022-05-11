package com.java.socket.programming.chatApplicationTCP;

import java.io.*;

import java.net.InetAddress;

import java.net.Socket;

public class Client
{
    private int serverPort;

    private InetAddress serverAddress;

    private String clientName;

    public Client(String clientName, InetAddress serverAddress, int serverPort)
    {
        this.serverPort = serverPort;

        this.clientName = clientName;

        this.serverAddress = serverAddress;
    }

    void startClient()
    {
        try
        {
            Socket socket = null;

            BufferedReader enter = null;

            BufferedWriter out = null;

            String message;

            try
            {
                socket = new Socket(serverAddress, serverPort);

                System.out.println(clientName + " created :" + socket);

                System.out.println("Enter in format: <username> > <message>");

                System.out.println("---------------------------------------");

                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                out.write(clientName);

                out.newLine();

                out.flush();

                new Thread(new ClientReceiverRunnable(socket)).start();

                while (true)
                {
                    enter = new BufferedReader(new InputStreamReader(System.in));

                    message = enter.readLine();

                    out.write(MessageUtil.getDataToSend(clientName, message));

                    out.newLine();

                    out.flush();

//                enter.close();
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
                    if (enter != null)
                    {
                        enter.close();
                    }
                    if (out != null)
                    {
                        out.close();
                    }
                    if (socket != null)
                    {
                        socket.close();
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
}
