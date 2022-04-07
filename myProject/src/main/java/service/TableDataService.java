package service;

import commonutil.ConnectionStartup;
import dao.Query;

import java.util.ArrayList;
import java.util.List;

public class TableDataService
{
    public List<List<Object>> getTableData(String tableName)
    {
        List<List<Object>> resultSetList = null;

        Query query = new Query();

        try
        {
            List<Object> preparedStatementData = new ArrayList<>();

            query.createConnection();

            //fixme hope this way we can use select method which i created without adding any value in list
            resultSetList = query.select("SELECT id, name, ip_hostname, type FROM " + tableName, preparedStatementData);

            query.releaseConnection();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            query.releaseConnection();
        }
        return resultSetList;
    }

    public static void main(String[] args)
    {
        ConnectionStartup connectionStartup = new ConnectionStartup();

        connectionStartup.init();

        TableDataService tableDataService = new TableDataService();

        System.out.println(tableDataService.getTableData("discovery"));
    }
}
