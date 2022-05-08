package commonutil;

import java.util.concurrent.ForkJoinPool;

class ForkJoinPoolUtil
{
    private static final ForkJoinPool DISCOVERY_FORK_JOIN_POOL;

    private static final ForkJoinPool MONITOR_FORK_JOIN_POOL;

    static
    {
        DISCOVERY_FORK_JOIN_POOL = new ForkJoinPool(Runtime.getRuntime().availableProcessors() / 2);

        MONITOR_FORK_JOIN_POOL = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
    }

    static ForkJoinPool getDiscoveryForkJoinPool()
    {
        return DISCOVERY_FORK_JOIN_POOL;
    }

    static ForkJoinPool getMonitorForkJoinPool()
    {
        return MONITOR_FORK_JOIN_POOL;
    }
}
