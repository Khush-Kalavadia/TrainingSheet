package commonutil;

import com.jcraft.jsch.HASH;
import service.NetworkingCommandService;

import java.util.HashMap;
import java.util.concurrent.RecursiveTask;

public class PingDeviceTask extends RecursiveTask<HashMap<String, Object>>
{
    private String ipHostname;

    public PingDeviceTask(String ipHostname)
    {
        this.ipHostname = ipHostname;
    }

    @Override
    protected HashMap<String, Object> compute()
    {
        return NetworkingCommandService.getParsedPingDeviceDetail(ipHostname);
    }
}
