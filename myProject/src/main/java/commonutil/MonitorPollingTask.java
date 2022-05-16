package commonutil;

import bean.DiscoveryBean;
import dao.Query;
import helper.MonitorTableHelper;
import helper.PollingTableHelper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.RecursiveAction;

public class MonitorPollingTask extends RecursiveAction             //will not return value
{
    private DiscoveryBean deviceDetail;

    MonitorPollingTask(DiscoveryBean deviceDetail)
    {
        this.deviceDetail = deviceDetail;
    }

    @Override
    protected void compute()
    {
        Query query = null;

        try
        {
            PingDeviceTask pingDeviceTask = new PingDeviceTask(deviceDetail.getIpHostname());

            HashMap<String, Object> sshPollingDeviceDetail = null;

            pingDeviceTask.fork();

            HashMap<String, Object> pingDeviceDetail = pingDeviceTask.join();

            Timestamp timestamp = new Timestamp(new Date().getTime());

            query = new Query();

            query.getConnection();

            if (pingDeviceDetail != null)
            {
                if (!pingDeviceDetail.isEmpty())
                {
                    pingDeviceDetail.put("availability", (Float) pingDeviceDetail.get("packetLoss") < 50 ? 1 : 0);

                    if (deviceDetail.getType().equals("ssh"))
                    {
                        if ((int) pingDeviceDetail.get("availability") == 1)
                        {
                            SSHPollingDeviceTask sshPollingDeviceTask = new SSHPollingDeviceTask(deviceDetail.getUsername(), deviceDetail.getPassword(), deviceDetail.getIpHostname());

                            sshPollingDeviceTask.fork();

                            sshPollingDeviceDetail = sshPollingDeviceTask.join();

                            timestamp = new Timestamp(new Date().getTime());

                            if (sshPollingDeviceDetail != null && sshPollingDeviceDetail.get("totalDiskGB") != null && sshPollingDeviceDetail.get("usedDiskPercent") != null)
                            {
                                sshPollingDeviceDetail.put("usedDiskGB", (float) sshPollingDeviceDetail.get("totalDiskGB") * (float) sshPollingDeviceDetail.get("usedDiskPercent") * 0.01);
                            }
                        }

                        if (sshPollingDeviceDetail == null)
                        {
                            sshPollingDeviceDetail = new HashMap<>();
                        }

                        sshPollingDeviceDetail.put("map_monitor_id", deviceDetail.getId());

                        sshPollingDeviceDetail.put("dateTime", timestamp);
                    }
                    MonitorTableHelper.updateMonitorAvailability(query, deviceDetail.getId(), (int) pingDeviceDetail.get("availability") == 1 ? "up" : "down");
                }
                else
                {
                    MonitorTableHelper.updateMonitorAvailability(query, deviceDetail.getId(), "unknown");
                }
            }
            else
            {
                pingDeviceDetail = new HashMap<>();

                MonitorTableHelper.updateMonitorAvailability(query, deviceDetail.getId(), "unknown");
            }

            pingDeviceDetail.put("map_monitor_id", deviceDetail.getId());

            pingDeviceDetail.put("dateTime", timestamp);

            PollingTableHelper.addPollingDeviceDetail(query, pingDeviceDetail, sshPollingDeviceDetail);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (query != null)
            {
                query.releaseConnection();
            }
        }
    }
}

