package bean;

import java.util.HashMap;
import java.util.List;

public class DiscoveryBean
{
    private int id;

    private String name;

    private String ipHostname;

    private String type;

    private String username;

    private String password;

    private boolean operationSuccess;

    private List<HashMap<String, Object>> discoveryTableData;

    private boolean duplicateEntry;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getIpHostname()
    {
        return ipHostname;
    }

    public void setIpHostname(String ipHostname)
    {
        this.ipHostname = ipHostname;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isOperationSuccess()
    {
        return operationSuccess;
    }

    public void setOperationSuccess(boolean operationSuccess)
    {
        this.operationSuccess = operationSuccess;
    }

    public List<HashMap<String, Object>> getDiscoveryTableData()
    {
        return discoveryTableData;
    }

    public void setDiscoveryTableData(List<HashMap<String, Object>> discoveryTableData)
    {
        this.discoveryTableData = discoveryTableData;
    }

    public boolean isDuplicateEntry()
    {
        return duplicateEntry;
    }

    public void setDuplicateEntry(boolean duplicateEntry)
    {
        this.duplicateEntry = duplicateEntry;
    }
}
