package service;

import bean.DiscoveryBean;
import helper.CommonTableHelper;
import helper.CredentialTableHelper;
import helper.DiscoveryTableHelper;
import helper.MonitorTableHelper;

import java.util.HashMap;

public class DiscoveryService
{
    public static void insertDiscoveryCredentialTableRow(DiscoveryBean discoveryBean)
    {
        boolean operationSuccess = false;

        try
        {
            if (DiscoveryTableHelper.checkIpHostnameAndTypeExists(discoveryBean.getIpHostname(), discoveryBean.getType()))
            {
                discoveryBean.setDuplicateEntry(true);
            }
            else
            {
                int insertedRowNumber = DiscoveryTableHelper.insertDiscoveryTableRow(discoveryBean.getName(), discoveryBean.getIpHostname(), discoveryBean.getType());

                if (insertedRowNumber != 0)
                {
                    operationSuccess = true;

                    discoveryBean.setId(insertedRowNumber);

                    if (discoveryBean.getType().equals("ssh"))
                    {
                        operationSuccess = CredentialTableHelper.insertCredentialTableRow(discoveryBean.getId(), discoveryBean.getUsername(), discoveryBean.getPassword());
                    }
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            operationSuccess = false;
        }
        discoveryBean.setOperationSuccess(operationSuccess);
    }

    public static void editDiscoveryCredentialTableRow(DiscoveryBean discoveryBean)
    {
        boolean operationSuccess = false;

        try
        {
            if (DiscoveryTableHelper.checkIpHostnameAndTypeExistsExceptGivenId(discoveryBean.getIpHostname(), discoveryBean.getType(), discoveryBean.getId()))
            {
                discoveryBean.setDuplicateEntry(true);
            }
            else
            {
                if (DiscoveryTableHelper.updateDiscoveryTableRow(discoveryBean.getId(), discoveryBean.getName(), discoveryBean.getIpHostname(), discoveryBean.getType()))
                {
                    operationSuccess = true;

                    DiscoveryTableHelper.setProvision(discoveryBean.getId(), 0);

                    discoveryBean.setDiscoveryTableData(CommonTableHelper.getTableData("discovery"));

                    if (discoveryBean.getType().equals("ssh"))
                    {
                        operationSuccess = CredentialTableHelper.updateCredentialTableRow(discoveryBean.getId(), discoveryBean.getUsername(), discoveryBean.getPassword());
                    }
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            operationSuccess = false;
        }
        discoveryBean.setOperationSuccess(operationSuccess);
    }

    public static void getDiscoveryCredentialTableData(DiscoveryBean discoveryBean)
    {
        boolean operationSuccess = false;

        try
        {
            HashMap<String, Object> discoveryRow = CommonTableHelper.getTableRowUsingId("discovery", "id", discoveryBean.getId());

            if (discoveryRow != null && !discoveryRow.isEmpty())
            {
                discoveryBean.setName((String) discoveryRow.get("name"));

                discoveryBean.setIpHostname((String) discoveryRow.get("ip_hostname"));

                discoveryBean.setType((String) discoveryRow.get("type"));

                operationSuccess = true;

                if (discoveryRow.get("type").equals("ssh"))
                {
                    HashMap<String, Object> credentialRow = CommonTableHelper.getTableRowUsingId("credential", "map_discovery_id", discoveryBean.getId());

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
        discoveryBean.setOperationSuccess(operationSuccess);
    }

    public static void deleteDiscoveryTableRow(DiscoveryBean discoveryBean)
    {
        try
        {
            discoveryBean.setOperationSuccess(CommonTableHelper.deleteTableRowUsingId("discovery", "id", discoveryBean.getId()));
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
            discoveryBean.setDiscoveryTableData(CommonTableHelper.getTableData("discovery"));
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
//            HashMap<String, Object> discoveryTableData = CommonTableHelper.getTableRowUsingId("discovery", "id", discoveryBean.getId());
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
//                            HashMap<String, Object> credentialTableData = CommonTableHelper.getTableRowUsingId("credential", "map_discovery_id", discoveryBean.getId());
//
//                            if (credentialTableData != null)
//                            {
//                                List<String> commandList = new ArrayList<>();
//
//                                commandList.add("uname");
//
//                                commandList.add("exit");
//
//                                HashMap<String, String> responseMap = DiscoveryService.fireSSHCommands((String) credentialTableData.get("username"), (String) credentialTableData.get("password"), ipHostname, commandList);
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
//            if (provision && DiscoveryTableHelper.setProvision(discoveryBean.getId(), 1))
//            {
//                discoveryBean.setDiscoveryTableData(CommonTableHelper.getTableData("discovery"));
//            }
//        }
//    }

    public static void addMonitorDevice(DiscoveryBean discoveryBean)
    {
        boolean operationSuccess = false;

        try
        {
            HashMap<String, Object> discoveryTableData = CommonTableHelper.getTableRowUsingId("discovery", "id", discoveryBean.getId());

            if (discoveryTableData != null)
            {
                if (CommonTableHelper.checkIdExists("monitor", "map_discovery_id", discoveryBean.getId()))
                {
                    operationSuccess = MonitorTableHelper.updateMonitorTableRow(discoveryBean.getId(), discoveryTableData.get("name"), discoveryTableData.get("ip_hostname"));
                }
                else
                {
                    Object type = discoveryTableData.get("type");

                    if (type.equals("ssh"))
                    {
                        HashMap<String, Object> credentialTableData = CommonTableHelper.getTableRowUsingId("credential", "map_discovery_id", discoveryBean.getId());

                        if (credentialTableData != null)
                        {
                            operationSuccess = MonitorTableHelper.insertSshDeviceMonitorTableRow(credentialTableData.get("id"), discoveryBean.getId(), discoveryTableData.get("name"), discoveryTableData.get("ip_hostname"), type);
                        }
                    }
                    else
                    {
                        operationSuccess = MonitorTableHelper.insertPingDeviceMonitorTableRow(discoveryBean.getId(), discoveryTableData.get("name"), discoveryTableData.get("ip_hostname"), type);
                    }
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        discoveryBean.setOperationSuccess(operationSuccess);
    }
}
