package chatGroup;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.github.brainlag.nsq.lookup.DefaultNSQLookup;

import com.github.brainlag.nsq.lookup.NSQLookup;

import oneToOne.Nsq;

import java.io.BufferedReader;

import java.io.InputStreamReader;

public class Member
{
    Group group;

    String consumerName;

    ObjectMapper mapper = new ObjectMapper();

    NSQLookup lookup = new DefaultNSQLookup(mapper);

    Member(String consumerName, Group group)
    {
        this.consumerName = consumerName;

        this.group = group;

        lookup.addLookupAddress(Nsq.getNsqLookupdHost(), 4161);
    }

    void read()
    {
        try
        {
            new Thread(new MemberReaderRunnable(lookup, group.groupName, consumerName)).start();
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

            String enterText, finalText;

            while (true)
            {
                System.out.println("Enter text");

                enterText = enter.readLine();

                finalText = consumerName + " sends -> " + enterText;

                group.producer.produce(group.groupName, finalText.getBytes());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
