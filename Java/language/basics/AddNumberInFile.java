package com.java.language.basics;

import java.io.BufferedReader;

import java.io.FileReader;

import java.util.Locale;

import java.util.Scanner;

public class AddNumberInFile
{
    public static void main(String[] args)
    {
        Scanner s = null;

        double sum = 0;
        try
        {
            s = new Scanner(new BufferedReader(new FileReader("usnumbers.txt")));

            s.useLocale(Locale.US);
            /*
            A Locale object represents a specific geographical, political, or cultural region. An operation
            that requires a Locale to perform its task is called locale-sensitive and uses the Locale to
            tailor information for the user. For example, displaying a number is a locale-sensitive operation—
            the number should be formatted according to the customs and conventions of the user's native country,
            region, or culture.
             */

            System.out.println("Non double according to US-locale");

            while (s.hasNext())
            {
                if (s.hasNextDouble())          //all numbers are double
                {
                    sum += s.nextDouble();
                }
                else                            //this is for other non-double according to uslocale present
                {
                    String str = s.next();

                    System.out.println(str);
                }
            }

            System.out.println("Summation: "+sum);
            /*INPUT
            10.2
            1.00001
            100,000
            String
            10,00,000           //not considered uslocale writing practise
            200

             */


        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (s != null)
            {
                s.close();
            }
        }

    }
}
