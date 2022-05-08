package service;

import bean.LoginBean;
import dao.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginService
{
    public static void validate(LoginBean loginBean)
    {
        List<HashMap<String, Object>> resultSetList;

        Query query = null;

        try
        {
            query = new Query();

            List<String> columnList = new ArrayList<>();

            columnList.add("username");

            columnList.add("password");

            List<Object> preparedStatementData = new ArrayList<>();

            String username = loginBean.getUsername();

            preparedStatementData.add(username);

            preparedStatementData.add(loginBean.getPassword());

            HashMap<Query.Condition, String> conditionHashmap = new HashMap<>();

            conditionHashmap.put(Query.Condition.WHERE, "username LIKE BINARY ? AND password LIKE BINARY ?");

            query.getConnection();

            resultSetList = query.commonSelect(columnList, "user", conditionHashmap, preparedStatementData);

            if (resultSetList != null && !resultSetList.isEmpty() && resultSetList.get(0) != null && resultSetList.get(0).get("username") != null && resultSetList.get(0).get("username").equals(username))
            {
                loginBean.setLogin(true);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (query != null)
            {
                query.releaseConnection();
            }
        }
    }

    public static void logout(LoginBean loginBean)
    {
        try
        {
            loginBean.setUsername(null);

            loginBean.setPassword(null);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
