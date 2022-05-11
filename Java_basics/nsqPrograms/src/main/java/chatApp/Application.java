package chatApp;

import com.github.brainlag.nsq.NSQProducer;

import oneToOne.Nsq;

public class Application
{
    NSQProducer producer = new NSQProducer();

    Application()
    {
        producer.addAddress(Nsq.getNsqdHost(), 4150);           //here, Nsq.getNsqdHost() = localhost

        producer.start();
    }
}
