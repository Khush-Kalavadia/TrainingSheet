package dao;

import java.util.ArrayList;
import java.util.List;

public class CredentialDao
{
    public static boolean insertCredentialTableRow(Object id, Object username, Object password)
    {
        boolean insertionStatus = false;

        Query query = new Query();

        List<Object> preparedStatementData = new ArrayList<>();


        try
        {
            preparedStatementData.add(id);

            preparedStatementData.add(username);

            preparedStatementData.add(password);

            String sql = "INSERT INTO credential(map_discovery_id, username, password) VALUES (?, ?, ?)";

            query.createConnection();

            if (query.executeUpdate(sql, preparedStatementData) != 0)
            {
                insertionStatus = true;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            query.releaseConnection();
        }
        return insertionStatus;
    }

    public static boolean updateCredentialTableRow(Object id, Object username, Object password)
    {
        boolean updateStatus = false;

        Query query = new Query();

        List<Object> preparedStatementData = new ArrayList<>();

        try
        {
            preparedStatementData.add(username);

            preparedStatementData.add(password);

            preparedStatementData.add(id);

            String sql = "UPDATE credential SET username = ?, password = ? WHERE map_discovery_id = ?";

            query.createConnection();

            if (query.executeUpdate(sql, preparedStatementData) != 0)
            {
                updateStatus = true;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            query.releaseConnection();
        }
        return updateStatus;
    }
}
