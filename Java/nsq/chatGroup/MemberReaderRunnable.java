package chatGroup;

import com.github.brainlag.nsq.NSQConsumer;

import com.github.brainlag.nsq.lookup.NSQLookup;

public class MemberReaderRunnable implements Runnable
{

    NSQLookup lookup;

    String groupName;

    String consumerName;

    MemberReaderRunnable(NSQLookup lookup, String groupName, String consumerName)
    {
        this.lookup = lookup;

        this.groupName = groupName;

        this.consumerName = consumerName;
    }

    @Override
    public void run()
    {
        try
        {
            NSQConsumer consumer;

            //#ephemeral "IMPORTANT concept": used to drop the message which were received when not online
            //Channel created when subscribed for the first time
            consumer = new NSQConsumer(lookup, groupName, consumerName + "#ephemeral", (message) ->
            {
                String messageString = new String(message.getMessage());

                System.out.println(messageString);

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
    }
}
