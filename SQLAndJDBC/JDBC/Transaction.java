package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Transaction
{
    public static void main(String args[])
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyDatabase", "root", "Mind@123");

            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement("insert into employee_info(firstName, lastName) values (?,?)");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while (true)
            {
                System.out.print("enter first name:");

                String fname = br.readLine();

                System.out.print("enter last name:");

                String lname = br.readLine();

                ps.setString(1, fname);

                ps.setString(2, lname);

                ps.executeUpdate();

                System.out.print("(executeUpdate done) Now commit/rollback:");

                String answer = br.readLine();

                if (answer.equals("commit"))
                {
                    con.commit();
                }
                if (answer.equals("rollback"))
                {
                    con.rollback();
                }

                System.out.print("Want to add more records y/n:");

                String ans = br.readLine();

                if (ans.equals("n"))
                {
                    break;
                }

            }

            con.commit();

            System.out.println("record successfully saved");

            con.close();//before closing connection commit() is called
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
}




