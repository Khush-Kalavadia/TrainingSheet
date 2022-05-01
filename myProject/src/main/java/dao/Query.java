package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Query
{
    private Connection connection;

    void createConnection()
    {
        try
        {
            this.connection = dao.ConnectionPoolHandler.getConnection();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    void releaseConnection()
    {
        try
        {
            dao.ConnectionPoolHandler.releaseConnection(connection);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public List<HashMap<String, Object>> select(String sql, List<Object> preparedStatementData)
    {
        List<HashMap<String, Object>> resultSetList = null;

        PreparedStatement preparedStatement = null;

        HashMap<String, Object> resultSetRow;

        try
        {
            if (connection != null)
            {
                int preparedStatementDataCount = 0;

                preparedStatement = connection.prepareStatement(sql);

                for (Object data : preparedStatementData)
                {
                    if (data != null)
                    {
                        preparedStatement.setObject(++preparedStatementDataCount, data);
                    }
                    else
                    {
                        preparedStatement.close();

                        return resultSetList;
                    }
                }

                System.out.println(preparedStatement);

                ResultSet resultSet = preparedStatement.executeQuery();

                ResultSetMetaData metaData = resultSet.getMetaData();

                resultSetList = new ArrayList<>();

                while (resultSet.next())
                {
                    resultSetRow = new HashMap<>();

                    for (int i = 1; i <= metaData.getColumnCount(); i++)
                    {
                        resultSetRow.put(metaData.getColumnLabel(i), resultSet.getObject(i));
                    }
                    resultSetList.add(resultSetRow);
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (preparedStatement != null && !preparedStatement.isClosed())
                {
                    preparedStatement.close();
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return resultSetList;
    }

    int executeUpdate(String sql, List<Object> preparedStatementData)
    {
        int updatedRow = 0;

        PreparedStatement preparedStatement = null;

        try
        {
            if (connection != null)
            {
                int preparedStatementDataCount = 1;

                preparedStatement = connection.prepareStatement(sql);

                for (Object value : preparedStatementData)
                {
                    preparedStatement.setObject(preparedStatementDataCount, value);

                    preparedStatementDataCount++;
                }

                System.out.println(preparedStatement);

                updatedRow = preparedStatement.executeUpdate();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (preparedStatement != null && !preparedStatement.isClosed())
                {
                    preparedStatement.close();
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return updatedRow;
    }

//    public static void main(String[] args)
//    {
//        ConnectionStartup connectionStartup = new ConnectionStartup();
//
//        connectionStartup.init();
//
//        Query query = new Query();
//
//        List<Object> list = new ArrayList<>();
//
//        list.add(1);
//
////        list.add("hello");
//
//        System.out.println(list);
//
//        query.createConnection();
//
////        int result = query.executeUpdate("insert into discovery(id, name) values (?, ?)", list);
//
//        System.out.println(query.select("select * from discovery where id = ?", list));
//
////        System.out.println(result);
//
//        query.releaseConnection();
//    }

}
