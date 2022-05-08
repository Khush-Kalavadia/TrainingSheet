package action;

import bean.DashboardBean;
import bean.DiscoveryBean;
import com.opensymphony.xwork2.ModelDriven;
import service.DashboardService;

public class DashboardAction implements ModelDriven<DashboardBean>
{
    private DashboardBean dashboardBean = new DashboardBean();

    public String loadDashboardDetails()
    {
        DashboardService.getDashboardDetails(dashboardBean);

        return "success";
    }

    @Override
    public DashboardBean getModel()
    {
        return dashboardBean;
    }
}
