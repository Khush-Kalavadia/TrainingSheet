package chatGroup;

public class Member3Main
{
    public static void main(String[] args)
    {
        try
        {
            Group group = new Group("myGroup");

            Member member = new Member("member3", group);

            member.read();

            member.write();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
