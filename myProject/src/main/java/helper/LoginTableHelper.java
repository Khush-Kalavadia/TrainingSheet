package helper;

import dao.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginTableHelper
{
    public static HashMap<String, Object> getRowByUsernamePassword(String username, String password)
    {
        List<HashMap<String, Object>> resultSetList;

        HashMap<String, Object> resultSetRow = null;

        Query query = new Query();

        try
        {
            List<Object> preparedStatementData = new ArrayList<>();

            preparedStatementData.add(username);

            preparedStatementData.add(password);

            query.createConnection();

            resultSetList = query.select("SELECT username, password FROM user WHERE username LIKE BINARY ? AND password LIKE BINARY ?", preparedStatementData);

            query.releaseConnection();

            if (resultSetList != null && !resultSetList.isEmpty())
            {
                resultSetRow = resultSetList.get(0);
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
        return resultSetRow;
    }
}
