package action;

import bean.DiscoveryBean;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.SessionAware;
import service.DiscoveryService;

import java.util.Map;

public class DiscoveryAction implements ModelDriven<DiscoveryBean>
{
    private DiscoveryBean discoveryBean = new DiscoveryBean();

    public String insertDiscoveryDevice()
    {
        DiscoveryService.insertDiscoveryCredentialTableRow(discoveryBean);

        return "success";
    }

    public String editDiscoveryDevice()
    {
        DiscoveryService.editDiscoveryCredentialTableRow(discoveryBean);

        return "success";
    }

    public String getDiscoveryDevice()
    {
        DiscoveryService.getDiscoveryCredentialTableData(discoveryBean);

        return "success";
    }

    public String loadDiscoveryTable()
    {
        DiscoveryService.getDiscoveryTableData(discoveryBean);

        return "success";
    }

    public String deleteDiscoveryDevice()
    {
        DiscoveryService.deleteDiscoveryTableRow(discoveryBean);

        return "success";
    }

    public String provisionDevice()
    {
        DiscoveryService.addMonitorDevice(discoveryBean);

        return "success";
    }

    @Override
    public DiscoveryBean getModel()
    {
        return discoveryBean;
    }
}
