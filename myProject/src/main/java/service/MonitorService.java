package service;

import bean.MonitorBean;
import dao.CommonTableDao;
import dao.PollingDao;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MonitorService
{
    public static void getMonitorTableData(MonitorBean monitorBean)
    {
        try
        {
            monitorBean.setMonitorTableData(CommonTableDao.getTableData("monitor"));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void deleteMonitorTableRow(MonitorBean monitorBean)
    {
        try
        {
            monitorBean.setOperationSuccess(CommonTableDao.deleteTableRowUsingId("monitor", "id", monitorBean.getId()));      //todo record not deleted from credential as it might be in discovery. so will have to check if it is not even present in the discovery
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void getMonitorDetail(MonitorBean monitorBean)
    {
        try
        {
            HashMap<String, Object> monitorTableData = CommonTableDao.getTableRowUsingId("monitor", "id", monitorBean.getId());

            monitorBean.setIpHostname((String) monitorTableData.get("ip_hostname"));

            monitorBean.setType((String) monitorTableData.get("type"));

            HashMap<String, Object> pingPollingTableData = PollingDao.getLatestTableRowUsingId("ping_polling", "map_monitor_id", monitorBean.getId());

            if (pingPollingTableData.get("packet_loss") != null)
            {
                monitorBean.setPacketLoss((float) pingPollingTableData.get("packet_loss"));
            }
            else
            {
                monitorBean.setPacketLoss(-1);
            }

            if (pingPollingTableData.get("min_rtt") != null)
            {
                monitorBean.setRttMin((float) pingPollingTableData.get("min_rtt"));
            }
            else
            {
                monitorBean.setRttMin(-1);
            }

            if (pingPollingTableData.get("packet_transmitted") != null)
            {
                monitorBean.setPacketsTransmitted((int) pingPollingTableData.get("packet_transmitted"));
            }
            else
            {
                monitorBean.setPacketsTransmitted(-1);
            }

            if (pingPollingTableData.get("packet_received") != null)
            {
                monitorBean.setPacketsReceived((int) pingPollingTableData.get("packet_received"));
            }
            else
            {
                monitorBean.setPacketsReceived(-1);
            }

            monitorBean.setPastAvailabilityPercent(PollingDao.getPastAvailabilityPercent(monitorBean.getId(), 24));

            HashMap<String, Object[]> axisData = PollingDao.getPastPacketLossData(monitorBean.getId(), 20);

            monitorBean.setPacketLossChartData(axisData.get("packetLossData"));

            String[] timeStringArray = new String[20];

            int arrayCount = 0;

            for (Object timeObject : axisData.get("timeData"))
            {
                timeStringArray[arrayCount++] = timeObject.toString();
            }

            monitorBean.setTimeChartData(timeStringArray);

            monitorBean.setAvailability((String) monitorTableData.get("availability_status"));

            if (monitorTableData.get("type").equals("ssh"))
            {
                HashMap<String, Object> sshPollingTableData = PollingDao.getLatestTableRowUsingId("ssh_polling", "map_monitor_id", monitorBean.getId());

                if (sshPollingTableData.get("total_memory_gb") != null)
                {
                    monitorBean.setTotalMemoryGb((float) sshPollingTableData.get("total_memory_gb"));
                }
                else
                {
                    monitorBean.setTotalMemoryGb(-1);
                }

                if (sshPollingTableData.get("total_disk_gb") != null)
                {
                    monitorBean.setTotalDiskGb((float) sshPollingTableData.get("total_disk_gb"));
                }
                else
                {
                    monitorBean.setTotalDiskGb(-1);
                }

                monitorBean.setUptime((String) sshPollingTableData.get("uptime"));

                if (sshPollingTableData.get("used_memory_gb") != null)
                {
                    monitorBean.setUsedMemoryGb((float) sshPollingTableData.get("used_memory_gb"));
                }
                else
                {
                    monitorBean.setUsedMemoryGb(-1);
                }

                if (sshPollingTableData.get("used_disk_gb") != null)
                {
                    monitorBean.setUsedDiskGb((float) sshPollingTableData.get("used_disk_gb"));
                }
                else
                {
                    monitorBean.setUsedDiskGb(-1);
                }

                if (sshPollingTableData.get("idle_cpu_percent") != null)
                {
                    monitorBean.setIdleCpuPercentage((float) sshPollingTableData.get("idle_cpu_percent"));
                }
                else
                {
                    monitorBean.setIdleCpuPercentage(-1);
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
