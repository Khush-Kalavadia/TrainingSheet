package action;

import bean.MonitorBean;
import com.opensymphony.xwork2.ModelDriven;
import service.MonitorService;

public class MonitorAction implements ModelDriven<MonitorBean>
{
    private MonitorBean monitorBean = new MonitorBean();

    public String loadMonitorTable()
    {
        MonitorService.getMonitorTableData(monitorBean);

        return "success";
    }

    public String deleteMonitorDevice()
    {
        MonitorService.deleteMonitorTableRow(monitorBean);

        return "success";
    }

    public String getMonitorDeviceDetail()
    {
        MonitorService.getMonitorDetail(monitorBean);

        return "success";
    }

    @Override
    public MonitorBean getModel()
    {
        return monitorBean;
    }
}
