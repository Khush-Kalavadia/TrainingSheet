package oneToOne;

import com.github.brainlag.nsq.NSQProducer;

import java.io.BufferedReader;

import java.io.InputStreamReader;

public class Producer
{

    public static void main(String[] args)
    {
        NSQProducer producer = null;

        BufferedReader enter = null;

        try
        {
            //Producer
            producer = new NSQProducer();

            producer.addAddress(Nsq.getNsqdHost(), 4150);

            producer.start();

            enter = new BufferedReader(new InputStreamReader(System.in));

            String str;

            while (true)
            {
                System.out.print("Enter text to send: ");

                str = enter.readLine();

                producer.produce("One", str.getBytes());
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