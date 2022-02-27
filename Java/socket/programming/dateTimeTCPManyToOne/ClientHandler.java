package com.java.socket.programming.dateTimeTCPManyToOne;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHandler implements Runnable
{
    Socket client;

    ClientCounter counter;

    BufferedReader in = null;

    BufferedWriter out = null;

    ClientHandler(Socket client, ClientCounter counter)
    {
        this.client = client;

        this.counter = counter;
    }

    @Override
    public void run()
    {
        DateFormat forDate = new SimpleDateFormat("dd/MM/yyyy");

        DateFormat forTime = new SimpleDateFormat("hh:mm:ss");

        try
        {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            String str;

            String result = null;

            Date date;

            while (true)
            {
//                System.out.println("Waiting for input: ");

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
                    case "exit":
                    {
                        client.close();

                        in.close();

                        out.close();

//                        System.out.println("Exiting client");

                        counter.decClientCount();

                        return;
                    }
                    case "exitServer":
                    {
                        client.close();

                        in.close();

                        out.close();

                        System.out.println("Exiting server");

                        System.exit(0);
                    }
                    default:
                    {
                        result = "Invalid Input";
                    }
                }
                out.write(result);

                out.newLine();

                out.flush();

//                System.out.println("Sent: " + result);
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
                if (in != null)
                {
                    in.close();
                }
                if (out != null)
                {
                    out.close();
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
