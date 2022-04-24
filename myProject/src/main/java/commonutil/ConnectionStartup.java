package commonutil;

import javax.servlet.http.HttpServlet;

public class ConnectionStartup extends HttpServlet
{
    public void init()
    {
        try
        {
            if (dao.ConnectionPoolHandler.start() == 0)
            {
                System.exit(1);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            System.exit(1);
        }
    }

    @Override
    public void destroy()
    {
        try
        {
            dao.ConnectionPoolHandler.destroy();               //called when we are redeploying project
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
