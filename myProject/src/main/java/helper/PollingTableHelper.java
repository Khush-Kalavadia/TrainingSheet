package helper;

import dao.Query;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

public class PollingTableHelper
{
    public static void addPollingDeviceDetail(Query query,HashMap<String, Object> pingDeviceDetail, HashMap<String, Object> sshPollingDeviceDetail)
    {
        List<Object> preparedStatementData;

        String sql;

        try
        {
            preparedStatementData = new ArrayList<>();

            preparedStatementData.add(pingDeviceDetail.get("map_monitor_id"));

            preparedStatementData.add(pingDeviceDetail.get("dateTime"));

            preparedStatementData.add(pingDeviceDetail.get("availability"));

            preparedStatementData.add(pingDeviceDetail.get("packetLoss"));

            preparedStatementData.add(pingDeviceDetail.get("rttAvg"));

            preparedStatementData.add(pingDeviceDetail.get("packetsTransmitted"));

            preparedStatementData.add(pingDeviceDetail.get("packetsReceived"));

            sql = "INSERT INTO ping_polling(map_monitor_id, time, availability, packet_loss, avg_rtt, packet_transmitted, packet_received) VALUES (?, ?, ?, ?, ?, ?, ?)";

            query.executeUpdate(sql, preparedStatementData);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        try
        {
            if (sshPollingDeviceDetail != null)
            {
                preparedStatementData = new ArrayList<>();

                preparedStatementData.add(sshPollingDeviceDetail.get("map_monitor_id"));

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
    }

    public static List<HashMap<String, Object>> getTopUsageDevice(Query query, String measuringUnit, boolean orderByDescending, int number)
    {
        List<HashMap<String, Object>> resultSetList = null;

        try
        {
            List<String> columnList = new ArrayList<>();

            columnList.add("name");

            columnList.add("ip_hostname");

            columnList.add(measuringUnit + " as measuring_unit");

            HashMap<Query.Condition, String> condition = new HashMap<>();

            condition.put(Query.Condition.WHERE, "availability_status = 'up' AND (time, map_monitor_id) in (SELECT MAX(time), map_monitor_id FROM ssh_polling GROUP BY map_monitor_id)");

            condition.put(Query.Condition.ORDER_BY, measuringUnit + " " + (orderByDescending ? "DESC" : "ASC"));

            condition.put(Query.Condition.LIMIT, "?");

            List<Object> preparedStatementData = new ArrayList<>();

            preparedStatementData.add(number);

            resultSetList = query.commonSelect(columnList, "ssh_polling INNER JOIN monitor ON id = map_monitor_id", condition, preparedStatementData);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSetList;
    }
}
