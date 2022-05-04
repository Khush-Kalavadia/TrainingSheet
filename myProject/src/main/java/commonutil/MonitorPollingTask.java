package commonutil;

import bean.DiscoveryBean;
import helper.MonitorTableHelper;
import helper.PollingTableHelper;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.RecursiveAction;

public class MonitorPollingTask extends RecursiveAction
{
    private DiscoveryBean deviceDetail;

    public MonitorPollingTask(DiscoveryBean deviceDetail)
    {
        this.deviceDetail = deviceDetail;
    }

    @Override
    protected void compute()
    {
        try
        {
            PingDeviceTask pingDeviceTask = new PingDeviceTask(deviceDetail.getIpHostname());

            HashMap<String, Object> sshPollingDeviceDetail = null;

            pingDeviceTask.fork();

            HashMap<String, Object> pingDeviceDetail = pingDeviceTask.join();

            Timestamp timestamp = new Timestamp(new Date().getTime());

            if (pingDeviceDetail != null)
            {
                if (!pingDeviceDetail.isEmpty())
                {
                    pingDeviceDetail.put("availability", (Float) pingDeviceDetail.get("packetLoss") < 50 ? 1 : 0);

                    MonitorTableHelper.updateMonitorAvailability(deviceDetail.getId(), (int) pingDeviceDetail.get("availability") == 1 ? "up" : "down");

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
                        else
                        {
                            sshPollingDeviceDetail = new HashMap<>();
                        }

                        sshPollingDeviceDetail.put("map_discovery_id", deviceDetail.getId());

                        sshPollingDeviceDetail.put("dateTime", timestamp);
                    }
                }
                else
                {
                    MonitorTableHelper.updateMonitorAvailability(deviceDetail.getId(), "unknown");
                }
            }
            else
            {
                pingDeviceDetail = new HashMap<>();

                MonitorTableHelper.updateMonitorAvailability(deviceDetail.getId(), "unknown");
            }

            pingDeviceDetail.put("map_discovery_id", deviceDetail.getId());

            pingDeviceDetail.put("dateTime", timestamp);

            PollingTableHelper.addPollingDeviceDetail(pingDeviceDetail, sshPollingDeviceDetail);

            System.out.println(new Date() + "\n" + deviceDetail.getType() + "-" + deviceDetail.getIpHostname() + "--->" + pingDeviceDetail + "||" + sshPollingDeviceDetail);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

}

