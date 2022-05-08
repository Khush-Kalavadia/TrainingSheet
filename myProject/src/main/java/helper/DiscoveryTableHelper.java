package helper;

import dao.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiscoveryTableHelper
{
    public static int insertDiscoveryTableRow(Query query, Object name, Object ip_hostname, Object type)
    {
        int insertedRowNumber = 0;

        List<Object> preparedStatementData = new ArrayList<>();

        try
        {
            preparedStatementData.add(name);

            preparedStatementData.add(ip_hostname);

            preparedStatementData.add(type);

            String sql = "INSERT INTO discovery(name, ip_hostname, type) VALUES (?, ?, ?)";

            insertedRowNumber = query.executeUpdate(sql, preparedStatementData);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return insertedRowNumber;
    }

    public static boolean updateDiscoveryTableRow(Query query, Object id, Object name, Object ip_hostname, Object type)
    {
        boolean updateStatus = false;

        List<Object> preparedStatementData = new ArrayList<>();

        try
        {
            preparedStatementData.add(name);

            preparedStatementData.add(ip_hostname);

            preparedStatementData.add(type);

            preparedStatementData.add(id);

            String sql = "UPDATE discovery SET name = ?, ip_hostname = ?, type = ? WHERE id = ?";

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

    public static void setProvision(Query query, int id, int provision)
    {
        try
        {
            List<Object> preparedStatementData = new ArrayList<>();

            preparedStatementData.add(provision);

            preparedStatementData.add(id);

            String sql = "UPDATE discovery SET provision = ? WHERE id = ?";

            query.executeUpdate(sql, preparedStatementData);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static String[][] createDiscoveryTableHtml(List<HashMap<String, Object>> discoveryData)
    {
        String[][] discoveryTableHtml = null;

        try
        {
            if (discoveryData != null && !discoveryData.isEmpty())
            {
                discoveryTableHtml = new String[discoveryData.size()][5];

                int rowNumber = 1;

                for (HashMap<String, Object> tableRow : discoveryData)
                {
                    if (tableRow != null)
                    {
                        String[] rowHtml = new String[5];

                        rowHtml[0] = Integer.toString(rowNumber);

                        rowHtml[1] = (String) tableRow.get("name");

                        rowHtml[2] = (String) tableRow.get("ip_hostname");

                        rowHtml[3] = (String) tableRow.get("type");

                        String iconDivHtml = "<div class='discoveryOperationsCell' data-database_table_id = " + tableRow.get("id") + "><i class='fa fa-play' title='Run Discovery'></i><i class='fa fa-edit' title='Edit device'></i><i class='fa fa-archive' title='Delete Device'></i>";

                        if ((int) tableRow.get("provision") == 1)
                        {
                            iconDivHtml += "<i class='fa fa-plus' title='Provision Device'></i>";
                        }

                        iconDivHtml += "</div>";

                        rowHtml[4] = iconDivHtml;

                        discoveryTableHtml[rowNumber - 1] = rowHtml;

                        rowNumber++;
                    }
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return discoveryTableHtml;
    }
}
