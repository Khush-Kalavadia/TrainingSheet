//testme in case when we have >max_connection - study rejection handler
package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPoolHandler
{
    private static final String DB_DRIVER_CLASS;

    private static final String DB_URL;

    private static final String DB_USERNAME;

    private static final String DB_PASSWORD;

    private static final int MAX_CONNECTION;

    private static final LinkedBlockingQueue<Connection> CONNECTION_POOL;

    private static final int MAX_CONNECTION_LIMIT = 150;        //allows 150 normal connections plus one connection from the SUPER account

    static
    {
        DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

        DB_URL = "jdbc:mysql://localhost:3306/ProjectDatabase";

        DB_USERNAME = "root";

        DB_PASSWORD = "Mind@123";

        int connections = MAX_CONNECTION_LIMIT;

        try
        {
            FileInputStream fileInputStream = new FileInputStream("/home/khush/IdeaProjects/myProject/src/main/java/dao/connection.prop");

            Properties properties = new Properties();

            properties.load(fileInputStream);

            connections = Integer.parseInt((String)properties.get("MAXIMUM_CONNECTION"));

            if (connections > MAX_CONNECTION_LIMIT)
            {
                connections = MAX_CONNECTION_LIMIT;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        MAX_CONNECTION = connections;

        CONNECTION_POOL = new LinkedBlockingQueue<>(MAX_CONNECTION);
    }

    public static int start()
    {
        try
        {
            Class.forName(DB_DRIVER_CLASS);

            for (int i = 0; i < MAX_CONNECTION; i++)
            {
                try
                {
                    CONNECTION_POOL.put(DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD));
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return CONNECTION_POOL.size();
    }

    static Connection getConnection()
    {
        Connection connection = null;

        try
        {
            connection = CONNECTION_POOL.take();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return connection;
    }

    static void releaseConnection(Connection connection)
    {
        try
        {
            if (connection != null && !CONNECTION_POOL.contains(connection))
            {
                CONNECTION_POOL.put(connection);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    /*
    * There is no way to know when a garbage collection of the connection objects will take place. The garbage collector has no knowledge about JDBC resources, just about memory. This means that a GC may only occur when there is a need to free up memory. If you have 60 connections sitting around but there is still free memory the GC wont run and you'll end up running out of connections.
    * */
    public static void destroy()
    {
        Connection connection = null;

        for (int i = 0; i < CONNECTION_POOL.size(); i++)
        {
            try
            {
                connection = CONNECTION_POOL.take();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            try
            {
                if (connection != null && !connection.isClosed())
                {
                    connection.close();
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
