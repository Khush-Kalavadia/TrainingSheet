package com.java.language.basics;

import java.io.*;

public class ByteStreamCopyFile
{
    public static void main(String[] args)
    {
        FileOutputStream out = null;

        FileInputStream in = null;

        BufferedReader bufferedReader = null;

        BufferedWriter bufferedWriter = null;

        byte[] readByte = null;

        try
        {
            //See files which are formed.

            in = new FileInputStream("myFile.txt");

            out = new FileOutputStream("copyFile.txt");

            //Way 1: reading integer value

            int readInt;          //.read returns int

            while ((readInt = in.read()) != -1)       //end of file is represented by -1
            {
                System.out.print(readInt + ",");

                out.write(readInt);
            }

            System.out.println("\n");

            //Way 2: reading 1 byte or 1 character at a time would work fine

            in = new FileInputStream("myFile.txt");
            //if we don't initialise it then the pointer which would have reached at the
            // end would start from beginning

            readByte = new byte[1];     //one character is of 1 byte

            while ((in.read(readByte)) != -1)       //end of file is represented by -1
            {
                System.out.println(">" + new String(readByte));

                out.write(readByte);
            }

            //Way 3: reading 10 bytes or 10 characters which would give garbage value at last 10 bytes

            in = new FileInputStream("myFile.txt");

            out = new FileOutputStream("copyFile1.txt");        //in last case data added at the end of the myFile

            readByte = new byte[10];     //.read also returns array of byte

            while ((in.read(readByte)) != -1)       //end of file is represented by -1
            {
                System.out.println(">" + new String(readByte));

                out.write(readByte);
            }

            //Way 4: reading 1024 bytes or 1024 characters at a time would contain many EOF characters

            in = new FileInputStream("myFile.txt");

            out = new FileOutputStream("copyFile2.txt");

            readByte = new byte[1024];     //one character is of 1 byte

            while ((in.read(readByte)) != -1)       //end of file is represented by -1
            {
                System.out.println(">" + new String(readByte));

                out.write(readByte);
            }

            //What happens if we read copyFile2 which is having many EOF characters?
            //It will again copy those characters

            in = new FileInputStream("copyFile2.txt");

            out = new FileOutputStream("copyFile3.txt");

            readByte = new byte[1];     //one character is of 1 byte

            while ((in.read(readByte)) != -1)       //end of file is represented by -1
            {
                System.out.println(">" + new String(readByte));

                out.write(readByte);
            }

            //MOST efficient way

            bufferedReader = new BufferedReader(new FileReader("myFile.txt"));
            //as myFile contains characters it is best practice to use character stream i.e. FileReader
            //BufferedReader is used to efficiently use the native API

            bufferedWriter = new BufferedWriter(new FileWriter("copyFile4.txt"));

            String readStr;

            while ((readStr = bufferedReader.readLine()) != null)       //end of file is represented by -1
            {
                System.out.println(">" + readStr);

                bufferedWriter.write(readStr);

                bufferedWriter.newLine();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
                if (out != null)
                {
                    out.close();
                }
                if (bufferedReader != null)
                {
                    bufferedReader.close();
                }
                if (bufferedWriter != null)
                {
                    bufferedWriter.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}
