//created database to handle any query
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class Query
{
    private Connection connection;

    public void createConnection()
    {
        this.connection = dao.ConnectionPoolHandler.getConnection();
    }

    public List<List<Object>> select(String sql, List<Object> preparedStatementData)
    {
        List<List<Object>> resultSetList = new ArrayList<>();

        PreparedStatement preparedStatement = null;

        try
        {
            if (connection != null)
            {
                preparedStatement = connection.prepareStatement(sql);

                String datatype;

                for (int i = 0; i < preparedStatementData.size(); i++)
                {
                    datatype = preparedStatementData.get(i).getClass().getName();

                    switch (datatype)
                    {
                        case "java.lang.String":

                            preparedStatement.setString(i + 1, (String) preparedStatementData.get(i));

                            break;

                        case "java.lang.Integer":

                            preparedStatement.setInt(i + 1, (Integer) preparedStatementData.get(i));

                            break;
                    }
                }

                ResultSet resultSet = preparedStatement.executeQuery();

                ResultSetMetaData metaData = resultSet.getMetaData();

                int columnCount = metaData.getColumnCount();

                while (resultSet.next())
                {
                    List<Object> resultSetRow = new ArrayList<>();

                    for (int i = 0; i < columnCount; i++)
                    {
                        datatype = metaData.getColumnTypeName(i + 1);

                        switch (datatype)
                        {
                            case "java.lang.String":

                                resultSetRow.add(resultSet.getString(i + 1));

                                break;

                            case "java.lang.Integer":

                                resultSetRow.add(resultSet.getInt(i + 1));

                                break;
                        }
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

    public void releaseConnection()
    {
        try
        {
            if (connection != null)
            {
                dao.ConnectionPoolHandler.releaseConnection(connection);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
