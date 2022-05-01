
package commonutil;

import javax.servlet.http.HttpServlet;

public class ForkJoinPoolStartup extends HttpServlet
{
    public void init()
    {
        try
        {
            (new Thread(new MonitorPollingForkJoinPoolRunnable())).start();

            (new Thread(new RunDiscoveryForkJoinPoolRunnable())).start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}