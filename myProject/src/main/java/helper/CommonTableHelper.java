package helper;

import dao.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommonTableHelper
{
    public static boolean deleteTableRowUsingId(Query query, String tableName, String idName, Object tableId)
    {
        boolean deletionStatus = false;

        try
        {
            String sql = "DELETE FROM " + tableName + " WHERE " + idName + " = ?";

            List<Object> preparedStatementData = new ArrayList<>();

            preparedStatementData.add(tableId);

            if (query.executeUpdate(sql, preparedStatementData) != 0)
            {
                deletionStatus = true;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return deletionStatus;
    }
}
