package action;

import commonutil.DataBlockingQueue;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/server-endpoint")
public class WebSocketHandler                   //testme why am i not able to see ws request on network inspect
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
        DataBlockingQueue.addDiscoveryDevice(id, session);
    }
}
