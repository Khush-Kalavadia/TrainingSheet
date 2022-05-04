package action;

import bean.MonitorBean;
import com.opensymphony.xwork2.ModelDriven;
import service.MonitorService;

public class MonitorAction implements ModelDriven<MonitorBean>
{
    private MonitorBean monitorBean = new MonitorBean();

    public String loadMonitorTable()
    {
        try
        {
            MonitorService.getMonitorTableData(monitorBean);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return "success";
    }

    public String deleteMonitorDevice()
    {
        try
        {
            MonitorService.deleteMonitorTableRow(monitorBean);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return "success";
    }

    public String getMonitorDeviceDetail()
    {
        try
        {
            MonitorService.getMonitorDetail(monitorBean);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return "success";
    }

    @Override
    public MonitorBean getModel()
    {
        return monitorBean;
    }
}
