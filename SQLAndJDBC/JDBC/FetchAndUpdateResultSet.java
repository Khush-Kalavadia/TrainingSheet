package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FetchAndUpdateResultSet
{
    public static void main(String[] args)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyDatabase", "root", "Mind@123");

            con.setAutoCommit(false);

            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String sql = "SELECT id, firstName FROM employee_info";

            ResultSet rs = stmt.executeQuery(sql);      //this is the only position where it fetches data from database

            while (rs.next())
            {
            }

            while (rs.previous())       //this gives error if we don't use scroll. BYDEFAULT forward_only
            {
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            }

            System.out.println("----Updating row 3----");

            rs.absolute(3);     //it fetches data from the resultset and not from database

            System.out.println(rs.getString(1)+ " "+rs.getString(2));

            rs.updateString(2, "Prakash");

            rs.updateRow();     //Updates the underlying database with the new contents of the
                                //current row of "this ResultSet object".

            System.out.println(rs.getString(1)+ " "+rs.getString(2));   //getString does not fetches from database

            con.commit();       //this will reflect changes to the database which are made in the result set

            rs.close();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
