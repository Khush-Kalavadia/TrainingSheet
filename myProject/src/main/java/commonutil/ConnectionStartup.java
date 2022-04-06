package commonutil;

import javax.servlet.http.HttpServlet;

public class ConnectionStartup extends HttpServlet
{
    public void init()
    {
        try
        {
            dao.ConnectionPoolHandler.start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void destroy()
    {
        try
        {
            dao.ConnectionPoolHandler.destory();               //called when we are redeploying project
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
