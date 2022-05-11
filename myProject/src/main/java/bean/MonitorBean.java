package bean;

public class MonitorBean
{
    private int id;

    private boolean operationSuccess;

    private String ipHostname;

    private String type;

    private String availability;

    private float pastAvailabilityPercent;

    private String packetsTransmitted;

    private String packetsReceived;

    private String packetLoss;

    private String rttAvg;

    private float idleCpuPercentage;

    private float totalMemoryGb;

    private float usedMemoryGb;

    private float totalDiskGb;

    private float usedDiskGb;

    private String uptime;

    private float[] packetLossChartData;

    private String[] timeChartData;

    private String[][] monitorTableData;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String[][] getMonitorTableData()
    {
        return monitorTableData;
    }

    public void setMonitorTableData(String[][] monitorTableData)
    {
        this.monitorTableData = monitorTableData;
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

    public String getPacketsTransmitted()
    {
        return packetsTransmitted;
    }

    public void setPacketsTransmitted(String packetsTransmitted)
    {
        this.packetsTransmitted = packetsTransmitted;
    }

    public String getPacketsReceived()
    {
        return packetsReceived;
    }

    public void setPacketsReceived(String packetsReceived)
    {
        this.packetsReceived = packetsReceived;
    }

    public String getPacketLoss()
    {
        return packetLoss;
    }

    public void setPacketLoss(String packetLoss)
    {
        this.packetLoss = packetLoss;
    }

    public String getRttAvg()
    {
        return rttAvg;
    }

    public void setRttAvg(String rttAvg)
    {
        this.rttAvg = rttAvg;
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

    public float[] getPacketLossChartData()
    {
        return packetLossChartData;
    }

    public void setPacketLossChartData(float[] packetLossChartData)
    {
        this.packetLossChartData = packetLossChartData;
    }

    public String[] getTimeChartData()
    {
        return timeChartData;
    }

    public void setTimeChartData(String[] timeChartData)
    {
        this.timeChartData = timeChartData;
    }
}
