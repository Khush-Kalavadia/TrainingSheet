package chatApp;

public class Member3Main
{
    public static void main(String[] args)
    {
        try
        {
            Application application = new Application();

            Member member = new Member("member3", application);

            System.out.println(member.memberName + " started: ");

            member.start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
