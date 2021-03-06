package commonutil;


import helper.NetworkingCommandHelper;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SSHDiscoveryDeviceTask extends RecursiveTask<HashMap<String, String>>
{
    private String username;

    private String password;

    private String host;

    private List<String> commandList;

    SSHDiscoveryDeviceTask(String username, String password, String host, List<String> commandList)
    {
        this.username = username;

        this.password = password;

        this.host = host;

        this.commandList = commandList;
    }

    @Override
    protected HashMap<String, String> compute()
    {
        return NetworkingCommandHelper.fireSSHCommands(username, password, host, commandList);
    }
}
