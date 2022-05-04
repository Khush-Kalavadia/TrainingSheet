package commonutil;

public class RunDiscoveryForkJoinPoolRunnable implements Runnable
{
    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                ForkJoinPoolUtil.getDiscoveryForkJoinPool().execute(new RunDiscoveryTask(DataBlockingQueue.getRunDiscoveryQueue().take()));
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
