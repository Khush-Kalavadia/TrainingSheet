package bean;

import java.util.HashMap;
import java.util.List;

public class MonitorBean
{
    private List<HashMap<String, Object>> monitorTableData;

    public List<HashMap<String, Object>> getMonitorTableData()
    {
        return monitorTableData;
    }

    public void setMonitorTableData(List<HashMap<String, Object>> monitorTableData)
    {
        this.monitorTableData = monitorTableData;
    }
}
