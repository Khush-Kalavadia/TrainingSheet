package chatGroup;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Member1Main
{
    public static void main(String[] args)
    {
        try
        {
            Group group = new Group("myGroup");

            Member member = new Member("member1", group);

            member.read();

            member.write();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}

/*
package chatGroup;

public class Member1Main
{
    public static void main(String[] args)
    {
        try
        {
            Group group = new Group("myGroup");

            Member member1 = new Member("member1", group);

            Member member2 = new Member("member2", group);

            Member member3 = new Member("member3", group);

            member1.write("hi i am member1");

            member2.write("hi i am member2");

            member3.write("hi i am member3");

            System.out.println("Motadata Group");

            member1.read();

            member2.read();

            member3.read();

//            Thread.sleep(5000);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}

 */