package com.java.jms.oneToOne;

import org.apache.activemq.ActiveMQConnection;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

import java.io.BufferedReader;

import java.io.InputStreamReader;

public class Producer
{
//    public static String subject = "TESTQUEUE";

    public static String subject = "QUEUE1";

    public static void main(String[] args)
    {
        Connection connection = null;

        Session session = null;

        MessageProducer producer = null;

        BufferedReader enter = null;

        try
        {
            //(id, password) are (admin, admin)
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
                    ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");

            connection = connectionFactory.createConnection();

            connection.start();

            /*
            The createSession() method has two parameters:
            1. A parameter that specifies whether the session is transacted or not transacted
            2. A parameter that specifies the acknowledgment mode for the session
             */
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue(subject);

            producer = session.createProducer(destination);

            TextMessage message;

            enter = new BufferedReader(new InputStreamReader(System.in));

            while (true)
            {
                System.out.print("Enter text: ");

                message = session.createTextMessage(enter.readLine());

                producer.send(message);

                System.out.println("Sent message -> " + message.getText());
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
                if (producer != null)
                {
                    producer.close();
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
            catch (JMSException e)
            {
                e.printStackTrace();
            }

        }
    }
}
