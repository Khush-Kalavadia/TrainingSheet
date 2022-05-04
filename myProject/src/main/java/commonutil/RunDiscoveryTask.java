package commonutil;

import helper.CommonTableHelper;
import helper.DiscoveryTableHelper;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class RunDiscoveryTask extends RecursiveAction
{
    private HashMap<String, Object> discoveryDeviceInfo;

    RunDiscoveryTask(HashMap<String, Object> discoveryDeviceInfo)
    {
        this.discoveryDeviceInfo = discoveryDeviceInfo;
    }

    @Override
    protected void compute()
    {
        String response = "error";

        int id = (int) discoveryDeviceInfo.get("id");

        String ipHostname = null;

        String type = null;

        try
        {
            HashMap<String, Object> discoveryTableData = CommonTableHelper.getTableRowUsingId("discovery", "id", id);

            if (discoveryTableData != null)
            {
                ipHostname = (String) discoveryTableData.get("ip_hostname");

                type = (String) discoveryTableData.get("type");

                discoveryDeviceInfo.put("type", type);

                discoveryDeviceInfo.put("ipHostname", ipHostname);

                PingDeviceTask pingDeviceTask = new PingDeviceTask(ipHostname);

                pingDeviceTask.fork();

                HashMap<String, Object> pingOutput = pingDeviceTask.join();

                if (pingOutput != null && pingOutput.get("packetLoss") != null)
                {
                    float packetLoss = (float) pingOutput.get("packetLoss");

                    if (packetLoss <= 50)
                    {
                        if (type.equals("ping"))
                        {
                            response = "success";

                            DiscoveryTableHelper.setProvision(id, 1);
                        }
                        else if (type.equals("ssh"))
                        {
                            response = "unsuccessful";

                            HashMap<String, Object> credentialTableData = CommonTableHelper.getTableRowUsingId("credential", "map_discovery_id", id);

                            if (credentialTableData != null)
                            {
                                List<String> commandList = new ArrayList<>();

                                commandList.add("uname");

                                SSHDiscoveryDeviceTask sshDiscoveryDeviceTask = new SSHDiscoveryDeviceTask((String) credentialTableData.get("username"), (String) credentialTableData.get("password"), ipHostname, commandList);

                                sshDiscoveryDeviceTask.fork();

                                HashMap<String, String> responseMap = sshDiscoveryDeviceTask.join();

                                if (responseMap != null)
                                {
                                    if (responseMap.get("error") == null)
                                    {
                                        if (responseMap.get("uname").contains("Linux"))
                                        {
                                            response = "success";

                                            DiscoveryTableHelper.setProvision(id, 1);
                                        }
                                        else
                                        {
                                            response = "notLinuxDevice";
                                        }
                                    }
                                    else
                                    {
                                        response = responseMap.get("error");
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        response = "unsuccessful";
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
            try
            {
                synchronized (RunDiscoveryTask.class)               //testme is it the right way to send the data to frontend
                {
                    ((Session) discoveryDeviceInfo.get("session")).getBasicRemote().sendText(ipHostname + ";" + type + ";" + response);
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
