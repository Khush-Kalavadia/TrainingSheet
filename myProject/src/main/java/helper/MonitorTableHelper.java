package helper;

import dao.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MonitorTableHelper
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

    public static boolean updateMonitorTableRow(Object map_discovery_id, Object name, Object ip_hostname)
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

    public static void updateMonitorAvailability(Object id, Object availability)
    {
        Query query = new Query();

        List<Object> preparedStatementData = new ArrayList<>();

        try
        {
            preparedStatementData.add(availability);

            preparedStatementData.add(id);

            String sql = "UPDATE monitor SET availability_status = ? WHERE id = ?";

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

    public static HashMap<String, Long> getDeviceCountOnAvailability()
    {
        Query query = new Query();

        List<Object> preparedStatementData;

        HashMap<String, Long> returnData = null;

        try
        {
            String sql = "SELECT availability_status, COUNT(*) AS device_count FROM monitor GROUP BY availability_status";

            preparedStatementData = new ArrayList<>();

            query.createConnection();

            List<HashMap<String, Object>> resultList = query.select(sql, preparedStatementData);

            if (resultList != null && !resultList.isEmpty())
            {
                returnData = new HashMap<>();

                int rowCount = 0;

                long totalDevice = 0;

                for (HashMap<String, Object> resultRow : resultList)
                {
                    Object availabilityStatus = resultRow.get("availability_status");

                    Object deviceCount = resultList.get(rowCount).get("device_count");

                    if (availabilityStatus != null && deviceCount != null)
                    {
                        if (availabilityStatus.equals("up"))          //check not null
                        {
                            returnData.put("up", (Long) deviceCount);
                        }
                        else if (availabilityStatus.equals("down"))
                        {
                            returnData.put("down", (Long) deviceCount);
                        }

                        totalDevice += (Long) deviceCount;
                    }
                    rowCount++;
                }

                returnData.put("total", totalDevice);
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
        return returnData;
    }
}
