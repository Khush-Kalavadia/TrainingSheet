package com.java.socket.programming.echoTCPOneToOne;

//Open folder using cd ~/IdeaProjects/Java_basics/src
//to compile: javac com/java/socket/programming/echoTCPOneToOne/EchoServer.java
// to run:    java com.java.socket.programming.echoTCPOneToOne.EchoServer 8080

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.*;

import java.net.*;

public class EchoServer
{
    public static void main(String[] args)
    {
        ServerSocket serverSocket = null;

        Socket client = null;

        BufferedWriter out = null;

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
            serverSocket = new ServerSocket(portNumber);            //by default sets loopback ip address which accepts both
            //localhost and the other ip assigned to the system

            System.out.println("Server socket: " + serverSocket);

            // Way 2
//            InetAddress hostname = InetAddress.getLocalHost();
//
//            serverSocket = new ServerSocket(portNumber, 50 , hostname);         //sets address by ourself, bydefault backlog is 50

            /*
            backlog is set to 50
            In the space of time between calls to accept(), incoming client connection requests
            are stored in a queue maintained by the operating system. Subsequent calls to accept()
            remove requests from this queue, or block if there are no waiting clients. The “backlog”
            argument controls the length of this queue.
            */

              // Way 3
//            SocketAddress socketAddress = new InetSocketAddress(portNumber);
//
//            serverSocket = new ServerSocket();
//
//            serverSocket.bind(socketAddress);           //binding the  socket with the bydefault inetAddress and port number


//            System.out.println("IP address: " + serverSocket.getInetAddress());
//
//            System.out.println("Port: " + serverSocket.getLocalPort());

            client = serverSocket.accept();

            System.out.println("serverSocket.accept(): " + client);             //observe this socket
                                                                                //port: received socket bind to this port
                                                                                //localport: port to which it is connected or communicating

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            // #1 using PrintWriter
//            out = new PrintWriter(client.getOutputStream(), true);

            // #2 using BufferedWriter
            out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            String inputLine;

            while ((inputLine = in.readLine()) != null)
            {
                System.out.println("Received: " + inputLine);

                // #1
//                out.println(inputLine);

                // #2
                out.write(inputLine);

                out.newLine();

                out.flush();


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



