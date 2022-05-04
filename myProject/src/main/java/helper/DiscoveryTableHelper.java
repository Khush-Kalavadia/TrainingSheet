package helper;

import dao.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiscoveryTableHelper
{
    public static int insertDiscoveryTableRow(Object name, Object ip_hostname, Object type)
    {
        int insertedRowNumber = 0;

        Query query = new Query();

        List<Object> preparedStatementData = new ArrayList<>();

        try
        {
            preparedStatementData.add(name);

            preparedStatementData.add(ip_hostname);

            preparedStatementData.add(type);

            String sql = "INSERT INTO discovery(name, ip_hostname, type) VALUES (?, ?, ?)";

            query.createConnection();

            insertedRowNumber = query.executeUpdate(sql, preparedStatementData);
       }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            query.releaseConnection();
        }
        return insertedRowNumber;
    }

    public static boolean updateDiscoveryTableRow(Object id, Object name, Object ip_hostname, Object type)
    {
        boolean updateStatus = false;

        Query query = new Query();

        List<Object> preparedStatementData = new ArrayList<>();

        try
        {
            preparedStatementData.add(name);

            preparedStatementData.add(ip_hostname);

            preparedStatementData.add(type);

            preparedStatementData.add(id);

            String sql = "UPDATE discovery SET name = ?, ip_hostname = ?, type = ? WHERE id = ?";

            query.createConnection();

            if (query.executeUpdate(sql, preparedStatementData) != 0)
            {
                updateStatus = true;
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
        return updateStatus;
    }

    public static void setProvision(int id, int provision)
    {
        Query query = new Query();

        try
        {
            List<Object> preparedStatementData = new ArrayList<>();

            preparedStatementData.add(provision);

            preparedStatementData.add(id);

            String sql = "UPDATE discovery SET provision = ? WHERE id = ?";

            query.createConnection();

            query.executeUpdate(sql, preparedStatementData);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            query.releaseConnection();
        }
    }

//    public static boolean setProvision(int id, int provision)
//    {
//        boolean setStatus = false;
//
//        Query query = new Query();
//
//        try
//        {
//            List<Object> preparedStatementData = new ArrayList<>();
//
//            preparedStatementData.add(provision);
//
//            preparedStatementData.add(id);
//
//            String sql = "UPDATE discovery SET provision = ? WHERE id = ?";
//
//            query.createConnection();
//
//            if (query.executeUpdate(sql, preparedStatementData) != 0)
//            {
//                setStatus = true;
//            }
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
//        finally
//        {
//            query.releaseConnection();
//        }
//        return setStatus;
//    }

    public static boolean checkIpHostnameAndTypeExists(Object ip_hostname, Object type)
    {
        return checkIpHostnameAndTypeExistsExceptGivenId(ip_hostname, type, null);
    }

    public static boolean checkIpHostnameAndTypeExistsExceptGivenId(Object ip_hostname, Object type, Object id)
    {
        boolean exists = false;

        Query query = new Query();

        String sql;

        try
        {
            List<Object> preparedStatementData = new ArrayList<>();

            preparedStatementData.add(ip_hostname);

            preparedStatementData.add(type);

            if (id != null)
            {
                preparedStatementData.add(id);

                sql = "SELECT * FROM discovery WHERE ip_hostname = ? and type = ? and id != ?";
            }
            else
            {
                sql = "SELECT * FROM discovery WHERE ip_hostname = ? and type = ?";
            }

            query.createConnection();

            List<HashMap<String, Object>> resultList = query.select(sql, preparedStatementData);

            if (resultList != null && !resultList.isEmpty())
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
