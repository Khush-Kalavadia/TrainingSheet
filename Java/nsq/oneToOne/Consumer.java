package oneToOne;

import com.github.brainlag.nsq.NSQConsumer;

import com.github.brainlag.nsq.lookup.DefaultNSQLookup;

import com.github.brainlag.nsq.lookup.NSQLookup;

public class Consumer
{
    public static void main(String[] args)
    {
        NSQConsumer consumer = null;
        try
        {
            //Lookup
            NSQLookup lookup = new DefaultNSQLookup();

            lookup.addLookupAddress(Nsq.getNsqLookupdHost(), 4161);

            System.out.println(lookup);

            consumer = new NSQConsumer(lookup, "OnetoOne", "First", (message) ->
            {
                System.out.println(new String(message.getMessage()));

                message.finished();
            });

            while (true)
            {
                consumer.start();
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (consumer != null)
            {
                consumer.shutdown();
            }
        }
    }
}
