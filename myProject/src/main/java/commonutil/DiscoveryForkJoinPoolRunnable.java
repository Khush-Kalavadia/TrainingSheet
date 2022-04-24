package commonutil;

import service.DiscoveryTask;
import service.RunDiscoveryPoolService;

public class DiscoveryForkJoinPoolRunnable implements Runnable
{
    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                ForkJoinPoolUtil.FORK_JOIN_POOL.execute(new DiscoveryTask(RunDiscoveryPoolService.DISCOVERY_POOL.take()));
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
