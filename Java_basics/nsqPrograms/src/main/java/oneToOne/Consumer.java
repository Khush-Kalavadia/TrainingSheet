package oneToOne;

import com.github.brainlag.nsq.NSQConsumer;

import com.github.brainlag.nsq.lookup.DefaultNSQLookup;

import com.github.brainlag.nsq.lookup.NSQLookup;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Consumer
{
    public static void main(String[] args)
    {
        NSQConsumer consumer = null;
        try
        {
            //Lookup
            NSQLookup lookup = new DefaultNSQLookup();

            lookup.addLookupAddress(Nsq.getNsqdHost(), 4161);

            System.out.println(lookup);

            consumer = new NSQConsumer(lookup, "One", "First", (message) ->
            {
                System.out.println(Thread.currentThread().getName()+" : "+new String(message.getMessage()));

                message.finished();
            });

            consumer.setExecutor(Executors.newSingleThreadExecutor());      //if not this then internally it uses Executors.newCachedThreadPool();

            consumer.start();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
//        finally                       //if we close this we consumer would be closed and we won't receive msgs
//        {
//            if (consumer != null)
//            {
//                consumer.shutdown();
//            }
//        }
    }
}
