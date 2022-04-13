//created database to handle any query
package dao;

import commonutil.ConnectionStartup;
import service.LoginService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Query
{
    private Connection connection;

    public void createConnection()
    {
        this.connection = dao.ConnectionPoolHandler.getConnection();
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

                List<Object> resultSetRow;

                while (resultSet.next())
                {
                    resultSetRow = new ArrayList<>();

                    for (int i = 0; i < columnCount; i++)
                    {
                        datatype = metaData.getColumnTypeName(i + 1);

                        switch (datatype)
                        {
                            case "VARCHAR":

                                resultSetRow.add(resultSet.getString(i + 1));

                                break;

                            case "INT":

                            case "TINYINT":

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

    public int executeUpdate(String sql, List<Object> preparedStatementData)
    {
        int updatedRow = 0;

        PreparedStatement preparedStatement = null;

        try
        {
            if (connection != null)
            {
                preparedStatement = connection.prepareStatement(sql);

                int i = 1;

                for (Object value : preparedStatementData)
                {
                    preparedStatement.setObject(i, value);

                    i++;
                }

//                System.out.println(preparedStatement);

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
//        list.add(1222);
//
//        list.add("hello");
//
//        System.out.println(list);
//
//        query.createConnection();
//
//        int result = query.executeUpdate("insert into discovery(id, name) values (?, ?)", list);
//
//        System.out.println(result);
//    }

}
