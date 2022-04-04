package service;

import dao.Query;
import dao.LoginDao;

import java.util.ArrayList;
import java.util.List;

public class LoginService
{
    public boolean validate(String username, String password)                   //todo validate the user based on data coming from dao
    {
        boolean loginStatus = false;

        LoginDao loginDao = new LoginDao();

        List<List<Object>> resultSetList;

        try
        {
            //resultSetList = loginDao.checkUser(username, password);

            Query database = new Query();

            List<Object> preparedStatementData = new ArrayList<>();

            preparedStatementData.add(username);

            preparedStatementData.add(password);

            resultSetList = database.select("SELECT username, password FROM user WHERE username = ? AND password = ?", preparedStatementData);

            if (resultSetList.get(0).size() != 0)
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
