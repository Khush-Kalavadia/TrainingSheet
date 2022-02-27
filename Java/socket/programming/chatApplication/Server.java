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

receive client -> server accept

check client
 N - find client -> socket find and put it in hashmap
 Y - send msg

*/

/*
client -> Socket(serverPort, serverAddress)

client1>

Ready to receive
 */

/*
work flow 1 - all clients present - from client to server to receiver
work flow 2 - all clinets not present
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
//            new Thread(new ServerNewClientRunnable(address, port)).start();

            ServerSocket serverSocket = null;

            Socket client = null;

            BufferedReader in = null;

            BufferedWriter out = null;

            Socket receiverSocket = null;

            String receivedData;

            try
            {
                serverSocket = new ServerSocket(port, 50, address);

                while (true)
                {
                    client = serverSocket.accept();

                    in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                    receivedData = in.readLine();

                    System.out.println("Entered: " + MessageUtil.getNewClientName(receivedData));

                    ClientListUtil.add(MessageUtil.getNewClientName(receivedData), client);

                    new Thread(new ServerClientInputRunnable(in)).start();

//                    client = null;
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
                    if (receiverSocket != null)
                    {
                        receiverSocket.close();
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
