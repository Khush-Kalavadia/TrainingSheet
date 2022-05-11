package com.java.language.basics;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PingDevice
{
    static void commands(ArrayList<String> commandList) throws Exception
    {
        // creating the sub process, execute system command
        ProcessBuilder build = new ProcessBuilder(commandList);

        Process process = build.start();

        // to read the output
        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

        BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        String s = null;

        System.out.println("Standard output: ");

        while ((s = input.readLine()) != null)
        {
            System.out.println(s);
        }

        System.out.println("error (if any): ");

        while ((s = error.readLine()) != null)
        {
            System.out.println(s);
        }
    }

    // Driver method
    public static void main(String args[]) throws Exception
    {
        // creating list for commands
        ArrayList<String> commandList = new ArrayList<>();

        commandList.add("ping");

        commandList.add("-c 10");

        commandList.add("-w 3");

        // can be replaced by IP
//        commandList.add("10.20.40.142");
        commandList.add("facebook.com");

        PingDevice.commands(commandList);
    }
}
