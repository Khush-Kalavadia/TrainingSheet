package commonutil;

import bean.DiscoveryBean;
import dao.Query;
import helper.CommonTableHelper;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddPollingDeviceJob implements Job
{
    @Override
    public void execute(JobExecutionContext jobExecutionContext)
    {
        Query query = null;

        List<String> allColumn = new ArrayList<>();

        try
        {
            allColumn.add("*");

            query = new Query();

            query.getConnection();

            List<HashMap<String, Object>> monitorTableData = query.commonSelect(allColumn, "monitor", null, null);

            List<HashMap<String, Object>> credentialTableData = query.commonSelect(allColumn, "credential", null, null);

            if (monitorTableData != null)
            {
                for (HashMap<String, Object> monitorTableRow : monitorTableData)
                {
                    try
                    {
                        DiscoveryBean deviceDetail = new DiscoveryBean();

                        if (monitorTableRow.get("id") != null && monitorTableRow.get("type") != null && monitorTableRow.get("ip_hostname") != null)
                        {
                            deviceDetail.setId((int) monitorTableRow.get("id"));

                            deviceDetail.setType((String) monitorTableRow.get("type"));

                            deviceDetail.setIpHostname((String) monitorTableRow.get("ip_hostname"));

                            if (monitorTableRow.get("type").equals("ssh"))
                            {
                                for (HashMap<String, Object> credentialTableRow : credentialTableData)
                                {
                                    if (credentialTableRow.get("id") == monitorTableRow.get("map_credential_id"))
                                    {
                                        deviceDetail.setUsername((String) credentialTableRow.get("username"));

                                        deviceDetail.setPassword((String) credentialTableRow.get("password"));

                                        break;
                                    }
                                }
                            }
                        }
                        DataBlockingQueue.getMonitorPollingQueue().put(deviceDetail);
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (query != null)
            {
                query.releaseConnection();
            }
        }
    }
}