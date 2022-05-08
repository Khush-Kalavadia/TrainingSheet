package service;

import bean.DiscoveryBean;
import dao.Query;
import helper.CommonTableHelper;
import helper.CredentialTableHelper;
import helper.DiscoveryTableHelper;
import helper.MonitorTableHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiscoveryService
{
    public static void getDiscoveryTableData(DiscoveryBean discoveryBean)
    {
        Query query = null;

        try
        {
            List<String> allColumn = new ArrayList<>();

            allColumn.add("*");

            query = new Query();

            query.getConnection();

            discoveryBean.setDiscoveryTableData(DiscoveryTableHelper.createDiscoveryTableHtml(query.commonSelect(allColumn, "discovery", null, null)));
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

    public static void getDiscoveryCredentialTableData(DiscoveryBean discoveryBean)
    {
        boolean operationSuccess = false;

        Query query = null;

        try
        {
            List<String> columnList = new ArrayList<>();

            columnList.add("*");

            HashMap<Query.Condition, String> conditionStringHashMap = new HashMap<>();

            conditionStringHashMap.put(Query.Condition.WHERE, "id = ?");

            List<Object> preparedStatementData = new ArrayList<>();

            preparedStatementData.add(discoveryBean.getId());

            query = new Query();

            query.getConnection();

            List<HashMap<String, Object>> resultSetList = query.commonSelect(columnList, "discovery", conditionStringHashMap, preparedStatementData);

            if (resultSetList != null && !resultSetList.isEmpty())
            {
//                HashMap<String, Object> discoveryRow = CommonTableHelper.getTableRowUsingId("discovery", "id", discoveryBean.getId());
                HashMap<String, Object> discoveryRow = resultSetList.get(0);

                if (discoveryRow != null && !discoveryRow.isEmpty())
                {
                    discoveryBean.setName((String) discoveryRow.get("name"));

                    discoveryBean.setIpHostname((String) discoveryRow.get("ip_hostname"));

                    discoveryBean.setType((String) discoveryRow.get("type"));

                    operationSuccess = true;

                    if (discoveryRow.get("type") != null && discoveryRow.get("type").equals("ssh"))
                    {
                        conditionStringHashMap.put(Query.Condition.WHERE, "map_discovery_id=?");

                        resultSetList = query.commonSelect(columnList, "credential", conditionStringHashMap, preparedStatementData);

                        if (resultSetList != null && !resultSetList.isEmpty())
                        {
                            HashMap<String, Object> credentialRow = resultSetList.get(0);

//                        HashMap<String, Object> credentialRow = CommonTableHelper.getTableRowUsingId("credential", "map_discovery_id", discoveryBean.getId());

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
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            operationSuccess = false;
        }
        finally
        {
            if (query != null)
            {
                query.releaseConnection();
            }
        }
        discoveryBean.setOperationSuccess(operationSuccess);
    }

    public static void insertDiscoveryCredentialTableRow(DiscoveryBean discoveryBean)
    {
        boolean operationSuccess = false;

        Query query = null;

        List<String> allColumn = new ArrayList<>();

        HashMap<Query.Condition, String> conditionStringHashMap = new HashMap<>();

        List<Object> preparedStatementData = new ArrayList<>();

        List<HashMap<String, Object>> resultSetList;

        try
        {
            allColumn.add("*");

            preparedStatementData.add(discoveryBean.getIpHostname());

            preparedStatementData.add(discoveryBean.getType());

            conditionStringHashMap.put(Query.Condition.WHERE, "ip_hostname = ? and type = ?");

            query = new Query();

            query.getConnection();

            resultSetList = query.commonSelect(allColumn, "discovery", conditionStringHashMap, preparedStatementData);

            if (resultSetList != null && !resultSetList.isEmpty())
            {
                discoveryBean.setDuplicateEntry(true);
            }
            else
            {
                int addedId = DiscoveryTableHelper.insertDiscoveryTableRow(query, discoveryBean.getName(), discoveryBean.getIpHostname(), discoveryBean.getType());

                if (addedId != 0)
                {
                    operationSuccess = true;

                    discoveryBean.setId(addedId);

                    resultSetList = query.commonSelect(allColumn, "discovery", null, null);

                    if (resultSetList != null && !resultSetList.isEmpty())
                    {
                        discoveryBean.setDiscoveryTableData(DiscoveryTableHelper.createDiscoveryTableHtml(resultSetList));

                        if (discoveryBean.getType().equals("ssh"))
                        {
                            operationSuccess = CredentialTableHelper.insertCredentialTableRow(query, discoveryBean.getId(), discoveryBean.getUsername(), discoveryBean.getPassword());
                        }
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
            if (query != null)
            {
                query.releaseConnection();
            }
        }
        discoveryBean.setOperationSuccess(operationSuccess);
    }

    public static void editDiscoveryCredentialTableRow(DiscoveryBean discoveryBean)
    {
        boolean operationSuccess = false;

        Query query = null;

        List<String> allColumn = new ArrayList<>();

        HashMap<Query.Condition, String> conditionStringHashMap = new HashMap<>();

        List<Object> preparedStatementData = new ArrayList<>();

        List<HashMap<String, Object>> resultList;

        try
        {
            allColumn.add("*");

            preparedStatementData.add(discoveryBean.getIpHostname());

            preparedStatementData.add(discoveryBean.getType());

            preparedStatementData.add(discoveryBean.getId());

            conditionStringHashMap.put(Query.Condition.WHERE, "ip_hostname = ? and type = ? and id != ?");      //check except the selected device

            query = new Query();

            query.getConnection();

            resultList = query.commonSelect(allColumn, "discovery", conditionStringHashMap, preparedStatementData);

            if (resultList != null && !resultList.isEmpty())
            {
                discoveryBean.setDuplicateEntry(true);
            }
            else
            {
                if (DiscoveryTableHelper.updateDiscoveryTableRow(query, discoveryBean.getId(), discoveryBean.getName(), discoveryBean.getIpHostname(), discoveryBean.getType()))
                {
                    operationSuccess = true;

                    DiscoveryTableHelper.setProvision(query, discoveryBean.getId(), 0);

                    discoveryBean.setDiscoveryTableData(DiscoveryTableHelper.createDiscoveryTableHtml(query.commonSelect(allColumn, "discovery", null, null)));

                    if (discoveryBean.getType().equals("ssh"))
                    {
                        operationSuccess = CredentialTableHelper.updateCredentialTableRow(query, discoveryBean.getId(), discoveryBean.getUsername(), discoveryBean.getPassword());
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
            if (query != null)
            {
                query.releaseConnection();
            }
        }
        discoveryBean.setOperationSuccess(operationSuccess);
    }

    public static void deleteDiscoveryTableRow(DiscoveryBean discoveryBean)
    {
        Query query = null;

        List<String> allColumn = new ArrayList<>();

        try
        {
            query = new Query();

            query.getConnection();

            if (CommonTableHelper.deleteTableRowUsingId(query, "discovery", "id", discoveryBean.getId()))
            {
                discoveryBean.setOperationSuccess(true);

                allColumn.add("*");

                discoveryBean.setDiscoveryTableData(DiscoveryTableHelper.createDiscoveryTableHtml(query.commonSelect(allColumn, "discovery", null, null)));
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

    public static void addMonitorDevice(DiscoveryBean discoveryBean)
    {
        boolean operationSuccess = false;

        Query query = null;

        List<String> allColumn = new ArrayList<>();

        HashMap<Query.Condition, String> conditionStringHashMap = new HashMap<>();

        List<Object> preparedStatementData = new ArrayList<>();

        List<HashMap<String, Object>> resultList;

        try
        {
            allColumn.add("*");

            preparedStatementData.add(discoveryBean.getId());

            conditionStringHashMap.put(Query.Condition.WHERE, "id=?");

            query = new Query();

            query.getConnection();

            resultList = query.commonSelect(allColumn, "discovery", conditionStringHashMap, preparedStatementData);

            if (resultList != null && !resultList.isEmpty())
            {
                HashMap<String, Object> discoveryTableData = resultList.get(0);

                if (discoveryTableData != null && !discoveryTableData.isEmpty())
                {
                    conditionStringHashMap.put(Query.Condition.WHERE, "map_discovery_id = ?");

                    resultList = query.commonSelect(allColumn, "monitor", conditionStringHashMap, preparedStatementData);

                    if (resultList != null && !resultList.isEmpty())
                    {
                        operationSuccess = MonitorTableHelper.updateMonitorTableRow(query, discoveryBean.getId(), discoveryTableData.get("name"), discoveryTableData.get("ip_hostname"));
                    }
                    else
                    {
                        Object type = discoveryTableData.get("type");

                        Object credentialTableId = null;

                        if (type != null && type.equals("ssh"))
                        {
                            conditionStringHashMap.put(Query.Condition.WHERE, "map_discovery_id=?");

                            List<String> columnList = new ArrayList<>();

                            columnList.add("id");

                            resultList = query.commonSelect(columnList, "credential", conditionStringHashMap, preparedStatementData);

                            if (resultList != null && !resultList.isEmpty() && resultList.get(0) != null)
                            {
                                credentialTableId = resultList.get(0).get("id");
                            }
                        }
                        operationSuccess = MonitorTableHelper.insertDeviceMonitorTableRow(query, credentialTableId, discoveryBean.getId(), discoveryTableData.get("name"), discoveryTableData.get("ip_hostname"), type);
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
        discoveryBean.setOperationSuccess(operationSuccess);
    }
}
