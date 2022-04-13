package action;

import bean.DiscoveryDataBean;
import com.opensymphony.xwork2.ModelDriven;
import service.TableDataService;

import java.util.ArrayList;
import java.util.List;

public class DiscoveryAction implements ModelDriven<DiscoveryDataBean>
{
    private DiscoveryDataBean discoveryDataBean = new DiscoveryDataBean();

    private TableDataService tableDataService = new TableDataService();

    public String insertDiscoveryRow()
    {
        List<Object> discoveryTableData = new ArrayList<>();

        List<Object> credentialsTableData;

        try
        {
            discoveryDataBean.setOperationSuccess(false);

            discoveryTableData.add(discoveryDataBean.getName());

            discoveryTableData.add(discoveryDataBean.getIpHostname());

            discoveryTableData.add(discoveryDataBean.getType());

            //todo check when entry in discovery and credential already present -> after using discovery id not required

            if (tableDataService.insertDiscoveryTableRow(discoveryTableData))
            {
                discoveryDataBean.setOperationSuccess(true);

                if (discoveryDataBean.getType().equals("ssh"))
                {
                    int id = tableDataService.getDiscoveryRowId(discoveryDataBean.getIpHostname(), discoveryDataBean.getType());

                    if (id != -1)
                    {
                        discoveryDataBean.setId(id);
                    }                                                           //fixme what if id is -1

                    credentialsTableData = new ArrayList<>();

                    credentialsTableData.add(discoveryDataBean.getId());

                    credentialsTableData.add(discoveryDataBean.getUsername());

                    credentialsTableData.add(discoveryDataBean.getPassword());

                    discoveryDataBean.setOperationSuccess(tableDataService.insertCredentialTableRow(credentialsTableData));
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            discoveryDataBean.setOperationSuccess(false);
        }
        return "success";
    }

    public String editDiscoveryDevice()
    {
        List<Object> discoveryTableData = new ArrayList<>();

        List<Object> credentialsTableData;
        try
        {
            discoveryDataBean.setOperationSuccess(false);

            discoveryTableData.add(discoveryDataBean.getName());

            discoveryTableData.add(discoveryDataBean.getIpHostname());

            discoveryTableData.add(discoveryDataBean.getType());

            discoveryTableData.add(discoveryDataBean.getId());

            if (tableDataService.updateDiscoveryTableRow(discoveryTableData))
            {
                discoveryDataBean.setOperationSuccess(true);

                if (discoveryDataBean.getType().equals("ssh"))
                {
                    credentialsTableData = new ArrayList<>();

                    credentialsTableData.add(discoveryDataBean.getUsername());

                    credentialsTableData.add(discoveryDataBean.getPassword());

                    credentialsTableData.add(discoveryDataBean.getId());

                    discoveryDataBean.setOperationSuccess(tableDataService.updateCredentialTableRow(credentialsTableData));     //todo MUST need to be done on map_monitor_id
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            discoveryDataBean.setOperationSuccess(false);
        }
        return "success";
    }

    public String getDiscoveryData()
    {
        try
        {
            discoveryDataBean.setOperationSuccess(false);

            List<Object> discoveryRow = tableDataService.getTableRowUsingId("discovery", "id",  discoveryDataBean.getId());

            if (!discoveryRow.isEmpty())
            {
                discoveryDataBean.setName((String) discoveryRow.get(1));

                discoveryDataBean.setIpHostname((String) discoveryRow.get(2));

                discoveryDataBean.setType((String) discoveryRow.get(3));

                discoveryDataBean.setOperationSuccess(true);

                if (discoveryRow.get(3).equals("ssh"))
                {
                    List<Object> credentialRow = tableDataService.getTableRowUsingId("credential", "map_discovery_id", discoveryDataBean.getId());

                    if (!credentialRow.isEmpty())
                    {
                        discoveryDataBean.setUsername((String) credentialRow.get(2));

                        discoveryDataBean.setPassword((String) credentialRow.get(3));

                        discoveryDataBean.setOperationSuccess(true);
                    }
                    else
                    {
                        discoveryDataBean.setOperationSuccess(false);
                    }
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            discoveryDataBean.setOperationSuccess(false);
        }
        return "success";
    }

    @Override
    public DiscoveryDataBean getModel()
    {
        return discoveryDataBean;
    }
}
