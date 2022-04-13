package service;

import dao.Query;

import java.util.ArrayList;
import java.util.List;

public class TableDataService
{
    public List<List<Object>> getTableData(String tableName)
    {
        List<List<Object>> resultSetList = null;

        Query query = new Query();

        try
        {
            List<Object> preparedStatementData = new ArrayList<>();

            query.createConnection();

            //fixme hope this way we can use select method which i created without adding any value in list
            resultSetList = query.select("SELECT id, name, ip_hostname, type, provision FROM " + tableName, preparedStatementData);
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

    public boolean deleteTableRowUsingId(String tableName, Object tableId)
    {
        boolean deletionStatus = false;

        Query query = new Query();

        try
        {
            query.createConnection();

            String sql = "DELETE FROM " + tableName + " WHERE id = ?";

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

//    public boolean deleteTableRowUsingIpHostname(String tableName, Object tableIpHostname)
//    {
//        boolean deletionStatus = false;
//
//        Query query = new Query();
//
//        try
//        {
//            query.createConnection();
//
//            String sql = "DELETE FROM " + tableName + " WHERE ip_hostname = ?";
//
//            List<Object> preparedStatementData = new ArrayList<>();
//
//            preparedStatementData.add(tableIpHostname);
//
//            if (query.executeUpdate(sql, preparedStatementData) != 0)
//            {
//                deletionStatus = true;
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
//        return deletionStatus;
//    }

    public boolean insertDiscoveryTableRow(List<Object> preparedStatementData)
    {
        boolean insertionStatus = false;

        Query query = new Query();

        try
        {
            query.createConnection();

            String sql = "INSERT INTO discovery(name, ip_hostname, type) VALUES (?, ?, ?)";

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

    public boolean insertCredentialTableRow(List<Object> preparedStatementData)
    {
        boolean insertionStatus = false;

        Query query = new Query();

        try
        {
            query.createConnection();

            String sql = "INSERT INTO credential(map_discovery_id, username, password) VALUES (?, ?, ?)";

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

    public int getDiscoveryRowId(String ipHostname, String type)
    {
        int id = -1;

        List<List<Object>> resultSetList;

        Query query = new Query();

        try
        {
            List<Object> preparedStatementData = new ArrayList<>();

            preparedStatementData.add(ipHostname);

            preparedStatementData.add(type);

            query.createConnection();

            resultSetList = query.select("SELECT id FROM discovery where ip_hostname LIKE BINARY ? AND type LIKE ?", preparedStatementData);

            if (resultSetList != null)
            {
                id = (int) resultSetList.get(0).get(0);
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

    public List<Object> getTableRowUsingId(String tableName, String idName, Object tableId)
    {
        List<List<Object>> resultSetList = null;

        List<Object> returnRow = null;

        Query query = new Query();

        try
        {
            String sql = "SELECT * FROM " + tableName + " WHERE " + idName + " = ?";

            List<Object> preparedStatementData = new ArrayList<>();

            preparedStatementData.add(tableId);

            query.createConnection();

            resultSetList = query.select(sql, preparedStatementData);

            returnRow = resultSetList.get(0);
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

//    public List<Object> getTableRowUsingIpHostname(String tableName, Object tableIpHostname)
//    {
//        List<List<Object>> resultSetList = null;
//
//        List<Object> returnRow = null;
//
//        Query query = new Query();
//
//        try
//        {
//            String sql = "SELECT * FROM " + tableName + " WHERE ip_hostname = ?";
//
//            List<Object> preparedStatementData = new ArrayList<>();
//
//            preparedStatementData.add(tableIpHostname);
//
//            query.createConnection();
//
//            resultSetList = query.select(sql, preparedStatementData);
//
//            returnRow = resultSetList.get(0);
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
//        finally
//        {
//            query.releaseConnection();
//        }
//        return returnRow;
//    }

    public boolean updateDiscoveryTableRow(List<Object> preparedStatementData)
    {
        boolean updateStatus = false;

        Query query = new Query();

        try
        {
            query.createConnection();

            String sql = "UPDATE discovery SET name = ?, ip_hostname = ?, type = ? WHERE id = ?";

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

    public boolean updateCredentialTableRow(List<Object> preparedStatementData)
    {
        boolean updateStatus = false;

        Query query = new Query();

        try
        {
            query.createConnection();

            String sql = "UPDATE credential SET username = ?, password = ? WHERE map_discovery_id = ?";

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


//    public static void main(String[] args)
//    {
//        ConnectionStartup connectionStartup = new ConnectionStartup();
//
//        connectionStartup.init();
//
//        TableDataService tableDataService = new TableDataService();
//
//        System.out.println(tableDataService.getTableData("discovery"));
//    }


}
