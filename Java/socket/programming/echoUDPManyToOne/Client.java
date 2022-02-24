package com.java.socket.programming.echoUDPManyToOne;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.net.DatagramPacket;

import java.net.DatagramSocket;

import java.net.InetAddress;

public class Client
{
    public static void main(String[] args)
    {
        DatagramSocket datagramSocket = null;

        BufferedReader in = null;

        int port = 9002;

        try
        {
            datagramSocket = new DatagramSocket();

            in = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter input: ");

            String str = in.readLine();

            byte[] sent = str.getBytes();

            DatagramPacket sentPacket = new DatagramPacket(sent, sent.length,
                    InetAddress.getLocalHost(), port);

            datagramSocket.send(sentPacket);

            DatagramPacket receivePacket = new DatagramPacket(sent, sent.length);

            datagramSocket.receive(receivePacket);

            str = new String(receivePacket.getData());

            System.out.println(str);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {

            if (datagramSocket != null)
            {
                datagramSocket.close();
            }
            try
            {
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

