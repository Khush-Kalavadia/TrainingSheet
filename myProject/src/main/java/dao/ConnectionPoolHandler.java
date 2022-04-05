package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPoolHandler
{
    private static final String DB_DRIVER_CLASS;

    private static final String DB_URL;

    private static final String DB_USERNAME;

    private static final String DB_PASSWORD;

    private static final int MAX_CONNECTION;

    private static final LinkedBlockingQueue<Connection> CONNECTION_POOL;

    private static final List<Connection> USED_CONNECTION;

    static
    {
        DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

        DB_URL = "jdbc:mysql://localhost:3306/ProjectDatabase";

        DB_USERNAME = "root";

        DB_PASSWORD = "Mind@123";

        MAX_CONNECTION = 5;

        CONNECTION_POOL = new LinkedBlockingQueue<>(MAX_CONNECTION);

        USED_CONNECTION = new ArrayList<>();
    }

    public static void start()
    {
        try
        {
            Class.forName(DB_DRIVER_CLASS);

            for (int i = 0; i < MAX_CONNECTION; i++)
            {
                CONNECTION_POOL.put(DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection()
    {
        Connection connection = null;

        try
        {
            connection = CONNECTION_POOL.take();

            USED_CONNECTION.add(connection);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return connection;
    }

    public static void releaseConnection(Connection connection)
    {
        try
        {
            CONNECTION_POOL.put(connection);

            USED_CONNECTION.remove(connection);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void destory()            //testme in case when we have >max_connection - study rejection handler
                                            //fixme need to check error which appears only sometime when destroying
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

            for (int i = 0; i < USED_CONNECTION.size(); i++)
            {
                connection = USED_CONNECTION.get(0);

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

    static boolean isAlive(Connection connection)
    {
        boolean connectionAlive = false;

        try
        {
            if (USED_CONNECTION.indexOf(connection) != -1)
            {
                connectionAlive = true;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return connectionAlive;
    }
}
