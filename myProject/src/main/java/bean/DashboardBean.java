package bean;

import java.util.HashMap;
import java.util.List;

public class DashboardBean
{
    private String totalDevice;

    private String downDevice;

    private String upDevice;

    private List<HashMap<String, Object>> topCpuUsageDevice;

    private List<HashMap<String, Object>> topMemoryUsageDevice;

    private List<HashMap<String, Object>> topDiskUsageDevice;

    public String getTotalDevice()
    {
        return totalDevice;
    }

    public void setTotalDevice(String totalDevice)
    {
        this.totalDevice = totalDevice;
    }

    public String getDownDevice()
    {
        return downDevice;
    }

    public void setDownDevice(String downDevice)
    {
        this.downDevice = downDevice;
    }

    public String getUpDevice()
    {
        return upDevice;
    }

    public void setUpDevice(String upDevice)
    {
        this.upDevice = upDevice;
    }

    public List<HashMap<String, Object>> getTopCpuUsageDevice()
    {
        return topCpuUsageDevice;
    }

    public void setTopCpuUsageDevice(List<HashMap<String, Object>> topCpuUsageDevice)
    {
        this.topCpuUsageDevice = topCpuUsageDevice;
    }

    public List<HashMap<String, Object>> getTopMemoryUsageDevice()
    {
        return topMemoryUsageDevice;
    }

    public void setTopMemoryUsageDevice(List<HashMap<String, Object>> topMemoryUsageDevice)
    {
        this.topMemoryUsageDevice = topMemoryUsageDevice;
    }

    public List<HashMap<String, Object>> getTopDiskUsageDevice()
    {
        return topDiskUsageDevice;
    }

    public void setTopDiskUsageDevice(List<HashMap<String, Object>> topDiskUsageDevice)
    {
        this.topDiskUsageDevice = topDiskUsageDevice;
    }
}
