package com.java.jms.activeMQ.pointToPoint;

import org.apache.activemq.ActiveMQConnection;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/*
Create multiple consumer. Different users are created.
When we share from one producer and multiple clients are created then the message is send turn-by-turn.
 */

public class Consumer
{
    public static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

//    public static String subject = "TESTQUEUE";

    public static String subject = "QUEUE1";

    public static void main(String[] args)
    {
        Connection connection = null;

        Session session = null;

        MessageConsumer consumer = null;

        try
        {
            System.out.println("URL -> " + url);

            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

            connection = connectionFactory.createConnection();

            connection.start();         //Prints INFO

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue(subject);

            consumer = session.createConsumer(destination);

            System.out.println("Consumer -> " + consumer);

            Message message;

            while (true)
            {
                message = consumer.receive();

                if (message instanceof TextMessage)
                {
                    TextMessage textMessage = (TextMessage) message;

                    System.out.println("Received message -> " + textMessage.getText());
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (consumer != null)
                {
                    consumer.close();
                }

                if (session != null)
                {
                    session.close();
                }

                if (connection != null)
                {
                    connection.close();
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
