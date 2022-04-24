package action;

import bean.MonitorBean;
import com.opensymphony.xwork2.ModelDriven;

public class MonitorAction implements ModelDriven<MonitorBean>
{
    private MonitorBean monitorBean = new MonitorBean();

    public String loadMonitorTable()
    {
        return "success";
    }

    @Override
    public MonitorBean getModel()
    {
        return monitorBean;
    }
}
