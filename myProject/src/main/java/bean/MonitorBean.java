package bean;

import java.util.HashMap;
import java.util.List;

public class MonitorBean
{
    private List<HashMap<String, Object>> monitorTableData;

    private int id;

    private boolean operationSuccess;

    private String ipHostname;

    private String type;

    private String availability;

    private float pastAvailabilityPercent;

    private int packetsTransmitted;

    private int packetsReceived;

    private float packetLoss;

    private float rttMin;

    private float idleCpuPercentage;

    private float totalMemoryGb;

    private float usedMemoryGb;

    private float totalDiskGb;

    private float usedDiskGb;

    private String uptime;

    private Object[] packetLossChartData;

    private Object[] timeChartData;

    public List<HashMap<String, Object>> getMonitorTableData()
    {
        return monitorTableData;
    }

    public void setMonitorTableData(List<HashMap<String, Object>> monitorTableData)
    {
        this.monitorTableData = monitorTableData;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public boolean isOperationSuccess()
    {
        return operationSuccess;
    }

    public void setOperationSuccess(boolean operationSuccess)
    {
        this.operationSuccess = operationSuccess;
    }

    public String getIpHostname()
    {
        return ipHostname;
    }

    public void setIpHostname(String ipHostname)
    {
        this.ipHostname = ipHostname;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getAvailability()
    {
        return availability;
    }

    public void setAvailability(String availability)
    {
        this.availability = availability;
    }

    public float getPastAvailabilityPercent()
    {
        return pastAvailabilityPercent;
    }

    public void setPastAvailabilityPercent(float pastAvailabilityPercent)
    {
        this.pastAvailabilityPercent = pastAvailabilityPercent;
    }

    public int getPacketsTransmitted()
    {
        return packetsTransmitted;
    }

    public void setPacketsTransmitted(int packetsTransmitted)
    {
        this.packetsTransmitted = packetsTransmitted;
    }

    public int getPacketsReceived()
    {
        return packetsReceived;
    }

    public void setPacketsReceived(int packetsReceived)
    {
        this.packetsReceived = packetsReceived;
    }

    public float getPacketLoss()
    {
        return packetLoss;
    }

    public void setPacketLoss(float packetLoss)
    {
        this.packetLoss = packetLoss;
    }

    public float getRttMin()
    {
        return rttMin;
    }

    public void setRttMin(float rttMin)
    {
        this.rttMin = rttMin;
    }

    public float getIdleCpuPercentage()
    {
        return idleCpuPercentage;
    }

    public void setIdleCpuPercentage(float idleCpuPercentage)
    {
        this.idleCpuPercentage = idleCpuPercentage;
    }

    public float getTotalMemoryGb()
    {
        return totalMemoryGb;
    }

    public void setTotalMemoryGb(float totalMemoryGb)
    {
        this.totalMemoryGb = totalMemoryGb;
    }

    public float getUsedMemoryGb()
    {
        return usedMemoryGb;
    }

    public void setUsedMemoryGb(float usedMemoryGb)
    {
        this.usedMemoryGb = usedMemoryGb;
    }

    public float getTotalDiskGb()
    {
        return totalDiskGb;
    }

    public void setTotalDiskGb(float totalDiskGb)
    {
        this.totalDiskGb = totalDiskGb;
    }

    public float getUsedDiskGb()
    {
        return usedDiskGb;
    }

    public void setUsedDiskGb(float usedDiskGb)
    {
        this.usedDiskGb = usedDiskGb;
    }

    public String getUptime()
    {
        return uptime;
    }

    public void setUptime(String uptime)
    {
        this.uptime = uptime;
    }

    public Object[] getPacketLossChartData()
    {
        return packetLossChartData;
    }

    public void setPacketLossChartData(Object[] packetLossChartData)
    {
        this.packetLossChartData = packetLossChartData;
    }

    public Object[] getTimeChartData()
    {
        return timeChartData;
    }

    public void setTimeChartData(Object[] timeChartData)
    {
        this.timeChartData = timeChartData;
    }
}
