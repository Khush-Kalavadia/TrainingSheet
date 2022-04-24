package service;

import bean.DiscoveryBean;
import commonutil.DiscoveryForkJoinPoolRunnable;
import dao.CommonTableDao;
import dao.DiscoveryDao;
import javafx.concurrent.Task;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class DiscoveryTask extends RecursiveAction
{
    private HashMap<String, Object> discoveryDeviceInfo;

    public DiscoveryTask(HashMap<String, Object> discoveryDeviceInfo)
    {
        this.discoveryDeviceInfo = discoveryDeviceInfo;
    }

    @Override
    protected void compute()
    {
        boolean operationSuccess = false;

        boolean provision = false;

        String response = "error";

        int id = (int) discoveryDeviceInfo.get("id");

        String ipHostname = null;

        String type = null;

        try
        {
            HashMap<String, Object> discoveryTableData = CommonTableDao.getTableRowUsingId("discovery", "id", id);

            if (discoveryTableData != null)
            {
                ipHostname = (String) discoveryTableData.get("ip_hostname");

                type = (String) discoveryTableData.get("type");

                discoveryDeviceInfo.put("type", type);

                discoveryDeviceInfo.put("ipHostname", ipHostname);

                PingTask pingTask = new PingTask(ipHostname);

                pingTask.fork();

                float packetLoss = pingTask.join();

                if (packetLoss != -1)
                {
                    operationSuccess = true;

                    if (packetLoss <= 50)
                    {
                        provision = true;

                        if (type.equals("ssh"))
                        {
                            HashMap<String, Object> credentialTableData = CommonTableDao.getTableRowUsingId("credential", "map_discovery_id", id);

                            if (credentialTableData != null)
                            {
                                List<String> commandList = new ArrayList<>();

                                commandList.add("uname");

                                commandList.add("exit");

                                SSHTask sshTask = new SSHTask((String) credentialTableData.get("username"), (String) credentialTableData.get("password"), ipHostname, commandList);

                                sshTask.fork();

                                HashMap<String, String> responseMap = sshTask.join();

                                if (responseMap != null)
                                {
                                    provision = responseMap.get("uname").contains("Linux");
                                }
                                else
                                {
                                    provision = false;

                                    operationSuccess = false;
                                }
                            }
                            else
                            {
                                operationSuccess = false;

                                provision = false;
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            operationSuccess = false;

            provision = false;
        }
        finally
        {
            if (operationSuccess)
            {
                if (provision)
                {
                    response = "success";

                    DiscoveryDao.setProvision(id, 1);       //fixme how to use the response
                }
                else
                {
                    response = "unsuccessful";
                }
            }
            try
            {
                ((Session) discoveryDeviceInfo.get("session")).getBasicRemote().sendText(ipHostname + ";" + type + ";" + response);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

}
