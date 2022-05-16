package service;

import bean.MonitorBean;
import dao.Query;
import helper.CommonTableHelper;
import helper.CredentialTableHelper;
import helper.MonitorTableHelper;
import helper.NetworkingCommandHelper;
import sun.text.resources.cldr.bn.FormatData_bn_IN;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MonitorService
{
    public static void getMonitorTableData(MonitorBean monitorBean)
    {
        Query query = null;

        try
        {
            List<String> columnList = new ArrayList<>();

            columnList.add("*");

            query = new Query();

            query.getConnection();

            monitorBean.setMonitorTableData(MonitorTableHelper.createMonitorHtmlTable(query.commonSelect(columnList, "monitor", null, null)));
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

    public static void getMonitorDetail(MonitorBean monitorBean)
    {
        Query query = new Query();

        List<Object> preparedStatementList = new ArrayList<>();

        HashMap<Query.Condition, String> conditionStringHashMap = new HashMap<>();

        List<String> allColumnList = new ArrayList<>();

        List<HashMap<String, Object>> resultSetList;

        try
        {
            allColumnList.add("*");

            query.getConnection();

            monitorBean.setMonitorTableData(MonitorTableHelper.createMonitorHtmlTable(query.commonSelect(allColumnList, "monitor", null, null)));

            conditionStringHashMap.put(Query.Condition.WHERE, "id=?");

            preparedStatementList.add(monitorBean.getId());

            resultSetList = query.commonSelect(allColumnList, "monitor", conditionStringHashMap, preparedStatementList);

            if (resultSetList != null && !resultSetList.isEmpty())
            {
                HashMap<String, Object> monitorDeviceData = resultSetList.get(0);

                if (monitorDeviceData != null && !monitorDeviceData.isEmpty())
                {
                    monitorBean.setIpHostname((String) monitorDeviceData.get("ip_hostname"));

                    monitorBean.setType((String) monitorDeviceData.get("type"));

                    monitorBean.setAvailability((String) monitorDeviceData.get("availability_status"));

                    //ssh device polling details

                    if (monitorDeviceData.get("type") != null && monitorDeviceData.get("type").equals("ssh"))
                    {
                        conditionStringHashMap.put(Query.Condition.WHERE, "map_monitor_id = ? and idle_cpu_percent is not null and total_memory_gb is not null and used_memory_gb is not null and total_disk_gb is not null and used_disk_gb is not null and uptime is not null");

                        conditionStringHashMap.put(Query.Condition.ORDER_BY, "time desc");

                        conditionStringHashMap.put(Query.Condition.LIMIT, "1");

                        resultSetList = query.commonSelect(allColumnList, "ssh_polling", conditionStringHashMap, preparedStatementList);

                        if (resultSetList != null && !resultSetList.isEmpty())
                        {
                            HashMap<String, Object> sshDevicePollingData = resultSetList.get(0);

                            if (sshDevicePollingData != null && !sshDevicePollingData.isEmpty())
                            {
                                monitorBean.setUptime((String) sshDevicePollingData.get("uptime"));

                                monitorBean.setLastNotNullSshTime(sshDevicePollingData.get("time").toString());

                                if (sshDevicePollingData.get("total_memory_gb") != null)
                                {
                                    monitorBean.setTotalMemoryGb((float) sshDevicePollingData.get("total_memory_gb"));
                                }
                                else
                                {
                                    monitorBean.setTotalMemoryGb(-1);
                                }

                                if (sshDevicePollingData.get("total_disk_gb") != null)
                                {
                                    monitorBean.setTotalDiskGb((float) sshDevicePollingData.get("total_disk_gb"));
                                }
                                else
                                {
                                    monitorBean.setTotalDiskGb(-1);
                                }

                                if (sshDevicePollingData.get("used_memory_gb") != null)
                                {
                                    monitorBean.setUsedMemoryGb((float) sshDevicePollingData.get("used_memory_gb"));
                                }
                                else
                                {
                                    monitorBean.setUsedMemoryGb(-1);
                                }

                                if (sshDevicePollingData.get("used_disk_gb") != null)
                                {
                                    monitorBean.setUsedDiskGb((float) sshDevicePollingData.get("used_disk_gb"));
                                }
                                else
                                {
                                    monitorBean.setUsedDiskGb(-1);
                                }

                                if (sshDevicePollingData.get("idle_cpu_percent") != null)
                                {
                                    monitorBean.setIdleCpuPercentage((float) sshDevicePollingData.get("idle_cpu_percent"));
                                }
                                else
                                {
                                    monitorBean.setIdleCpuPercentage(-1);
                                }
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

        //ping device polling data

        List<String> columnList = new ArrayList<>();
        try
        {
            if (preparedStatementList.size() == 1)
            {
                preparedStatementList.add(monitorBean.getId());
            }

            conditionStringHashMap.put(Query.Condition.WHERE, "map_monitor_id = ? AND time = (SELECT  MAX(time) FROM ping_polling WHERE map_monitor_id = ?)");

            resultSetList = query.commonSelect(allColumnList, "ping_polling", conditionStringHashMap, preparedStatementList);

            if (resultSetList != null && !resultSetList.isEmpty())
            {
                HashMap<String, Object> pingDevicePollingData = resultSetList.get(0);

                if (pingDevicePollingData != null && !pingDevicePollingData.isEmpty())
                {
                    if (pingDevicePollingData.get("packet_loss") != null)
                    {
                        monitorBean.setPacketLoss(String.valueOf((float) pingDevicePollingData.get("packet_loss")));
                    }

                    if (pingDevicePollingData.get("avg_rtt") != null)
                    {
                        monitorBean.setRttAvg(String.valueOf((float) pingDevicePollingData.get("avg_rtt")));
                    }

                    if (pingDevicePollingData.get("packet_transmitted") != null)
                    {
                        monitorBean.setPacketsTransmitted(String.valueOf((int) pingDevicePollingData.get("packet_transmitted")));
                    }

                    if (pingDevicePollingData.get("packet_received") != null)
                    {
                        monitorBean.setPacketsReceived(String.valueOf((int) pingDevicePollingData.get("packet_received")));
                    }
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        // 24 hr availability

        try
        {
            columnList.add("AVG(availability) as past_availability_percent");

            preparedStatementList.clear();

            preparedStatementList.add(monitorBean.getId());

            preparedStatementList.add(new Timestamp(new Date().getTime() - 24 * 3600 * 1000));

            preparedStatementList.add(new Timestamp(new Date().getTime()));

            conditionStringHashMap.put(Query.Condition.WHERE, "map_monitor_id = ? and time BETWEEN ? AND ?");

            resultSetList = query.commonSelect(columnList, "ping_polling", conditionStringHashMap, preparedStatementList);

            if (resultSetList != null && !resultSetList.isEmpty() && resultSetList.get(0) != null && resultSetList.get(0).get("past_availability_percent") != null)
            {
                monitorBean.setPastAvailabilityPercent(((BigDecimal) resultSetList.get(0).get("past_availability_percent")).floatValue() * (float) 100.0);
            }
            else
            {
                monitorBean.setPastAvailabilityPercent(-1);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        //packet loss chart data

        try
        {
            columnList.clear();

            columnList.add("time");

            columnList.add("packet_loss");

            preparedStatementList.clear();

            preparedStatementList.add(monitorBean.getId());

            conditionStringHashMap.put(Query.Condition.WHERE, "map_monitor_id = ?");

            conditionStringHashMap.put(Query.Condition.ORDER_BY, "time DESC");

            conditionStringHashMap.put(Query.Condition.LIMIT, "10");

            resultSetList = query.commonSelect(columnList, "ping_polling", conditionStringHashMap, preparedStatementList);

            if (resultSetList != null && !resultSetList.isEmpty())
            {
                int resultListSize = resultSetList.size();

                Object[] packetLossData = new Object[resultListSize];

                Object[] timeData = new Object[resultListSize];

                int rowCount = 1;

                for (HashMap<String, Object> resultRow : resultSetList)
                {
                    timeData[resultListSize - rowCount] = resultRow.get("time");

                    packetLossData[resultListSize - rowCount] = resultRow.get("packet_loss");

                    rowCount++;
                }

                int arrayCount = 0;

                String[] timeStringArray = new String[resultListSize];

                for (Object timeObject : timeData)
                {
                    timeStringArray[arrayCount++] = timeObject.toString();
                }

                arrayCount = 0;

                float[] packetLossArray = new float[resultListSize];

                for (Object packetLoss : packetLossData)
                {
                    if (packetLoss != null)
                    {
                        packetLossArray[arrayCount++] = (float) packetLoss;
                    }
                }

                monitorBean.setPacketLossChartData(packetLossArray);

                monitorBean.setTimeChartData(timeStringArray);
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

    public static void deleteMonitorTableRow(MonitorBean monitorBean)
    {
        Query query = null;

        List<String> allColumn = new ArrayList<>();

        try
        {
            query = new Query();

            query.getConnection();

            if (CommonTableHelper.deleteTableRowUsingId(query, "monitor", "id", monitorBean.getId()))
            {
                monitorBean.setOperationSuccess(true);

                allColumn.add("*");

                monitorBean.setMonitorTableData(MonitorTableHelper.createMonitorHtmlTable(query.commonSelect(allColumn, "monitor", null, null)));
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

    public static void getMonitorCredentialTableData(MonitorBean monitorBean)
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

            preparedStatementData.add(monitorBean.getId());

            query = new Query();

            query.getConnection();

            List<HashMap<String, Object>> resultSetList = query.commonSelect(columnList, "monitor", conditionStringHashMap, preparedStatementData);

            if (resultSetList != null && !resultSetList.isEmpty())
            {
                HashMap<String, Object> monitorRow = resultSetList.get(0);

                if (monitorRow != null && !monitorRow.isEmpty())
                {
                    Object type = monitorRow.get("type");

                    monitorBean.setName((String) monitorRow.get("name"));

                    monitorBean.setIpHostname((String) monitorRow.get("ip_hostname"));

                    monitorBean.setType((String) type);

                    operationSuccess = true;

                    if (type != null && type.equals("ssh") && monitorRow.get("map_credential_id") != null)
                    {
                        preparedStatementData.clear();

                        preparedStatementData.add(monitorRow.get("map_credential_id"));

                        resultSetList = query.commonSelect(columnList, "credential", conditionStringHashMap, preparedStatementData);

                        if (resultSetList != null && !resultSetList.isEmpty())
                        {
                            HashMap<String, Object> credentialRow = resultSetList.get(0);

                            if (credentialRow != null && !credentialRow.isEmpty())
                            {
                                monitorBean.setPassword((String) credentialRow.get("password"));

                                monitorBean.setUsername((String) credentialRow.get("username"));

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
        monitorBean.setOperationSuccess(operationSuccess);
    }

    public static void editMonitorCredentialTableRow(MonitorBean monitorBean)
    {
        boolean operationSuccess = false;

        Query query = null;

        List<HashMap<String, Object>> resultList;

        String availability = "down";

        try
        {
            String trimmedName = monitorBean.getName().trim();

            String trimmedIpHostname = monitorBean.getIpHostname().trim();

            String trimmedUsername = monitorBean.getUsername().trim();

            String trimmedPassword = monitorBean.getPassword().trim();

            String type = monitorBean.getType();

            if ((!trimmedName.isEmpty() && !trimmedIpHostname.isEmpty()) && (type.equals("ping") || (type.equals("ssh") && !trimmedUsername.isEmpty() && !trimmedPassword.trim().isEmpty())))
            {
                HashMap<String, Object> pingOutput = NetworkingCommandHelper.getParsedPingDeviceDetail(trimmedIpHostname);

                if (pingOutput != null && pingOutput.get("packetLoss") != null && (float) pingOutput.get("packetLoss") <= 50)
                {
                    if (type.equals("ping"))
                    {
                        availability = "up";
                    }
                    else if (type.equals("ssh"))
                    {
                        List<String> commandList = new ArrayList<>();

                        commandList.add("uname");

                        HashMap<String, String> sshOutput = NetworkingCommandHelper.fireSSHCommands(trimmedUsername, trimmedPassword, trimmedIpHostname, commandList);

                        if (sshOutput != null && !sshOutput.isEmpty() && sshOutput.get("error") == null && sshOutput.get("uname") != null && sshOutput.get("uname").equals("Linux"))
                        {
                            availability = "up";
                        }
                    }
                }

                monitorBean.setAvailability(availability);

                if (availability.equals("up"))
                {
                    List<String> allColumn = new ArrayList<>();

                    HashMap<Query.Condition, String> conditionStringHashMap = new HashMap<>();

                    List<Object> preparedStatementData = new ArrayList<>();

                    allColumn.add("*");

                    preparedStatementData.add(monitorBean.getIpHostname());

                    preparedStatementData.add(type);

                    preparedStatementData.add(monitorBean.getId());

                    conditionStringHashMap.put(Query.Condition.WHERE, "ip_hostname = ? and type = ? and id != ?");      //check except the selected device

                    query = new Query();

                    query.getConnection();

                    resultList = query.commonSelect(allColumn, "monitor", conditionStringHashMap, preparedStatementData);

                    if (resultList != null && !resultList.isEmpty())
                    {
                        monitorBean.setDuplicateEntry(true);
                    }
                    else
                    {
                        if (MonitorTableHelper.updateMonitorTableRow(query, monitorBean.getId(), monitorBean.getName(), monitorBean.getIpHostname()))
                        {
                            monitorBean.setMonitorTableData(MonitorTableHelper.createMonitorHtmlTable(query.commonSelect(allColumn, "monitor", null, null)));

                            operationSuccess = true;

                            if (type.equals("ssh"))
                            {
                                List<String> columnList = new ArrayList<>();

                                columnList.add("map_credential_id");

                                conditionStringHashMap.put(Query.Condition.WHERE, "id=?");

                                preparedStatementData.clear();

                                preparedStatementData.add(monitorBean.getId());

                                resultList = query.commonSelect(columnList, "monitor", conditionStringHashMap, preparedStatementData);

                                operationSuccess = resultList != null && !resultList.isEmpty() && resultList.get(0) != null && resultList.get(0).get("map_credential_id") != null && CredentialTableHelper.updateCredentialTableRow(query, resultList.get(0).get("map_credential_id"), monitorBean.getUsername(), monitorBean.getPassword());
                            }
                        }
                    }
                }
            }
            else
            {
                monitorBean.setEmptyInputEntry(true);
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
        monitorBean.setOperationSuccess(operationSuccess);
    }
}
