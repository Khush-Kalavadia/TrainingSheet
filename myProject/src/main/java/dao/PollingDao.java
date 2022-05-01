package dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PollingDao
{
    public static void addPollingDeviceDetail(HashMap<String, Object> pingDeviceDetail, HashMap<String, Object> sshPollingDeviceDetail)
    {
        Query query = new Query();

        List<Object> preparedStatementData;

        try
        {
            query.createConnection();

            String sql;

            preparedStatementData = new ArrayList<>();

            preparedStatementData.add(pingDeviceDetail.get("map_discovery_id"));

            preparedStatementData.add(pingDeviceDetail.get("dateTime"));

            preparedStatementData.add(pingDeviceDetail.get("availability"));

            preparedStatementData.add(pingDeviceDetail.get("packetLoss"));

            preparedStatementData.add(pingDeviceDetail.get("rttMin"));

            preparedStatementData.add(pingDeviceDetail.get("packetsTransmitted"));

            preparedStatementData.add(pingDeviceDetail.get("packetsReceived"));

            sql = "INSERT INTO ping_polling(map_monitor_id, time, availability, packet_loss, min_rtt, packet_transmitted, packet_received) VALUES (?, ?, ?, ?, ?, ?, ?)";

            query.executeUpdate(sql, preparedStatementData);

            if (sshPollingDeviceDetail != null)
            {
                preparedStatementData = new ArrayList<>();

                preparedStatementData.add(sshPollingDeviceDetail.get("map_discovery_id"));

                preparedStatementData.add(sshPollingDeviceDetail.get("dateTime"));

                preparedStatementData.add(sshPollingDeviceDetail.get("idleCpuPercent"));

                preparedStatementData.add(sshPollingDeviceDetail.get("totalMemoryGB"));

                preparedStatementData.add(sshPollingDeviceDetail.get("usedMemoryGB"));

                preparedStatementData.add(sshPollingDeviceDetail.get("totalDiskGB"));

                preparedStatementData.add(sshPollingDeviceDetail.get("usedDiskGB"));

                preparedStatementData.add(sshPollingDeviceDetail.get("upTime"));

                sql = "INSERT INTO ssh_polling(map_monitor_id, time, idle_cpu_percent, total_memory_gb, used_memory_gb, total_disk_gb, used_disk_gb, uptime) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                query.executeUpdate(sql, preparedStatementData);
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
    }

    public static float getPastAvailabilityPercent(int monitorId, int hour)
    {
        float availabilityPercent = 0;

        Query query = new Query();

        try
        {
            String sql = "SELECT AVG(availability) as past_availability_percent FROM ping_polling WHERE map_monitor_id = ? and time BETWEEN ? AND ?";

            List<Object> preparedStatementData = new ArrayList<>();

            preparedStatementData.add(monitorId);

            preparedStatementData.add(new Timestamp(new Date().getTime() - hour * 3600 * 1000));

            preparedStatementData.add(new Timestamp(new Date().getTime()));

            query.createConnection();

            List<HashMap<String, Object>> resultList = query.select(sql, preparedStatementData);

            if (resultList != null && !resultList.isEmpty())
            {
                availabilityPercent = ((BigDecimal) resultList.get(0).get("past_availability_percent")).floatValue() * (float) 100.0;
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
        return availabilityPercent;
    }

    public static HashMap<String, Object> getLatestTableRowUsingId(String tableName, String idName, Object tableId)
    {
        HashMap<String, Object> returnRow = null;

        Query query = new Query();

        try
        {
            String sql = "SELECT * FROM " + tableName + " WHERE time = (SELECT  MAX(time) FROM " + tableName + " WHERE " + idName + " = ?)";

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

    public static HashMap<String, Object[]> getPastPacketLossData(int monitorId, int numberOfObservation)
    {
        Object[] packetLossData = new Object[numberOfObservation];

        Object[] timeData = new Object[numberOfObservation];

        HashMap<String, Object[]> sendResult = null;

        Query query = new Query();

        try
        {
            String sql = "SELECT time, packet_loss FROM ping_polling WHERE map_monitor_id = ? ORDER BY time DESC LIMIT ?";

            List<Object> preparedStatementData = new ArrayList<>();

            preparedStatementData.add(monitorId);

            preparedStatementData.add(numberOfObservation);

            query.createConnection();

            List<HashMap<String, Object>> resultList = query.select(sql, preparedStatementData);

            if (resultList != null && !resultList.isEmpty())
            {
                int rowCount = 0;

                for (HashMap<String, Object> resultRow : resultList)
                {
                    timeData[rowCount] = resultRow.get("time");

                    packetLossData[rowCount] = resultRow.get("packet_loss");

                    rowCount++;
                }
                sendResult = new HashMap<>();

                sendResult.put("timeData", timeData);

                sendResult.put("packetLossData", packetLossData);
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
        return sendResult;
    }
}
