package commonutil;

public class MonitorPollingForkJoinPoolRunnable implements Runnable
{
    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                ForkJoinPoolUtil.getMonitorForkJoinPool().execute(new MonitorPollingTask(DataBlockingQueue.getMonitorPollingQueue().take()));
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}

