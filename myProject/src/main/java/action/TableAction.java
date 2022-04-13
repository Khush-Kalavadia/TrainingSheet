package action;

import bean.TableBean;
import com.opensymphony.xwork2.ModelDriven;
import service.TableDataService;

import java.util.List;

public class TableAction implements ModelDriven<TableBean>
{
    private TableBean tableBean = new TableBean();

    private TableDataService tableDataService = new TableDataService();

    public String loadDiscoveryTable()
    {
        try
        {
            tableBean.setTableData(tableDataService.getTableData(tableBean.getTableName()));            //checked if null is returned
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            tableBean.setTableData(null);
        }

        return "success";
    }

    public String deleteDiscoveryRow()
    {
        try
        {
            tableBean.setDeletionStatus(tableDataService.deleteTableRowUsingId(tableBean.getTableName(), tableBean.getTableId()));      //todo record not deleted form credential as it might be in monitors. so will have to check later
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            tableBean.setDeletionStatus(false);
        }

        return "success";
    }

//    public String getDiscoveryRow()
//    {
//        try
//        {
//            tableBean.setReturnRow(null);
//
//            List<Object> discoveryRow = tableDataService.getTableRowUsingId("discovery", "id",  tableBean.getTableId());
//
//            if (!discoveryRow.isEmpty())
//            {
//                if (discoveryRow.get(3).equals("ssh"))
//                {
//                    List<Object> credentialRow = tableDataService.getTableRowUsingId("credential", "map_discovery_id", discoveryRow.get(0));
//
//                    if (!credentialRow.isEmpty())
//                    {
//                        discoveryRow.addAll(credentialRow.subList(1, credentialRow.size()));
//
//                        tableBean.setReturnRow(discoveryRow);
//                    }
//                }
//                else
//                {
//                    tableBean.setReturnRow(discoveryRow);
//                }
//            }
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
//        return "success";
//    }

    @Override
    public TableBean getModel()
    {
        return tableBean;
    }
}
