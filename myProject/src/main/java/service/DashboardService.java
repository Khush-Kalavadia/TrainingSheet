package service;

import bean.DashboardBean;
import com.jcraft.jsch.HASH;
import helper.MonitorTableHelper;
import helper.PollingTableHelper;
import org.omg.CORBA.OBJ_ADAPTER;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

public class DashboardService
{
    public static void getDashboardDetails(DashboardBean dashboardBean)
    {
        try
        {
            HashMap<String, Long> deviceCountOnAvailability = MonitorTableHelper.getDeviceCountOnAvailability();

            if (deviceCountOnAvailability != null && !deviceCountOnAvailability.isEmpty())
            {
                dashboardBean.setTotalDevice(deviceCountOnAvailability.get("total"));

                dashboardBean.setUpDevice(deviceCountOnAvailability.get("up"));

                dashboardBean.setDownDevice(deviceCountOnAvailability.get("down"));
            }

            dashboardBean.setTopMemoryUsageDevice(PollingTableHelper.getTopUsageDevice("used_memory_gb", true, 3));

            dashboardBean.setTopDiskUsageDevice(PollingTableHelper.getTopUsageDevice("used_disk_gb", true, 3));

            List<HashMap<String, Object>> topCpuUsageDeviceList = PollingTableHelper.getTopUsageDevice("idle_cpu_percent", false, 3);

            for (HashMap<String, Object> deviceDetail : topCpuUsageDeviceList)
            {
                Object idleCpuPercent = deviceDetail.get("measuring_unit");

                if (idleCpuPercent != null && !idleCpuPercent.equals("-"))
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
    }
}
