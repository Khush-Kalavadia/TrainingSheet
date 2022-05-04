package commonutil;

import helper.NetworkingCommandHelper;

import java.util.HashMap;
import java.util.concurrent.RecursiveTask;

public class SSHPollingDeviceTask extends RecursiveTask<HashMap<String, Object>>
{
    private String username;

    private String password;

    private String host;

    SSHPollingDeviceTask(String username, String password, String host)
    {
        this.username = username;

        this.password = password;

        this.host = host;
    }

    @Override
    protected HashMap<String, Object> compute()
    {
        return NetworkingCommandHelper.getParsedSSHDeviceDetail(username, password, host);
    }
}

