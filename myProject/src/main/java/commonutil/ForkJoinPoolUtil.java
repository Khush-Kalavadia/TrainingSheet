package commonutil;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolUtil
{
    private static final ForkJoinPool DISCOVERY_FORK_JOIN_POOL;

    private static final ForkJoinPool MONITOR_FORK_JOIN_POOL;

    static
    {
        DISCOVERY_FORK_JOIN_POOL = new ForkJoinPool(Runtime.getRuntime().availableProcessors() / 2, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);

        MONITOR_FORK_JOIN_POOL = new ForkJoinPool(Runtime.getRuntime().availableProcessors(), ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);
    }

    public static ForkJoinPool getDiscoveryForkJoinPool()
    {
        return DISCOVERY_FORK_JOIN_POOL;
    }

    static ForkJoinPool getMonitorForkJoinPool()
    {
        return MONITOR_FORK_JOIN_POOL;
    }
}
