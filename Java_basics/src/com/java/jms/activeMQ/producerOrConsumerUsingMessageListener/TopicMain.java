package com.java.jms.activeMQ.producerOrConsumerUsingMessageListener;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicMain
{
    public static void main(String[] args)
    {
        Connection connection = null;

        try
        {
            // Server
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            connection = connectionFactory.createConnection();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Topic topic = session.createTopic("customerTopic");

            // Consumer1 subscribes to customerTopic
            MessageConsumer consumer1 = session.createConsumer(topic);

            consumer1.setMessageListener(new ConsumerMessageListener("Consumer1"));

            // Consumer2 subscribes to customerTopic
            MessageConsumer consumer2 = session.createConsumer(topic);

            consumer2.setMessageListener(new ConsumerMessageListener("Consumer2"));

            connection.start();

            // Publish
            String payload = "Important Task";

            Message msg = session.createTextMessage(payload);

            MessageProducer producer = session.createProducer(topic);

            System.out.println("Sending text '" + payload + "'");

            producer.send(msg);

            Thread.sleep(1000);

            session.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (JMSException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}

