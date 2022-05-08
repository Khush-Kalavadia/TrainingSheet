package service;

import bean.DashboardBean;
import dao.Query;
import helper.PollingTableHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DashboardService
{
    public static void getDashboardDetails(DashboardBean dashboardBean)
    {
        Query query = null;

        try
        {
            query = new Query();

            List<String> columnList = new ArrayList<>();

            columnList.add("availability_status");

            columnList.add("COUNT(*) AS device_count");

            HashMap<Query.Condition, String> conditionStringHashMap = new HashMap<>();

            conditionStringHashMap.put(Query.Condition.GROUP_BY, "availability_status");

            query.getConnection();

            List<HashMap<String, Object>> resultList = query.commonSelect(columnList, "monitor", conditionStringHashMap, null);

            if (resultList != null && !resultList.isEmpty())
            {
                int rowCount = 0;

                long totalDevice = 0;

                for (HashMap<String, Object> resultRow : resultList)
                {
                    Object availabilityStatus = resultRow.get("availability_status");

                    Object deviceCount = resultList.get(rowCount).get("device_count");

                    if (availabilityStatus != null && deviceCount != null)
                    {
                        if (availabilityStatus.equals("up"))
                        {
                            dashboardBean.setUpDevice((Long) deviceCount);
                        }
                        else if (availabilityStatus.equals("down"))
                        {
                            dashboardBean.setDownDevice((Long) deviceCount);
                        }
                        totalDevice += (Long) deviceCount;
                    }
                    rowCount++;
                }
                dashboardBean.setTotalDevice(totalDevice);
            }

            List<HashMap<String, Object>> topMemoryUsagePercentDevice = PollingTableHelper.getTopUsageDevice(query, "used_memory_gb/total_memory_gb", true, 3);

            for (HashMap<String, Object> deviceDetail : topMemoryUsagePercentDevice)
            {
                Object memoryUsagePercent = deviceDetail.get("measuring_unit");

                if (memoryUsagePercent != null)
                {
                    deviceDetail.put("measuring_unit", new DecimalFormat("###.##").format((double) memoryUsagePercent * 100));
                }
            }

            dashboardBean.setTopMemoryUsageDevice(topMemoryUsagePercentDevice);

            List<HashMap<String, Object>> topDiskUsagePercentDevice = PollingTableHelper.getTopUsageDevice(query, "used_disk_gb/total_disk_gb", true, 3);

            for (HashMap<String, Object> deviceDetail : topDiskUsagePercentDevice)
            {
                Object diskUsagePercent = deviceDetail.get("measuring_unit");

                if (diskUsagePercent != null)
                {
                    deviceDetail.put("measuring_unit", new DecimalFormat("###.##").format((double) diskUsagePercent * 100));
                }
            }

            dashboardBean.setTopDiskUsageDevice(topDiskUsagePercentDevice);

            List<HashMap<String, Object>> topCpuUsageDeviceList = PollingTableHelper.getTopUsageDevice(query, "idle_cpu_percent", false, 3);

            for (HashMap<String, Object> deviceDetail : topCpuUsageDeviceList)
            {
                Object idleCpuPercent = deviceDetail.get("measuring_unit");

                if (idleCpuPercent != null)
                {
                    deviceDetail.put("measuring_unit", new DecimalFormat("###.##").format(100.0 - (float) idleCpuPercent));
                }
            }

            dashboardBean.setTopCpuUsageDevice(topCpuUsageDeviceList);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (query != null)
            {
                query.releaseConnection();
            }
        }
    }
}
