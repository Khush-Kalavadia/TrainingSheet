package service;

import bean.DiscoveryBean;
import dao.*;

import java.util.HashMap;

public class DiscoveryService
{
    public static void insertDiscoveryCredentialTableRow(DiscoveryBean discoveryBean)
    {
        boolean operationSuccess = false;

        try
        {
            if (DiscoveryDao.checkIpHostnameAndTypeExists(discoveryBean.getIpHostname(), discoveryBean.getType()))
            {
                discoveryBean.setDuplicateEntry(true);
            }
            else
            {
                if (DiscoveryDao.insertDiscoveryTableRow(discoveryBean.getName(), discoveryBean.getIpHostname(), discoveryBean.getType()))
                {
                    operationSuccess = true;

                    int id = CommonTableDao.getMaxTableId("discovery");     //fixme get this from the index which is returned from the insert query itself. Basically change the type of insertDiscoveryTableRow from boolean to int and get the int recieved from execute query

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
            if (DiscoveryDao.checkIpHostnameAndTypeExistsExceptGivenId(discoveryBean.getIpHostname(), discoveryBean.getType(), discoveryBean.getId()))
            {
                discoveryBean.setDuplicateEntry(true);
            }
            else
            {
                if (DiscoveryDao.updateDiscoveryTableRow(discoveryBean.getId(), discoveryBean.getName(), discoveryBean.getIpHostname(), discoveryBean.getType()))
                {
                    operationSuccess = true;

                    DiscoveryDao.setProvision(discoveryBean.getId(), 0);

                    discoveryBean.setDiscoveryTableData(CommonTableDao.getTableData("discovery"));

                    if (discoveryBean.getType().equals("ssh"))
                    {
                        operationSuccess = CredentialDao.updateCredentialTableRow(discoveryBean.getId(), discoveryBean.getUsername(), discoveryBean.getPassword());
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
        discoveryBean.setOperationSuccess(operationSuccess);
    }

    public static void deleteDiscoveryTableRow(DiscoveryBean discoveryBean)
    {
        try
        {
            discoveryBean.setOperationSuccess(CommonTableDao.deleteTableRowUsingId("discovery", "id", discoveryBean.getId()));      //todo record not deleted from credential as it might be in monitors. so will have to check later if it is not even present in the monitor
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
//            if (provision && DiscoveryDao.setProvision(discoveryBean.getId(), 1))
//            {
//                discoveryBean.setDiscoveryTableData(CommonTableDao.getTableData("discovery"));
//            }
//        }
//    }

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
                    operationSuccess = MonitorDao.updateMonitorTableRow(discoveryBean.getId(), discoveryTableData.get("name"), discoveryTableData.get("ip_hostname"));
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
        discoveryBean.setOperationSuccess(operationSuccess);
    }

}
