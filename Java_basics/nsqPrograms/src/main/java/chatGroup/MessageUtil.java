package chatGroup;

class MessageUtil
{
    static String getDataToSend(String senderName, String message)
    {
        try
        {
            return senderName + ">" + message.replace('>','#');
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    static String getSenderName(String receivedData)
    {
        try
        {
            return receivedData.substring(0, receivedData.indexOf('>'));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

//    static String getReceiverName(String receivedData)
//    {
//        try
//        {
//            return receivedData.substring(receivedData.indexOf('>') + 1, receivedData.indexOf('#'));
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//
//    static String getMessage(String receivedData)
//    {
//        try
//        {
//            return receivedData.substring(receivedData.indexOf('#') + 1, receivedData.length());
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//
//    public static void main(String[] args)        //to check the utility
//    {
//        try
//        {
//            String msg = MessageUtil.getDataToSend("client1", "client2>hiii guys");
//
//            System.out.println(msg);
//
//            System.out.println(MessageUtil.getSenderName(msg));
//
//            System.out.println(MessageUtil.getReceiverName(msg));
//
//            System.out.println(MessageUtil.getMessage(msg));
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
}
