package service;

import dao.Query;

import java.util.ArrayList;
import java.util.List;

public class LoginService
{
    public boolean validate(String username, String password)
    {
        boolean loginStatus = false;

        List<List<Object>> resultSetList;

        try
        {
            Query database = new Query();

            List<Object> preparedStatementData = new ArrayList<>();

            preparedStatementData.add(username);

            preparedStatementData.add(password);

            resultSetList = database.select("SELECT username, password FROM user WHERE username = ? AND password = ?", preparedStatementData);

            if (resultSetList.size() != 0)
            {
                loginStatus = true;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return loginStatus;
    }
}
