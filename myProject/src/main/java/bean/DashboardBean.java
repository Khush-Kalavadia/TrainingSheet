package bean;

import java.util.HashMap;
import java.util.List;

public class DashboardBean
{
    private long totalDevice;

    private long downDevice;

    private long upDevice;

    private List<HashMap<String, Object>> topCpuUsageDevice;

    private List<HashMap<String, Object>> topMemoryUsageDevice;

    private List<HashMap<String, Object>> topDiskUsageDevice;

    public long getTotalDevice()
    {
        return totalDevice;
    }

    public void setTotalDevice(long totalDevice)
    {
        this.totalDevice = totalDevice;
    }

    public long getDownDevice()
    {
        return downDevice;
    }

    public void setDownDevice(long downDevice)
    {
        this.downDevice = downDevice;
    }

    public long getUpDevice()
    {
        return upDevice;
    }

    public void setUpDevice(long upDevice)
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
