package bean;

import java.util.HashMap;
import java.util.List;

public class TableBean
{
    private String tableName;

    private List<HashMap<String, Object>> tableData;

    private int tableId;

    private boolean deletionStatus;

    private List<Object> returnRow;

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public List<HashMap<String, Object>> getTableData()
    {
        return tableData;
    }

    public void setTableData(List<HashMap<String, Object>> tableData)
    {
        this.tableData = tableData;
    }

    public int getTableId()
    {
        return tableId;
    }

    public void setTableId(int tableId)
    {
        this.tableId = tableId;
    }

    public boolean isDeletionStatus()
    {
        return deletionStatus;
    }

    public void setDeletionStatus(boolean deletionStatus)
    {
        this.deletionStatus = deletionStatus;
    }

    public List<Object> getReturnRow()
    {
        return returnRow;
    }

    public void setReturnRow(List<Object> returnRow)
    {
        this.returnRow = returnRow;
    }
}