package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BatchProcessing
{
    public static void main(String[] args)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/organisation", "root", "Mind@123");

            con.setAutoCommit(false);

            Statement stmt = con.createStatement();

            stmt.addBatch("INSERT INTO Worker(WORKER_ID) VALUES (9)");

            stmt.addBatch("INSERT INTO Worker(WORKER_ID) VALUES (10)");

            stmt.executeBatch();

            con.commit();

            con.close();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
