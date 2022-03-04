package oneToOne;

import com.github.brainlag.nsq.NSQConsumer;

import com.github.brainlag.nsq.NSQProducer;

import com.github.brainlag.nsq.lookup.DefaultNSQLookup;

import com.github.brainlag.nsq.lookup.NSQLookup;

import java.io.BufferedReader;

import java.io.InputStreamReader;

public class Producer
{

    public static void main(String[] args)
    {
        NSQProducer producer = null;

        NSQConsumer consumer = null;

        BufferedReader enter = null;

        try
        {
//        System.setProperty("io.netty.noJdkZlibDecoder", "false");

            //Lookup
            NSQLookup lookup = new DefaultNSQLookup();

            System.out.println(lookup);

            lookup.addLookupAddress(Nsq.getNsqLookupdHost(), 4161);

            //Producer
            producer = new NSQProducer();

            producer.addAddress(Nsq.getNsqdHost(), 4150);

            producer.start();

            enter = new BufferedReader(new InputStreamReader(System.in));

            String str;

            while (true)
            {
                System.out.println("Enter text to send: ");

                str = enter.readLine();

                producer.produce("OnetoOne", str.getBytes());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (producer != null)
            {
                producer.shutdown();
            }
        }
    }
}