package com.java.socket.programming.basics;

import java.io.BufferedReader;

import java.io.InputStream;

import java.io.InputStreamReader;

public class BufferedReaderInput
{
    public static void main(String[] args)
    {
        try
        {
            System.out.println("Waiting for input");

            InputStream entered = System.in;                //we get bytes

            InputStreamReader inputStreamReader = new InputStreamReader(entered);   //converted to characters

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  //creates buffer

            String string = bufferedReader.readLine();      //returns a line in string i.e. collection of characters

            System.out.println(string);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
