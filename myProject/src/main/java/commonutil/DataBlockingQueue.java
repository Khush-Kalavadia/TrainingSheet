package commonutil;

import bean.DiscoveryBean;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class DataBlockingQueue
{
    private static final LinkedBlockingQueue<HashMap<String, Object>> RUN_DISCOVERY_QUEUE;

    private static final LinkedBlockingQueue<DiscoveryBean> MONITOR_POLLING_QUEUE;

    static
    {
        RUN_DISCOVERY_QUEUE = new LinkedBlockingQueue<>();

        MONITOR_POLLING_QUEUE = new LinkedBlockingQueue<>();
    }

    static LinkedBlockingQueue<DiscoveryBean> getMonitorPollingQueue()
    {
        return MONITOR_POLLING_QUEUE;
    }

    public static void addDiscoveryDevice(int id, Session session)
    {
        try
        {
            HashMap<String, Object> discoveryDeviceInfo = new HashMap<>();

            discoveryDeviceInfo.put("id", id);

            discoveryDeviceInfo.put("session", session);

            RUN_DISCOVERY_QUEUE.put(discoveryDeviceInfo);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static LinkedBlockingQueue<HashMap<String, Object>> getRunDiscoveryQueue()
    {
        return RUN_DISCOVERY_QUEUE;
    }
}
