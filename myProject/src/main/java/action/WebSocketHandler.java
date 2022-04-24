package action;

import service.RunDiscoveryPoolService;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/server-endpoint")
public class WebSocketHandler
{
    private Session session;

    @OnOpen
    public void handleOpen(Session session)
    {
        try
        {
            this.session = session;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @OnClose
    public void handleClose()
    {
        System.out.println("Web socket is now disconnected");
    }

    @OnError
    public void handleError(Throwable throwable)
    {
        throwable.printStackTrace();
    }

    @OnMessage
    public void handleMessage(Integer id)         //receiving msg and sending reply back as well. We can keep it void as well.
    {
        try
        {
            RunDiscoveryPoolService.addDiscoveryDevice(id, session);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
