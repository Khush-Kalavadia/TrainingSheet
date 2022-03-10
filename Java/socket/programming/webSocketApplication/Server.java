import javax.websocket.*;

import javax.websocket.server.ServerEndpoint;

import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/server")
public class Server
{
    ConcurrentHashMap<String, Session> sessionConcurrentHashMap = new ConcurrentHashMap<>();

    @OnOpen
    public void handleOpen(Session session)
    {
        try
        {
            System.out.println("Client has connected to the server....");

            sessionConcurrentHashMap.put(session.getId(), session);

            session.getBasicRemote().sendText("Hi");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @OnMessage
    public String handleMessage(String message)
    {
        System.out.println("From Client to server : " + message);

        System.out.println("Sending to the Client : " + message + "-FromServer");

        return message + "-FromServer";
    }

    @OnClose
    public void handleClose()
    {
        System.out.println("Client is now disconnected....");
    }

    @OnError
    public void handleError(Throwable throwable)
    {
        throwable.printStackTrace();
    }
}