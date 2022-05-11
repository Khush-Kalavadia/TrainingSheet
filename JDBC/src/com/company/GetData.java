package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetData
{
    public static void main(String[] args)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyDatabase", "root", "Mind@123");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProjectDatabase", "root", "Mind@123");

            con.setAutoCommit(false);

            Statement stmt = con.createStatement();

//            ResultSet rs = stmt.executeQuery("select id, firstName from employee_info");

            ResultSet rs = stmt.executeQuery("select username, password from user");

            while (rs.next())
            {
//                System.out.println(rs.getInt(1) + "  " + rs.getString(2));

                System.out.println(rs.getString(1) + "  " + rs.getString(2));
            }

            con.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
