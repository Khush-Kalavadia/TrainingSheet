package action;

import commonutil.ForkJoinPoolUtil;
import commonutil.RunDiscoveryTask;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;

@ServerEndpoint("/server-endpoint")
public class WebSocketHandler
{
    private Session session;

    @OnOpen
    public void handleOpen(Session session)
    {
        this.session = session;
    }

    @OnClose
    public void handleClose()
    {
//        System.out.println("Web socket is now disconnected");
    }

    @OnError
    public void handleError(Throwable throwable)
    {
        throwable.printStackTrace();
    }

    @OnMessage
    public void handleMessage(Integer id)         //receiving request and storing the session as well to send reply back to same session.
    {
        try
        {
            HashMap<String, Object> discoveryDeviceInfo = new HashMap<>();

            discoveryDeviceInfo.put("id", id);

            discoveryDeviceInfo.put("session", session);

            ForkJoinPoolUtil.getDiscoveryForkJoinPool().execute(new RunDiscoveryTask(discoveryDeviceInfo));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
