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
            //called when we are redeploying project

            dao.ConnectionPoolHandler.destory();                //todo check destory method else it would wait if all threads are not there
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
