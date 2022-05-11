package com.java.socket.programming.squareUDPOneToOne;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

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
            int myInt = 8;

            byte[] sentData = (myInt + "").getBytes();

            //sentData length
//            System.out.println(sentData.length);

            datagramSocket = new DatagramSocket();         //it is udp so we can specify ipaddress in packet as well

            ipAddress = InetAddress.getLocalHost();

//            ipAddress = InetAddress.getByName("localhost");

            datagramPacket = new DatagramPacket(sentData, sentData.length, ipAddress, port);

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
