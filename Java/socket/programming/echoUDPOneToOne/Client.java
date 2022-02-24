package com.java.socket.programming.echoUDPOneToOne;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.net.DatagramPacket;

import java.net.DatagramSocket;

import java.net.InetAddress;

import java.util.Scanner;

public class Client
{
    public static void main(String[] args)
    {
        DatagramPacket datagramPacket = null;

        DatagramSocket datagramSocket = null;

        DatagramPacket datagramPacket1 = null;

        InetAddress ipAddress = null;

        int port = 9999;

        try
        {
            byte[] sentData;

            System.out.println("Enter text");

            //Way 1: Using scanner (generally when we know the input is of type string or primitive)
            Scanner scanner = new Scanner(System.in);

            sentData = scanner.nextLine().getBytes();

            //Way 2: Using buffered reader
//            BufferedReader out = new BufferedReader(new InputStreamReader((System.in)));
//
//            sentData = out.readLine().getBytes();

            datagramSocket = new DatagramSocket();         //it is udp so we can specify ipaddress in packet as well

            ipAddress = InetAddress.getLocalHost();

            datagramPacket = new DatagramPacket(sentData, sentData.length, ipAddress, port);        //packet to send so mention all 4 arguments

            datagramSocket.send(datagramPacket);

            byte[] receivedData = new byte[1024];

            datagramPacket1 = new DatagramPacket(receivedData, receivedData.length);      //ipAddress, port not mentioned in case of receiving

            datagramSocket.receive(datagramPacket1);

            String str = new String(datagramPacket1.getData());

            System.out.println("Output is " + str);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (datagramSocket != null)
            {
                datagramSocket.close();
            }
        }
    }
}
