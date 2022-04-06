//testme in case when we have >max_connection - study rejection handler
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPoolHandler
{
    private static final String DB_DRIVER_CLASS;

    private static final String DB_URL;

    private static final String DB_USERNAME;

    private static final String DB_PASSWORD;

    private static final int MAX_CONNECTION;

    private static final LinkedBlockingQueue<Connection> CONNECTION_POOL;

    static
    {
        DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

        DB_URL = "jdbc:mysql://localhost:3306/ProjectDatabase";

        DB_USERNAME = "root";

        DB_PASSWORD = "Mind@123";

        MAX_CONNECTION = 5;

        CONNECTION_POOL = new LinkedBlockingQueue<>(MAX_CONNECTION);
    }

    public static void start()
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
            if (!CONNECTION_POOL.contains(connection))
            {
                CONNECTION_POOL.put(connection);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void destory()           //fixme need to check error which appears only sometime when destroying
    {
        try
        {
            Connection connection;

            for (int i = 0; i < CONNECTION_POOL.size(); i++)
            {
                connection = CONNECTION_POOL.take();

                try
                {
                    if (!connection.isClosed())
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
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
