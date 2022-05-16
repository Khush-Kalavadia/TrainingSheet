package helper;

import dao.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MonitorTableHelper
{
    public static boolean insertDeviceMonitorTableRow(Query query, Object map_credential_id, Object map_discovery_id, Object name, Object ip_hostname, Object type)
    {
        boolean insertionStatus = false;

        List<Object> preparedStatementData = new ArrayList<>();

        try
        {
            preparedStatementData.add(map_credential_id);

            preparedStatementData.add(map_discovery_id);

            preparedStatementData.add(name);

            preparedStatementData.add(ip_hostname);

            preparedStatementData.add(type);

            String sql = "INSERT INTO monitor(map_credential_id, map_discovery_id, name, ip_hostname, type) VALUES (?, ?, ?, ?, ?)";

            if (query.executeUpdate(sql, preparedStatementData) != 0)
            {
                insertionStatus = true;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return insertionStatus;
    }

    public static boolean updateMonitorTableRow(Query query, Object id, Object name, Object ip_hostname)
    {
        boolean updateStatus = false;

        List<Object> preparedStatementData = new ArrayList<>();

        try
        {
            preparedStatementData.add(name);

            preparedStatementData.add(ip_hostname);

            preparedStatementData.add(id);

            String sql = "UPDATE monitor SET name = ?, ip_hostname = ? WHERE id = ?";

            if (query.executeUpdate(sql, preparedStatementData) != 0)
            {
                updateStatus = true;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return updateStatus;
    }

    public static void updateMonitorAvailability(Query query, Object id, Object availability)
    {
        List<Object> preparedStatementData = new ArrayList<>();

        try
        {
            preparedStatementData.add(availability);

            preparedStatementData.add(id);

            String sql = "UPDATE monitor SET availability_status = ? WHERE id = ?";

            query.executeUpdate(sql, preparedStatementData);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static String[][] createMonitorHtmlTable(List<HashMap<String, Object>> monitorData)
    {
        String[][] monitorTableHtml = null;

        try
        {
            if (monitorData != null)
            {
                monitorTableHtml = new String[monitorData.size()][6];

                int rowNumber = 1;

                for (HashMap<String, Object> tableRow : monitorData)
                {
                    if (tableRow != null)
                    {
                        String[] rowHtml = new String[6];

                        rowHtml[0] = Integer.toString(rowNumber);

                        rowHtml[1] = (String) tableRow.get("name");

                        rowHtml[2] = (String) tableRow.get("ip_hostname");

                        rowHtml[3] = (String) tableRow.get("type");

                        rowHtml[4] = (String) tableRow.get("availability_status");

                        rowHtml[5] = "<div class='monitorOperationsCell' data-database_table_id = " + tableRow.get("id") + "> <i class='fa fa-desktop' title='Show monitor details'></i> <i class='fa fa-edit' title='Edit device'></i> <i class='fa fa-archive' title='Delete monitor'></i> </div>";

                        monitorTableHtml[rowNumber - 1] = rowHtml;

                        rowNumber++;
                    }
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return monitorTableHtml;
    }
}
