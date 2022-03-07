package chatApp;

import com.github.brainlag.nsq.NSQConsumer;

import com.github.brainlag.nsq.lookup.NSQLookup;

public class MemberReaderRunnable implements Runnable
{
    NSQLookup lookup;

    String consumerName;

    MemberReaderRunnable(NSQLookup lookup, String consumerName)
    {
        this.lookup = lookup;

        this.consumerName = consumerName;
    }

    @Override
    public void run()
    {
        try
        {
            NSQConsumer consumer = new NSQConsumer(lookup, consumerName, "user", nsqMessage ->
            {
                System.out.println(MessageUtil.getMessageToPresent(new String(nsqMessage.getMessage())));

                nsqMessage.finished();
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
    }
}
