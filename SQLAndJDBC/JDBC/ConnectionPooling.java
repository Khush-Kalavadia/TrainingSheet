package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionPooling
{
    public static void main(String[] args)
    {
        try (Connection connection = DBUtil.getDataSource().getConnection())
        {
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM movie";

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                System.out.print(resultSet.getString(2) + " ");

                System.out.print(resultSet.getString(3) + " ");

                System.out.println(resultSet.getInt(4));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
