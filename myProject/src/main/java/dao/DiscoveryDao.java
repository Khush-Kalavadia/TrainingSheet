package dao;

import commonutil.ConnectionStartup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiscoveryDao
{
    public static boolean insertDiscoveryTableRow(Object name, Object ip_hostname, Object type)
    {
        boolean insertionStatus = false;

        Query query = new Query();

        List<Object> preparedStatementData = new ArrayList<>();

        try
        {
            preparedStatementData.add(name);

            preparedStatementData.add(ip_hostname);

            preparedStatementData.add(type);

            String sql = "INSERT INTO discovery(name, ip_hostname, type) VALUES (?, ?, ?)";

            query.createConnection();

            if (query.executeUpdate(sql, preparedStatementData) != 0)
            {
                insertionStatus = true;
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
        return insertionStatus;
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

//    public static String getDiscoveryIpHostname(int id)
//    {
//        String ipHostname = null;
//
//        List<HashMap<String, Object>> resultSetList;
//
//        Query query = new Query();
//
//        try
//        {
//            List<Object> preparedStatementData = new ArrayList<>();
//
//            preparedStatementData.add(id);
//
//            query.createConnection();
//
//            resultSetList = query.select("SELECT ip_hostname FROM discovery where id = ?", preparedStatementData);
//
//            if (resultSetList != null)
//            {
//                ipHostname = (String) resultSetList.get(0).get("ip_hostname");
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
//        return ipHostname;
//    }

    public static boolean setProvision(int id, int provision)
    {
        boolean setStatus = false;

        Query query = new Query();

        try
        {
            List<Object> preparedStatementData = new ArrayList<>();

            preparedStatementData.add(provision);

            preparedStatementData.add(id);

            String sql = "UPDATE discovery SET provision = ? WHERE id = ?";

            query.createConnection();

            if (query.executeUpdate(sql, preparedStatementData) != 0)
            {
                setStatus = true;
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
        return setStatus;
    }

}
