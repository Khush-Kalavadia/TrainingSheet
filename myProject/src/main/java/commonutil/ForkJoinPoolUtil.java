package commonutil;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolUtil
{
    public static final ForkJoinPool FORK_JOIN_POOL;

    static
    {
        FORK_JOIN_POOL = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
    }
}
