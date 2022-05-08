package commonutil;

import helper.NetworkingCommandHelper;

import java.util.HashMap;
import java.util.concurrent.RecursiveTask;

public class PingDeviceTask extends RecursiveTask<HashMap<String, Object>>
{
    private String ipHostname;

    PingDeviceTask(String ipHostname)
    {
        this.ipHostname = ipHostname;
    }

    @Override
    protected HashMap<String, Object> compute()
    {
        return NetworkingCommandHelper.getParsedPingDeviceDetail(ipHostname);
    }
}
