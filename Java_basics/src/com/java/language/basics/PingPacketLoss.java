package com.java.language.basics;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PingPacketLoss
{
    float getPingPacketLoss(String ipHostname)
    {
        float packetLoss = -1;

        try
        {
            ArrayList<String> commandList = new ArrayList<>();

            commandList.add("ping");

            commandList.add("-c 3");

            commandList.add("-w 3");

            commandList.add(ipHostname);

            ProcessBuilder build = new ProcessBuilder(commandList);

            Process process = build.start();

            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String pingOutputLine;

            while ((pingOutputLine = input.readLine()) != null)
            {
                if (pingOutputLine.contains("% packet loss"))
                {
                    String[] pingOutputArray = pingOutputLine.split(", ");

                    for (String pingOutputSubString : pingOutputArray)
                    {
                        if (pingOutputSubString.contains("% packet loss"))
                        {
                            packetLoss = Float.parseFloat(pingOutputSubString.substring(0, pingOutputSubString.indexOf("% packet loss")));
                        }
                    }
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return packetLoss;
    }


    public static void main(String[] args)
    {
        PingPacketLoss object = new PingPacketLoss();

//        System.out.println(object.getPingPacketLoss("11.20.40.143"));
        System.out.println(object.getPingPacketLoss("facebook.com"));
    }
}
