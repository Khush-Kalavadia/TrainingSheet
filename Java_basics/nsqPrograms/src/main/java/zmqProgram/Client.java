package zmqProgram;

//  Hello World client in Java
//  Connects REQ socket to tcp://localhost:5555
//  Sends "Hello" to server, expects "World" back

/*
context
ZMQ.socket socket = context.createSocket(SocketType.REQ);
byte[] received = socket.recv(0)
socket.send(myString.getBytes(ZMQ.CHARSET), 0)
sout(new String(reply, ZMQ.CHARSET))
 */

import org.zeromq.SocketType;

import org.zeromq.ZMQ;

import org.zeromq.ZContext;

public class Client
{
    public static void main(String[] args)
    {
        try (ZContext context = new ZContext())
        {
            System.out.println("Connecting to hello world server");

            //  Socket to talk to server
            ZMQ.Socket socket = context.createSocket(SocketType.REQ);

            socket.connect("tcp://localhost:5555");     //socket connects

            for (int requestNbr = 0; requestNbr != 10; requestNbr++)
            {
                String request = "Hello";

                socket.send(request.getBytes(ZMQ.CHARSET), 0);      //blocking

//                socket.send(request.getBytes(ZMQ.CHARSET), ZMQ.NOBLOCK);      //non-blocking. Could be mainly implemented in push pull

//                socket.send(request.getBytes(ZMQ.CHARSET), ZMQ.SNDMORE);      //multiple message parts

                System.out.println("Sending Hello " + requestNbr);

                byte[] reply = socket.recv(0);      //blocking

                if (reply == null)
                {
                    System.out.println("Received null");
                }
                else
                {
                    System.out.println("Received " + new String(reply, ZMQ.CHARSET) + " " + requestNbr);
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}

