package com.java.socket.programming.echo;

import java.io.*;

import java.net.Socket;

public class EchoClient
{
    public static void main(String[] args)
    {
        Socket echoSocket = null;

        PrintWriter out = null;

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

            echoSocket = new Socket(hostname, portNumber);

            out = new PrintWriter(echoSocket.getOutputStream(), true);

            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

            entered = new BufferedReader(new InputStreamReader(System.in));

            String userInput;

            while ((userInput = entered.readLine()) != null)
            {
                out.println(userInput);

                System.out.println("echo: " + in.readLine());
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
