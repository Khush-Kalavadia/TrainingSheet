package chatApp;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.github.brainlag.nsq.lookup.DefaultNSQLookup;

import com.github.brainlag.nsq.lookup.NSQLookup;
import oneToOne.Nsq;

import java.io.BufferedReader;

import java.io.InputStreamReader;

public class Member
{
    String memberName;

    Application application;

    NSQLookup lookup = new DefaultNSQLookup();

    public Member(String memberName, Application application)
    {
        this.memberName = memberName;

        this.application = application;

        lookup.addLookupAddress(Nsq.getNsqLookupdHost(), 4161);
    }

    void start()
    {
        try
        {
            this.read();

            this.write();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    void read()
    {
        try
        {
            new Thread(new MemberReaderRunnable(lookup, memberName)).start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    void write()
    {
        try
        {
            BufferedReader enter = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter in format <user> > <message>: \n");

            String enterString;

            while (true)
            {
                enterString = enter.readLine();

                application.producer.produce(MessageUtil.getReceiverName(enterString), MessageUtil.getDataToSend(memberName, enterString).getBytes());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
//user1>user2#message
