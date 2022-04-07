package service;

import commonutil.ConnectionStartup;
import dao.Query;

import java.util.ArrayList;
import java.util.List;

public class LoginService
{
    public boolean validate(String username, String password)
    {
        boolean loginStatus = false;

        List<List<Object>> resultSetList;

        Query query = new Query();

        try
        {
            List<Object> preparedStatementData = new ArrayList<>();

            preparedStatementData.add(username);

            preparedStatementData.add(password);

            query.createConnection();

            resultSetList = query.select("SELECT username, password FROM user WHERE username LIKE BINARY ? AND password LIKE BINARY ?", preparedStatementData);

            query.releaseConnection();

            if (!resultSetList.isEmpty())
            {
                loginStatus = true;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            query.releaseConnection();
        }
        return loginStatus;
    }
}
