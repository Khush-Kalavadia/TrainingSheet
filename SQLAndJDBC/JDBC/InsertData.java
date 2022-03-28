package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertData
{
    public static void main(String args[]) throws Exception
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyDatabase", "root", "Mind@123");

            con.setAutoCommit(false);

            Statement stmt = con.createStatement();

            int result = stmt.executeUpdate("insert into employee_info(firstName) values (\"Mahesh\")");

            System.out.println("Rows affected: " + result);

            con.commit();       //if we remove it changes won't be committed. required in executeUpdate and execute

            con.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
