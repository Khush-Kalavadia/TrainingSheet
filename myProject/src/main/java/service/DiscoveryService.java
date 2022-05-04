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

//                if (DiscoveryTableHelper.insertDiscoveryTableRowBoolean(discoveryBean.getName(), discoveryBean.getIpHostname(), discoveryBean.getType()))
//                {
//                    operationSuccess = true;
//
//                    int id = CommonTableHelper.getMaxTableId("discovery");     //fixme get this from the index which is returned from the insert query itself. Basically change the type of insertDiscoveryTableRow from boolean to int and get the int recieved from execute query
//
//                    if (id != -1)
//                    {
//                        discoveryBean.setId(id);
//                    }
//                    else
//                    {
//                        operationSuccess = false;
//                    }
//
//                    if (discoveryBean.getType().equals("ssh"))
//                    {
//                        operationSuccess = CredentialTableHelper.insertCredentialTableRow(discoveryBean.getId(), discoveryBean.getUsername(), discoveryBean.getPassword());
//                    }
//                }

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
