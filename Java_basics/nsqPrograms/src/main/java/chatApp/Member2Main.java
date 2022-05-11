package chatApp;

public class Member2Main
{
    public static void main(String[] args)
    {
        try
        {
            Application application = new Application();

            Member member = new Member("member2", application);

            System.out.println(member.memberName + " started: ");

            member.start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
