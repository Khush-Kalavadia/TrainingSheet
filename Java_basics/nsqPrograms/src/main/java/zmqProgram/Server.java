package zmqProgram;
//  Hello World server in Java
//  Binds REP socket to tcp://*:5555
//  Expects "Hello" from client, replies with "World"

import org.zeromq.SocketType;

import org.zeromq.ZContext;

import org.zeromq.ZMQ;

public class Server
{
    public static void main(String[] args) throws Exception
    {
        try (ZContext context = new ZContext())         //ZContext implements closeable and it is automatically closed at the end
        {
            //  Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(SocketType.REP);

            socket.bind("tcp://localhost:5555");        //server binds

            System.out.println(socket.getLinger());

            socket.setLinger(3000);

            System.out.println(socket.getLinger());

            while (!Thread.currentThread().isInterrupted())
            {
                byte[] reply = socket.recv(0);

//                byte[] reply = socket.recv(ZMQ.NOBLOCK);

                if (reply != null)
                {
                    System.out.println("Received " + ": [" + new String(reply, ZMQ.CHARSET) + "]");
                }
                else
                {
                    System.out.println("Received " + ": [null]");
                }

                String response = "world";

                socket.send(response.getBytes(ZMQ.CHARSET), 0);

                Thread.sleep(1000); //  Do some 'work'
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
