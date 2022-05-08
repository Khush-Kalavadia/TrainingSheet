package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Query
{
    public enum Condition
    {
        WHERE, GROUP_BY, HAVING, ORDER_BY, LIMIT
    }

    private Connection connection;

    public void getConnection()
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
            if (connection != null && !connection.isClosed())
            {
                preparedStatement = connection.prepareStatement(sql);

                if (preparedStatement != null)
                {
                    if (preparedStatementData != null)
                    {
                        int preparedStatementDataCount = 0;

                        for (Object data : preparedStatementData)
                        {
                            preparedStatement.setObject(++preparedStatementDataCount, data);
                        }
                    }

                    System.out.println(preparedStatement);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet != null)
                    {
                        ResultSetMetaData metaData = resultSet.getMetaData();

                        if (metaData != null)
                        {
                            resultSetList = new ArrayList<>();

                            while (resultSet.next())
                            {
                                resultSetRow = new HashMap<>();

                                for (int columnCount = 1; columnCount <= metaData.getColumnCount(); columnCount++)
                                {
                                    resultSetRow.put(metaData.getColumnLabel(columnCount), resultSet.getObject(columnCount));
                                }
                                resultSetList.add(resultSetRow);
                            }
                        }
                    }
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
            if (connection != null && !connection.isClosed())
            {
                preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                if (preparedStatement != null)
                {
                    if (preparedStatementData != null)
                    {
                        int preparedStatementDataCount = 0;

                        for (Object value : preparedStatementData)
                        {
                            preparedStatement.setObject(++preparedStatementDataCount, value);
                        }
                    }

                    if (sql.contains("INSERT"))
                    {
                        preparedStatement.executeUpdate();

                        ResultSet resultSetId = preparedStatement.getGeneratedKeys();

                        if (resultSetId != null)
                        {
                            while (resultSetId.next())
                            {
                                returnedValue = resultSetId.getInt(1);
                            }
                        }
                    }
                    else
                    {
                        returnedValue = preparedStatement.executeUpdate();
                    }
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
        return returnedValue;
    }

    public List<HashMap<String, Object>> commonSelect(List<String> listOfColumn, String tableName, HashMap<Condition, String> condition, List<Object> preparedStatementData)
    {
        List<HashMap<String, Object>> resultSetList = null;

        try
        {
            if (tableName != null && listOfColumn != null && !listOfColumn.isEmpty() && listOfColumn.get(0)!=null)
            {
                StringBuilder sqlQuery = new StringBuilder();

                sqlQuery.append("SELECT ");

                if (listOfColumn.get(0).equals("*"))
                {
                    sqlQuery.append("* ");
                }
                else
                {
                    int columnCounter = 0;

                    int lastColumnIndex = listOfColumn.size() - 1;

                    for (String columnName : listOfColumn)
                    {
                        sqlQuery.append(columnName);

                        if (columnCounter++ != lastColumnIndex)
                        {
                            sqlQuery.append(", ");
                        }
                        else
                        {
                            sqlQuery.append(" ");
                        }
                    }
                }

                sqlQuery.append("FROM ");

                sqlQuery.append(tableName);

                if (condition != null)
                {
                    String conditionValue = condition.get(Condition.WHERE);

                    if (conditionValue != null)
                    {
                        sqlQuery.append(" WHERE ").append(conditionValue);
                    }

                    conditionValue = condition.get(Condition.GROUP_BY);

                    if (conditionValue != null)
                    {
                        sqlQuery.append(" GROUP BY ").append(conditionValue);
                    }

                    conditionValue = condition.get(Condition.HAVING);

                    if (conditionValue != null)
                    {
                        sqlQuery.append(" HAVING ").append(conditionValue);
                    }

                    conditionValue = condition.get(Condition.ORDER_BY);

                    if (conditionValue != null)
                    {
                        sqlQuery.append(" ORDER BY ").append(conditionValue);
                    }

                    conditionValue = condition.get(Condition.LIMIT);

                    if (conditionValue != null)
                    {
                        sqlQuery.append(" LIMIT ").append(conditionValue);
                    }
                }

                resultSetList = this.select(sqlQuery.toString(), preparedStatementData);

            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSetList;
    }

//    public static void main(String[] args)
//    {
//        ConnectionPoolHandler.start();
//
//        Query query = new Query();
//
//        query.getConnection();
//
//        List<String> columnList = new ArrayList<>();
//
//        columnList.add("name");
//
//        columnList.add("ip_hostname");
//
//        columnList.add("CONVERT(used_memory_gb/total_memory_gb * 100, DECIMAL(4,2)) as measuring_unit");
//
//        HashMap<Condition, String> condition = new HashMap<>();
//
//        condition.put(Condition.WHERE, "availability_status = 'up' AND (time, map_monitor_id) in (SELECT MAX(time), map_monitor_id FROM ssh_polling GROUP BY map_monitor_id)");
//
//        condition.put(Condition.ORDER_BY, "used_memory_gb/total_memory_gb " + (true ? "DESC" : "ASC"));
//
//        condition.put(Condition.LIMIT, "?");
//
//        List<Object> preparedStatementData = new ArrayList<>();
//
//        preparedStatementData.add(3);
//
//        System.out.println(query.commonSelect(Operation.SELECT, columnList, "ssh_polling INNER JOIN monitor ON id = map_monitor_id", condition, preparedStatementData));
//
//        query.releaseConnection();
//    }
}
