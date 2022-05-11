package action;

import javax.websocket.*;

import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/server")
public class Server
{
    @OnOpen
    public void handleOpen(Session session)
    {
        try
        {
            System.out.println("Client has connected to the server....");

            session.getBasicRemote().sendText("Hi");        //session plays important role if we want to send msg to client
                                                              //so we can store it and can be used later to send msg using this method
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @OnMessage
    public String handleMessage(String message)         //receiving msg and sending reply back as well. We can keep it void as well.
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