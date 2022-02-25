package com.java.socket.programming.echoObjectUDPManyToOne;

import java.io.*;

import java.net.DatagramPacket;

import java.net.DatagramSocket;

import java.net.InetAddress;

public class Client
{
    public static void main(String[] args)
    {
        DatagramSocket datagramSocket = null;

        BufferedReader in = null;

        ObjectOutputStream objectOutputStream = null;

        ObjectInputStream objectInputStream = null;

        int port = 9002;

        try
        {
            datagramSocket = new DatagramSocket();

            in = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter integer value: ");

            int valueInput = Integer.parseInt(in.readLine());

            System.out.println("Enter a string: ");

            String sendString = in.readLine();

            MyClass writeObj = new MyClass(valueInput, sendString);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024);

            objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(outputStream));

            objectOutputStream.writeObject(writeObj);

            objectOutputStream.flush();

            byte[] sendData = outputStream.toByteArray();

            DatagramPacket sentPacket = new DatagramPacket(sendData, sendData.length,
                    InetAddress.getLocalHost(), port);

            datagramSocket.send(sentPacket);

            //now the ClientHandler will echo the packet which is deserialized over here

            byte[] receiveData = new byte[1024];

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            datagramSocket.receive(receivePacket);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(receiveData);

            objectInputStream = new ObjectInputStream(new BufferedInputStream(inputStream));

            MyClass readObject = (MyClass) objectInputStream.readObject();

            System.out.println("Received object:");

            System.out.println(readObject);
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
                if (objectInputStream != null)
                {
                    objectInputStream.close();
                }
                if (objectOutputStream != null)
                {
                    objectOutputStream.close();
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

/*
package com.java.socket.programming.echoObjectUDPManyToOne;

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

            System.out.println("Enter integer value: ");

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
 */