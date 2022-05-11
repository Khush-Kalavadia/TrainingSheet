package com.java.language.basics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFromFile
{
    public static void main(String[] args)
    {
        //creating File instance to reference text file in Java
        File text = new File("/home/khush/IdeaProjects/Java_basics/src/com/java/language/basics/myNumberFile.txt");

        //Creating Scanner instance to read File in Java
        Scanner scnr = null;
        try
        {
            scnr = new Scanner(text);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        //Reading each line of the file using Scanner class
        int lineNumber = 1;
        while(scnr.hasNextDouble()){
            Double num = scnr.nextDouble();
            System.out.println("number " + lineNumber + " :" + num);
            lineNumber++;
        }
    }
}
