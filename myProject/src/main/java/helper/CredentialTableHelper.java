package helper;

import dao.Query;

import java.util.ArrayList;
import java.util.List;

public class CredentialTableHelper
{
    public static boolean insertCredentialTableRow(Query query, Object id, Object username, Object password)
    {
        boolean insertionStatus = false;

        List<Object> preparedStatementData = new ArrayList<>();

        try
        {
            preparedStatementData.add(id);

            preparedStatementData.add(username);

            preparedStatementData.add(password);

            String sql = "INSERT INTO credential(map_discovery_id, username, password) VALUES (?, ?, ?)";

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

    public static boolean updateCredentialTableRow(Query query, Object id, Object username, Object password)
    {
        boolean updateStatus = false;

        List<Object> preparedStatementData = new ArrayList<>();

        try
        {
            preparedStatementData.add(username);

            preparedStatementData.add(password);

            preparedStatementData.add(id);

            String sql = "UPDATE credential SET username = ?, password = ? WHERE map_discovery_id = ?";

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
}
