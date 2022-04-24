package service;

import bean.DiscoveryBean;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import dao.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DiscoveryService
{
    public static void insertDiscoveryCredentialTableRow(DiscoveryBean discoveryBean)
    {
        boolean operationSuccess = false;

        try
        {
            if (DiscoveryDao.insertDiscoveryTableRow(discoveryBean.getName(), discoveryBean.getIpHostname(), discoveryBean.getType()))
            {
                operationSuccess = true;

                int id = CommonTableDao.getMaxTableId("discovery");

                if (id != -1)
                {
                    discoveryBean.setId(id);
                }
                else
                {
                    operationSuccess = false;
                }

                if (discoveryBean.getType().equals("ssh"))
                {
                    operationSuccess = CredentialDao.insertCredentialTableRow(discoveryBean.getId(), discoveryBean.getUsername(), discoveryBean.getPassword());
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            operationSuccess = false;
        }
        finally
        {
            discoveryBean.setOperationSuccess(operationSuccess);
        }
    }

    public static void editDiscoveryCredentialTableRow(DiscoveryBean discoveryBean)
    {
        boolean operationSuccess = false;

        try
        {
            if (DiscoveryDao.updateDiscoveryTableRow(discoveryBean.getId(), discoveryBean.getName(), discoveryBean.getIpHostname(), discoveryBean.getType()))
            {
                operationSuccess = true;

                discoveryBean.setDiscoveryTableData(CommonTableDao.getTableData("discovery"));

                if (discoveryBean.getType().equals("ssh"))
                {
                   operationSuccess = CredentialDao.updateCredentialTableRow(discoveryBean.getId(), discoveryBean.getUsername(), discoveryBean.getPassword());
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            operationSuccess = false;
        }
        finally
        {
            discoveryBean.setOperationSuccess(operationSuccess);
        }
    }

    public static void getDiscoveryCredentialTableData(DiscoveryBean discoveryBean)
    {
        boolean operationSuccess = false;

        try
        {
            HashMap<String, Object> discoveryRow = CommonTableDao.getTableRowUsingId("discovery", "id", discoveryBean.getId());

            if (discoveryRow != null && !discoveryRow.isEmpty())
            {
                discoveryBean.setName((String) discoveryRow.get("name"));

                discoveryBean.setIpHostname((String) discoveryRow.get("ip_hostname"));

                discoveryBean.setType((String) discoveryRow.get("type"));

                operationSuccess = true;

                if (discoveryRow.get("type").equals("ssh"))
                {
                    HashMap<String, Object> credentialRow = CommonTableDao.getTableRowUsingId("credential", "map_discovery_id", discoveryBean.getId());

                    if (credentialRow != null && !credentialRow.isEmpty())
                    {
                        discoveryBean.setUsername((String) credentialRow.get("username"));

                        discoveryBean.setPassword((String) credentialRow.get("password"));

                        operationSuccess = true;
                    }
                    else
                    {
                        operationSuccess = false;
                    }
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            operationSuccess = false;
        }
        finally
        {
            discoveryBean.setOperationSuccess(operationSuccess);
        }
    }

    public static void deleteDiscoveryTableRow(DiscoveryBean discoveryBean)
    {
        try
        {
            discoveryBean.setOperationSuccess(CommonTableDao.deleteTableRowUsingId("discovery", "id", discoveryBean.getId()));      //todo record not deleted form credential as it might be in monitors. so will have to check later if it is not even present in the monitor
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void getDiscoveryTableData(DiscoveryBean discoveryBean)
    {
        try
        {
            discoveryBean.setDiscoveryTableData(CommonTableDao.getTableData("discovery"));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


//    public static void discoverDevice(DiscoveryBean discoveryBean)
//    {
//        boolean operationSuccess = false;
//
//        boolean provision = false;
//
//        try
//        {
//            HashMap<String, Object> discoveryTableData = CommonTableDao.getTableRowUsingId("discovery", "id", discoveryBean.getId());
//
//            if (discoveryTableData != null)
//            {
//                String ipHostname = (String) discoveryTableData.get("ip_hostname");
//
//                String type = (String) discoveryTableData.get("type");
//
//                discoveryBean.setType(type);
//
//                discoveryBean.setIpHostname(ipHostname);
//
//                float packetLoss = DiscoveryService.getPingPacketLoss(ipHostname);
//
//                if (packetLoss != -1)
//                {
//                    operationSuccess = true;
//
//                    if (packetLoss <= 50)
//                    {
//                        provision = true;
//
//                        if (type.equals("ssh"))
//                        {
//                            HashMap<String, Object> credentialTableData = CommonTableDao.getTableRowUsingId("credential", "map_discovery_id", discoveryBean.getId());
//
//                            if (credentialTableData != null)
//                            {
//                                List<String> commandList = new ArrayList<>();
//
//                                commandList.add("uname");
//
//                                commandList.add("exit");
//
//                                HashMap<String, String> responseMap = DiscoveryService.fireGenericSSHCommand((String) credentialTableData.get("username"), (String) credentialTableData.get("password"), ipHostname, commandList);
//
//                                if (responseMap != null)
//                                {
//                                    provision = responseMap.get("uname").contains("Linux");
//                                }
//                                else
//                                {
//                                    provision = false;
//
//                                    operationSuccess = false;
//                                }
//                            }
//                            else
//                            {
//                                operationSuccess = false;
//
//                                provision = false;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//
//            operationSuccess = false;
//
//            provision = false;
//        }
//        finally
//        {
//            //error thrown from following methods are handled so no need of try catch over here
//            discoveryBean.setOperationSuccess(operationSuccess);
//
//            discoveryBean.setProvision(provision);
//
//            if (provision && DiscoveryDao.setProvision(discoveryBean.getId(), 1))
//            {
//                discoveryBean.setDiscoveryTableData(CommonTableDao.getTableData("discovery"));
//            }
//        }
//    }

    static float getPingPacketLoss(String ipHostname)
    {
        float packetLoss = -1;

        try
        {
            ArrayList<String> commandList = new ArrayList<>();

            commandList.add("ping");

            commandList.add("-c 3");

            commandList.add("-w 3");

            commandList.add(ipHostname);

            ProcessBuilder build = new ProcessBuilder(commandList);

            Process process = build.start();

            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String pingOutputLine;

            while ((pingOutputLine = input.readLine()) != null)
            {
                if (pingOutputLine.contains("% packet loss"))
                {
                    String[] pingOutputArray = pingOutputLine.split(", ");

                    for (String pingOutputSubString : pingOutputArray)
                    {
                        if (pingOutputSubString.contains("% packet loss"))
                        {
                            packetLoss = Float.parseFloat(pingOutputSubString.substring(0, pingOutputSubString.indexOf("% packet loss")));
                        }
                    }
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("Packet Loss: " + packetLoss);

        return packetLoss;
    }

    static HashMap<String, String> fireGenericSSHCommand(String username, String password, String host, List<String> commandList)
    {
        int port = 22;

        Session session = null;

        Channel channel = null;

        BufferedWriter commandWriter = null;

        BufferedReader responseReader = null;

        HashMap<String, String> responseMap = null;

        try
        {
            session = new JSch().getSession(username, host, port);

            session.setPassword(password);

            session.setConfig("StrictHostKeyChecking", "no");       //accepts SSH host keys from remote servers and those not in the known host’s list.

            session.connect(10 * 1000);

            channel = session.openChannel("shell");

            channel.connect(10 * 1000);

            if (channel.isConnected() && session.isConnected())
            {
                commandWriter = new BufferedWriter(new OutputStreamWriter(channel.getOutputStream()));

                responseReader = new BufferedReader(new InputStreamReader(channel.getInputStream()));

                StringBuilder commandConcat = new StringBuilder();

                for (String command : commandList)
                {
                    commandConcat.append(command).append("\n");
                }

                commandWriter.write(commandConcat.toString());

                commandWriter.flush();

                String response;

                int commandIndex = 0;

                StringBuilder commandString;

                responseMap = new HashMap<>();

                do
                {
                    response = responseReader.readLine();
                }
                while (!response.contains(":~$ " + commandList.get(commandIndex)));

                commandIndex++;

                do
                {
                    commandString = new StringBuilder();

                    response = responseReader.readLine();

                    while (!response.contains(":~$ " + commandList.get(commandIndex)))
                    {
                        commandString.append(response.concat("\n"));

                        response = responseReader.readLine();
                    }

                    responseMap.put(commandList.get(commandIndex - 1), commandString.toString());

                    commandIndex++;
                }
                while (commandIndex != commandList.size());
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (responseReader != null)
            {
                try
                {
                    responseReader.close();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            if (commandWriter != null)
            {
                try
                {
                    commandWriter.close();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            if (channel != null)
            {
                channel.disconnect();
            }
            if (session != null)
            {
                session.disconnect();
            }
        }
        return responseMap;
    }

    public static void addMonitorDevice(DiscoveryBean discoveryBean)
    {
        boolean operationSuccess = false;

        try
        {
            HashMap<String, Object> discoveryTableData = CommonTableDao.getTableRowUsingId("discovery", "id", discoveryBean.getId());

            if (discoveryTableData != null)
            {
                if (CommonTableDao.checkIdExists("monitor", "map_discovery_id", discoveryBean.getId()))
                {
                    operationSuccess = MonitorDao.updateDeviceMonitorTableRow(discoveryBean.getId(), discoveryTableData.get("name"), discoveryTableData.get("ip_hostname"));
                }
                else
                {
                    Object type = discoveryTableData.get("type");

                    if (type.equals("ssh"))
                    {
                        HashMap<String, Object> credentialTableData = CommonTableDao.getTableRowUsingId("credential", "map_discovery_id", discoveryBean.getId());

                        if (credentialTableData != null)
                        {
                            operationSuccess = MonitorDao.insertSshDeviceMonitorTableRow(credentialTableData.get("id"), discoveryBean.getId(), discoveryTableData.get("name"), discoveryTableData.get("ip_hostname"), type);
                        }
                    }
                    else
                    {
                        operationSuccess = MonitorDao.insertPingDeviceMonitorTableRow(discoveryBean.getId(), discoveryTableData.get("name"), discoveryTableData.get("ip_hostname"), type);
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
            discoveryBean.setOperationSuccess(operationSuccess);
        }
    }

}
