package action;

import bean.DiscoveryBean;
import com.opensymphony.xwork2.ModelDriven;
import service.DiscoveryService;

public class DiscoveryAction implements ModelDriven<DiscoveryBean>
{
    private DiscoveryBean discoveryBean = new DiscoveryBean();

    public String insertDiscoveryDevice()
    {
        try
        {
            DiscoveryService.insertDiscoveryCredentialTableRow(discoveryBean);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return "success";
    }

    public String editDiscoveryDevice()
    {
        try
        {
            DiscoveryService.editDiscoveryCredentialTableRow(discoveryBean);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return "success";
    }

    public String getDiscoveryDevice()
    {
        try
        {
            DiscoveryService.getDiscoveryCredentialTableData(discoveryBean);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return "success";
    }

    public String loadDiscoveryTable()
    {
        try
        {
            DiscoveryService.getDiscoveryTableData(discoveryBean);            //checked if null is returned
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return "success";
    }

    public String deleteDiscoveryDevice()
    {
        try
        {
            DiscoveryService.deleteDiscoveryTableRow(discoveryBean);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return "success";
    }

    public String provisionDevice(){
        try
        {
            DiscoveryService.addMonitorDevice(discoveryBean);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return "success";
    }

    @Override
    public DiscoveryBean getModel()
    {
        return discoveryBean;
    }
}
