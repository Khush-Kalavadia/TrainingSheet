package service;

import commonutil.ForkJoinPoolUtil;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.LinkedBlockingQueue;

public class RunDiscoveryPoolService
{
    public static final LinkedBlockingQueue<HashMap<String, Object>> DISCOVERY_POOL;

    static
    {
        DISCOVERY_POOL = new LinkedBlockingQueue<>();
    }

    public static void addDiscoveryDevice(int id, Session session)
    {
        try
        {
            HashMap<String, Object> discoveryDeviceInfo = new HashMap<>();

            discoveryDeviceInfo.put("id", id);

            discoveryDeviceInfo.put("session", session);

            DISCOVERY_POOL.add(discoveryDeviceInfo);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
