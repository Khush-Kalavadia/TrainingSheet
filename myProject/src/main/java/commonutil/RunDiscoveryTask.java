package commonutil;

import com.jcraft.jsch.HASH;
import com.sun.corba.se.spi.ior.ObjectKey;
import dao.Query;
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

        String ipHostname = null;

        String type = null;

        Query query = null;

        try
        {
            Object idObject = discoveryDeviceInfo.get("id");

            if (idObject != null)
            {
                int id = (int) idObject;

                List<String> allColumn = new ArrayList<>();

                HashMap<Query.Condition, String> conditionStringHashMap = new HashMap<>();

                List<Object> preparedStatementData = new ArrayList<>();

                List<HashMap<String, Object>> resultList;

                allColumn.add("*");

                conditionStringHashMap.put(Query.Condition.WHERE, "id=?");

                preparedStatementData.add(id);

                query = new Query();

                query.getConnection();

                resultList = query.commonSelect(allColumn, "discovery", conditionStringHashMap, preparedStatementData);

                if (resultList != null && !resultList.isEmpty())
                {
                    HashMap<String, Object> discoveryDeviceData = resultList.get(0);
//                HashMap<String, Object> discoveryDeviceData = CommonTableHelper.getTableRowUsingId("discovery", "id", id);

                    if (discoveryDeviceData != null && discoveryDeviceData.get("ip_hostname") != null && discoveryDeviceData.get("type") != null)
                    {
                        ipHostname = (String) discoveryDeviceData.get("ip_hostname");

                        type = (String) discoveryDeviceData.get("type");

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

                                    DiscoveryTableHelper.setProvision(query, id, 1);
                                }
                                else if (type.equals("ssh"))
                                {
                                    response = "unsuccessful";

                                    conditionStringHashMap.put(Query.Condition.WHERE, "map_discovery_id=?");

                                    resultList = query.commonSelect(allColumn, "credential", conditionStringHashMap, preparedStatementData);

                                    if (resultList != null && !resultList.isEmpty())
                                    {

                                        HashMap<String, Object> deviceCredentialData = resultList.get(0);
//                                    HashMap<String, Object> deviceCredentialData = CommonTableHelper.getTableRowUsingId("credential", "map_discovery_id", id);

                                        if (deviceCredentialData != null && !deviceCredentialData.isEmpty())
                                        {
                                            List<String> commandList = new ArrayList<>();

                                            commandList.add("uname");

                                            SSHDiscoveryDeviceTask sshDiscoveryDeviceTask = new SSHDiscoveryDeviceTask((String) deviceCredentialData.get("username"), (String) deviceCredentialData.get("password"), ipHostname, commandList);

                                            sshDiscoveryDeviceTask.fork();

                                            HashMap<String, String> responseMap = sshDiscoveryDeviceTask.join();

                                            if (responseMap != null)
                                            {
                                                if (responseMap.get("error") == null)
                                                {
                                                    if (responseMap.get("uname")!=null && responseMap.get("uname").contains("Linux"))
                                                    {
                                                        response = "success";

                                                        DiscoveryTableHelper.setProvision(query, id, 1);
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
                            }
                            else
                            {
                                response = "unsuccessful";
                            }
                        }
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
            try
            {
                synchronized (RunDiscoveryTask.class)
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
