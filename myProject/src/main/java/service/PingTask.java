package service;

import java.util.concurrent.RecursiveTask;

public class PingTask extends RecursiveTask<Float>
{
    private String ipHostname;

    public PingTask(String ipHostname)
    {
        this.ipHostname = ipHostname;
    }

    @Override
    protected Float compute()
    {
        return DiscoveryService.getPingPacketLoss(ipHostname);
    }
}
