package service;

import bean.MonitorBean;
import helper.CommonTableHelper;
import helper.PollingTableHelper;

import java.util.HashMap;

public class MonitorService
{
    public static void getMonitorTableData(MonitorBean monitorBean)
    {
        try
        {
            monitorBean.setMonitorTableData(CommonTableHelper.getTableData("monitor"));
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
            monitorBean.setOperationSuccess(CommonTableHelper.deleteTableRowUsingId("monitor", "id", monitorBean.getId()));
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
            HashMap<String, Object> monitorTableData = CommonTableHelper.getTableRowUsingId("monitor", "id", monitorBean.getId());

            monitorBean.setIpHostname((String) monitorTableData.get("ip_hostname"));

            monitorBean.setType((String) monitorTableData.get("type"));

            monitorBean.setAvailability((String) monitorTableData.get("availability_status"));

            HashMap<String, Object> pingPollingTableData = PollingTableHelper.getLatestTableRowUsingId("ping_polling", "map_monitor_id", monitorBean.getId());

            if (pingPollingTableData != null)
            {
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
            }
            else
            {
                monitorBean.setPacketLoss(-1);

                monitorBean.setRttMin(-1);

                monitorBean.setPacketsTransmitted(-1);

                monitorBean.setPacketsReceived(-1);
            }

            monitorBean.setPastAvailabilityPercent(PollingTableHelper.getPastAvailabilityPercent(monitorBean.getId(), 24));

            HashMap<String, Object[]> axisData = PollingTableHelper.getPastPacketLossAndTimeData(monitorBean.getId(), 20);

            String[] timeStringArray = new String[20];

            float[] packetLossArray = new float[20];

//            for (int i = 0; i < 20; i++)
//            {
//                packetLossArray[i] = -1;
//            }

            if (axisData != null)
            {
                int arrayCount = 0;

                if (axisData.get("timeData") != null)
                {
                    for (Object timeObject : axisData.get("timeData"))
                    {
                        timeStringArray[arrayCount++] = timeObject.toString();
                    }
                }

                if (axisData.get("packetLossData") != null)
                {
                    arrayCount = 0;

                    for (Object packetLoss : axisData.get("packetLossData"))
                    {
                        if (packetLoss != null)
                        {
                            packetLossArray[arrayCount++] = (float) packetLoss;
                        }
                    }
                }
            }

            monitorBean.setPacketLossChartData(packetLossArray);

            monitorBean.setTimeChartData(timeStringArray);

            if (monitorTableData.get("type").equals("ssh"))
            {
                HashMap<String, Object> sshPollingTableData = PollingTableHelper.getLatestTableRowUsingId("ssh_polling", "map_monitor_id", monitorBean.getId());

                if (sshPollingTableData != null)
                {
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
                else
                {
                    monitorBean.setTotalMemoryGb(-1);

                    monitorBean.setTotalDiskGb(-1);

                    monitorBean.setUsedMemoryGb(-1);

                    monitorBean.setUsedDiskGb(-1);

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
