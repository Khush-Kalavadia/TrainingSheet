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
    public List<List<Object>> select(String sql, List<Object> preparedStatementData)
    {
        List<List<Object>> resultSetList = new ArrayList<>();

        Connection connection = null;

        PreparedStatement preparedStatement = null;

        try
        {
            connection = dao.ConnectionPoolHandler.getConnection();

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
                    datatype = metaData.getColumnTypeName(i+1);

                    switch (datatype)
                    {
                        case "java.lang.String":

                            resultSetRow.add(resultSet.getString(i+1));

                            break;

                        case "java.lang.Integer":

                            resultSetRow.add(resultSet.getInt(i+1));

                            break;
                    }
                }

                resultSetList.add(resultSetRow);
            }

            preparedStatement.close();

            dao.ConnectionPoolHandler.releaseConnection(connection);

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
            try
            {
                if (connection != null)
                {
                    if (dao.ConnectionPoolHandler.isAlive(connection))
                    {
                        dao.ConnectionPoolHandler.releaseConnection(connection);
                    }
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return resultSetList;
    }
}
