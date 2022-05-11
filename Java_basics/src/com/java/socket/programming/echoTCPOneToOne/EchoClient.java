package com.java.socket.programming.echoTCPOneToOne;

//Open the folder using cd ~/IdeaProjects/Java_basics/src
//to compile: javac com/java/socket/programming/echoTCPOneToOne/EchoClient.java
//to run:     java com.java.socket.programming.echoTCPOneToOne.EchoClient 0.0.0.0 8080

import java.io.*;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class EchoClient
{
    public static void main(String[] args)
    {
        Socket echoSocket = null;

        BufferedWriter out = null;

        BufferedReader in = null;

        BufferedReader entered = null;

        try
        {
            //create socket
            //in out stream
            //read and write
            //close stream
            //close socket

            if (args.length != 2)
            {
                System.err.println("Wrong syntax");

                System.exit(1);
            }

            String hostname = args[0];

            int portNumber = Integer.parseInt(args[1]);

            //Creates a stream socket and connects it to the specified port number at the specified IP address.
            echoSocket = new Socket(hostname, portNumber);

            //port:      The port number on the remote host to which this socket is connected.
            //localport: The local port number to which this socket is connected.
            System.out.println("Client socket: " + echoSocket);     //observe socket

            entered = new BufferedReader(new InputStreamReader(System.in));

            // #1 using PrintWriter
//            out = new PrintWriter(echoSocket.getOutputStream(), true);

            // #2 using BufferedWriter
            out = new BufferedWriter(new OutputStreamWriter(echoSocket.getOutputStream()));

            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

            String userInput;

            System.out.println("Client executing:");

            while ((userInput = entered.readLine()) != null)
            {
                // #1
//                out.println(userInput);

                // #2
                out.write(userInput);

                out.newLine();

                out.flush();


                System.out.println("Echo: " + in.readLine());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            System.exit(1);
        }
        finally
        {
            try
            {
                if (echoSocket != null)
                {
                    echoSocket.close();
                }

                if (out != null)
                {
                    out.close();
                }

                if (in != null)
                {
                    in.close();
                }

                if (entered != null)
                {
                    entered.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();

                System.exit(1);
            }
        }
    }
}
