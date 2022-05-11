package com.java.socket.programming.basics;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.net.URL;

public class URLReader
{
    public static void main(String[] args) throws Exception
    {

        URL url = new URL("https://www.motadata.com/");

        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        String inputLine;

        while ((inputLine = in.readLine()) != null)
        {
            System.out.println(inputLine);
        }
        in.close();
    }
}
