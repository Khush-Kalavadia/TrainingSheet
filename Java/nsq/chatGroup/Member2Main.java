package chatGroup;

public class Member2Main
{
    public static void main(String[] args)
    {
        try
        {
            Group group = new Group("myGroup");

            Member member = new Member("member2", group);

            member.read();

            member.write();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
