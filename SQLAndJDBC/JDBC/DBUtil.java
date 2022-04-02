package com.company;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class DBUtil
{
    private static final String DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

    private static final String DB_USERNAME = "root";

    private static final String DB_PASSWORD = "Mind@123";

    private static final String DB_URL = "jdbc:mysql://localhost:3306/MovieDb";

    private static MysqlDataSource dataSource = null;

    static
    {
        try
        {
            Class.forName(DB_DRIVER_CLASS);

            dataSource = new MysqlDataSource();

            dataSource.setUrl(DB_URL);

            dataSource.setUser(DB_USERNAME);

            dataSource.setPassword(DB_PASSWORD);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static DataSource getDataSource()
    {
        return dataSource;
    }

}
