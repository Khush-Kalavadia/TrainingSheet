package dao;

import java.util.ArrayList;
import java.util.List;

public class MonitorDao
{
    public static boolean insertSshDeviceMonitorTableRow(Object map_credential_id, Object map_discovery_id, Object name, Object ip_hostname, Object type)
    {
        boolean insertionStatus = false;

        Query query = new Query();

        List<Object> preparedStatementData = new ArrayList<>();

        try
        {
            preparedStatementData.add(map_credential_id);

            preparedStatementData.add(map_discovery_id);

            preparedStatementData.add(name);

            preparedStatementData.add(ip_hostname);

            preparedStatementData.add(type);

            String sql = "INSERT INTO monitor(map_credential_id, map_discovery_id, name, ip_hostname, type) VALUES (?, ?, ?, ?, ?)";

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

    public static boolean insertPingDeviceMonitorTableRow(Object map_discovery_id, Object name, Object ip_hostname, Object type)
    {
        boolean insertionStatus = false;

        Query query = new Query();

        List<Object> preparedStatementData = new ArrayList<>();

        try
        {
            preparedStatementData.add(map_discovery_id);

            preparedStatementData.add(name);

            preparedStatementData.add(ip_hostname);

            preparedStatementData.add(type);

            String sql = "INSERT INTO monitor(map_discovery_id, name, ip_hostname, type) VALUES (?, ?, ?, ?)";

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

    public static boolean updateDeviceMonitorTableRow(Object map_discovery_id, Object name, Object ip_hostname)
    {
        boolean updateStatus = false;

        Query query = new Query();

        List<Object> preparedStatementData = new ArrayList<>();

        try
        {
            preparedStatementData.add(name);

            preparedStatementData.add(ip_hostname);

            preparedStatementData.add(map_discovery_id);

            String sql = "UPDATE monitor SET name = ?, ip_hostname = ? WHERE map_discovery_id = ?";

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
}
