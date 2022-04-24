package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommonTableDao
{
    public static List<HashMap<String, Object>> getTableData(String tableName)
    {
        List<HashMap<String, Object>> resultSetList = null;

        Query query = new Query();

        try
        {
            List<Object> preparedStatementData = new ArrayList<>();

            query.createConnection();

            resultSetList = query.select("SELECT * FROM " + tableName, preparedStatementData);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            query.releaseConnection();
        }
        return resultSetList;
    }

    public static HashMap<String, Object> getTableRowUsingId(String tableName, String idName, Object tableId)
    {
        HashMap<String, Object> returnRow = null;

        Query query = new Query();

        try
        {
            String sql = "SELECT * FROM " + tableName + " WHERE " + idName + " = ?";

            List<Object> preparedStatementData = new ArrayList<>();

            preparedStatementData.add(tableId);

            query.createConnection();

            List<HashMap<String, Object>> resultList = query.select(sql, preparedStatementData);

            if (resultList != null && !resultList.isEmpty())
            {
                returnRow = resultList.get(0);
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
        return returnRow;
    }

    public static boolean deleteTableRowUsingId(String tableName, String idName, Object tableId)
    {
        boolean deletionStatus = false;

        Query query = new Query();

        try
        {
            String sql = "DELETE FROM " + tableName + " WHERE " + idName + " = ?";

            query.createConnection();

            List<Object> preparedStatementData = new ArrayList<>();

            preparedStatementData.add(tableId);

            if (query.executeUpdate(sql, preparedStatementData) != 0)
            {
                deletionStatus = true;
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
        return deletionStatus;
    }

    public static int getMaxTableId(String tableName)
    {
        int id = -1;

        List<HashMap<String, Object>> resultSetList;

        Query query = new Query();

        try
        {
            List<Object> preparedStatementData = new ArrayList<>();

            query.createConnection();

            resultSetList = query.select("SELECT MAX(id) FROM " + tableName, preparedStatementData);

            if (resultSetList != null)
            {
                id = (int) resultSetList.get(0).get("MAX(id)");
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
        return id;
    }

    public static boolean checkIdExists(String tableName, String idName, Object tableId)
    {
        boolean exists = false;

        Query query = new Query();

        try
        {
            String sql = "SELECT * FROM " + tableName + " WHERE " + idName + " = ?";

            query.createConnection();

            List<Object> preparedStatementData = new ArrayList<>();

            preparedStatementData.add(tableId);

            if (!query.select(sql, preparedStatementData).isEmpty())
            {
                exists = true;
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
        return exists;
    }
}
