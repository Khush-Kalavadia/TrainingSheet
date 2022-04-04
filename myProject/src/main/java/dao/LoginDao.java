package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginDao
{
    public List<List<Object>> checkUser(String username, String password)
    {
        PreparedStatement preparedStatement = null;

        List<List<Object>> resultSetList = new ArrayList<>();

        Connection connection = null;

        try
        {
            String query = "SELECT username, password FROM user WHERE username = ? AND password = ?";

            connection = dao.ConnectionPoolHandler.getConnection();

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, username);

            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Object> resultSetRow = new ArrayList<>();

            while (resultSet.next())
            {
                resultSetRow.add(resultSet.getString(1));

                resultSetRow.add(resultSet.getString(2));
            }

            preparedStatement.close();

            dao.ConnectionPoolHandler.releaseConnection(connection);

            resultSetList.add(resultSetRow);
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
