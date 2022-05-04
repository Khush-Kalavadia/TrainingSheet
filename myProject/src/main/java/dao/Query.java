package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Query
{
    private Connection connection;

    public void createConnection()
    {
        try
        {
            this.connection = ConnectionPoolHandler.getConnection();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void releaseConnection()
    {
        try
        {
            ConnectionPoolHandler.releaseConnection(connection);
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
                    preparedStatement.setObject(++preparedStatementDataCount, data);
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

    public int executeUpdate(String sql, List<Object> preparedStatementData)
    {
        int returnedValue = 0;

        PreparedStatement preparedStatement = null;

        try
        {
            if (connection != null)
            {
                int preparedStatementDataCount = 0;

                preparedStatement = connection.prepareStatement(sql);

                for (Object value : preparedStatementData)
                {
                    preparedStatement.setObject(++preparedStatementDataCount, value);
                }

                System.out.println(preparedStatement);

                returnedValue = preparedStatement.executeUpdate();
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
        return returnedValue;
    }
}
