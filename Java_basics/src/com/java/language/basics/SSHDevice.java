package com.java.language.basics;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;

public class SSHDevice
{
    public static void fireExecSSHCommand(String username, String password, String host, int port, String command)
    {
        Session session = null;

        ChannelExec channel = null;

        try
        {
            session = new JSch().getSession(username, host, port);

            if (session != null)
            {
                session.setPassword(password);

                session.setConfig("StrictHostKeyChecking", "no");       //accepts SSH host keys from remote servers and those not in the known host’s list.

                session.connect(10000);

                if (session.isConnected())
                {
                    channel = (ChannelExec) session.openChannel("exec");

                    if (channel != null)
                    {
                        channel.setCommand(command);

                        channel.setInputStream(null);

                        channel.setErrStream(System.err);

//                        ByteArrayOutputStream responseStream = new ByteArrayOutputStream();

//                        channel.setOutputStream(responseStream);

                        BufferedInputStream bufferedInputStream = new BufferedInputStream(channel.getInputStream());

                        channel.connect(10000);

                        while (channel.isConnected())
                        {
                            Thread.sleep(100);
                        }

//                        String responseString = new String(responseStream.toByteArray());
//
//                        System.out.println(responseString);

                        int i;

                        while ((i = bufferedInputStream.read()) != -1)
                        {
                            System.out.print((char) i);
                        }
                    }
                }

            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (channel != null)
            {
                channel.disconnect();       //Closing the channel will in turn cause the stream to be closed.
            }
            if (session != null)
            {
                session.disconnect();
            }
        }
    }

    public static HashMap<String, String> fireExecSSHCommandList(String username, String password, String host, List<String> commandList)
    {
        int port = 22;

        Session session = null;

        ChannelExec channel = null;

        HashMap<String, String> responseMap = null;

        boolean loginError = true;

        try
        {
            session = new JSch().getSession(username, host, port);

            if (session != null)
            {
                session.setPassword(password);

                session.setConfig("StrictHostKeyChecking", "no");       //accepts SSH host keys from remote servers and those not in the known host’s list.

                session.connect(10000);

                if (session.isConnected())
                {
                    responseMap = new HashMap<>();

                    loginError = false;

                    BufferedInputStream bufferedInputStream = null;

                    for (String command : commandList)
                    {
                        try
                        {
                            channel = (ChannelExec) session.openChannel("exec");

                            if (channel != null)
                            {
                                channel.setCommand(command);

                                channel.setInputStream(null);

                                channel.setErrStream(System.err);

                                bufferedInputStream = new BufferedInputStream(channel.getInputStream());

                                channel.connect(10000);

                                while (channel.isConnected())
                                {
                                    Thread.sleep(100);
                                }

                                int byt;

                                StringBuilder commandResult = new StringBuilder();

                                while ((byt = bufferedInputStream.read()) != -1)
                                {
                                    commandResult.append((char) byt);
                                }

                                responseMap.put(command, commandResult.toString());
                            }
                        }
                        catch (Exception ex)
                        {
                            ex.printStackTrace();
                        }
                        finally
                        {
                            if (bufferedInputStream != null)
                            {
                                bufferedInputStream.close();
                            }
                            if (channel != null)
                            {
                                channel.disconnect();
                            }
                        }
                    }
                }

            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (session != null)
            {
                session.disconnect();       //Closing the session will in turn cause the stream to be closed.
            }
        }

        if (loginError)
        {
            responseMap = new HashMap<>();

            responseMap.put("error", "userPasswordError");
        }

        return responseMap;
    }

    public static void fireShellSSHCommand(String username, String password, String host, int port, String commands)
    {
        Session session = null;

        Channel channel = null;

        BufferedWriter commandWriter = null;

        BufferedReader responseReader = null;

        try
        {
            session = new JSch().getSession(username, host, port);

            session.setPassword(password);

            session.setConfig("StrictHostKeyChecking", "no");       //accepts SSH host keys from remote servers and those not in the known host’s list.

            session.connect(10000);

            channel = session.openChannel("shell");

            channel.connect(10000);

            commandWriter = new BufferedWriter(new OutputStreamWriter(channel.getOutputStream()));

            responseReader = new BufferedReader(new InputStreamReader(channel.getInputStream()));

            commandWriter.write(commands);

            commandWriter.flush();

            String response;

            do
            {
                response = responseReader.readLine();

                System.out.println(response);
            }
            while (!response.contains("Linux"));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (responseReader != null)
            {
                try
                {
                    responseReader.close();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            if (commandWriter != null)
            {
                try
                {
                    commandWriter.close();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            if (channel != null)
            {
                channel.disconnect();
            }
            if (session != null)
            {
                session.disconnect();
            }
        }
    }

    public static HashMap<String, String> fireGenericShellSSHCommand(String username, String password, String host, int port, List<String> commandList)
    {
        Session session = null;

        Channel channel = null;

        BufferedWriter commandWriter = null;

        BufferedReader responseReader = null;

        BufferedReader errorReader;

        HashMap<String, String> responseMap = new HashMap<>();

        try
        {
            session = new JSch().getSession(username, host, port);

            if (session != null)
            {
                session.setPassword(password);

                session.setConfig("StrictHostKeyChecking", "no");       //accepts SSH host keys from remote servers and those not in the known host’s list.

                session.connect(10 * 1000);

                if (session.isConnected())
                {
                    channel = session.openChannel("shell");

                    if (channel != null)
                    {
                        channel.connect(10 * 1000);

                        if (channel.isConnected())
                        {
                            commandWriter = new BufferedWriter(new OutputStreamWriter(channel.getOutputStream()));

                            responseReader = new BufferedReader(new InputStreamReader(channel.getInputStream()));

                            StringBuilder commandConcat = new StringBuilder();

                            commandList.add("exit");

                            for (String command : commandList)
                            {
                                commandConcat.append(command).append("\n");
                            }

                            commandWriter.write(commandConcat.toString());

                            commandWriter.flush();

                            String response;

                            int commandIndex = 0;

                            StringBuilder commandString;

                            responseMap = new HashMap<>();

                            do
                            {
                                response = responseReader.readLine();
                            }
                            while (!(response.contains(":~$ " + commandList.get(commandIndex)) || response.contains(":~# " + commandList.get(commandIndex))));

                            commandIndex++;

                            do
                            {
                                commandString = new StringBuilder();

                                response = responseReader.readLine();

                                while (!response.contains(commandList.get(commandIndex)))
                                {
                                    commandString.append(response.concat("\n"));

                                    response = responseReader.readLine();
                                }

                                responseMap.put(commandList.get(commandIndex - 1), commandString.toString());

                                commandIndex++;
                            }
                            while (commandIndex != commandList.size());
                        }
                    }
                }
            }
        }
        catch (Exception ex)
        {

//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//            LocalDateTime now = LocalDateTime.now();
//            System.out.println(dtf.format(now));

            ex.printStackTrace();
        }
        finally
        {
            if (responseReader != null)
            {
                try
                {
                    responseReader.close();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            if (commandWriter != null)
            {
                try
                {
                    commandWriter.close();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            if (channel != null)
            {
                channel.disconnect();
            }
            if (session != null)
            {
                session.disconnect();
            }
        }
        return responseMap;
    }

    public static void main(String[] args)
    {
        try
        {
//            SSHDevice.fireExecSSHCommand("pavan", "Mind@123", "10.20.40.139", 22, "ls;");
//
//            SSHDevice.fireShellSSHCommand("pavan", "Mind@123", "10.20.40.139", 22, "ls\nps\nexit\nuname\n");

//            List<String> commandList = new ArrayList<>();
//
//            commandList.add("ls");
//
//            commandList.add("uname");
//
//            commandList.add("ping google.com -c 1");
//
//            commandList.add("exit");
//
////            SSHDevice.fireGenericShellSSHCommand("pavan", "Mind@123", "10.20.40.139", 22, commandList);
////            SSHDevice.fireGenericShellSSHCommand("shekhar", "Mind@123", "10.20.42.142", 22, commandList);
//
//            SSHDevice.fireGenericShellSSHCommand("pavan", "Mind@123", "10.20.40.139", 22, commandList);

            List<String> commandList = new ArrayList<>();

            commandList.add("uptime -p");               //uptime data

            commandList.add("top -bn 2 | grep %Cpu");   //cpu usage. 1st iteration of top -b returns the percentages since boot, we therefore need at least two iterations (-n 2) to get the current percentage. So, as long as you run top once before using it to gather your data you will be fine.

            commandList.add("df -l --total -x tmpfs | grep total");                 //disk usage. lh for local and human readable format. tmpfs intended to appear as a mounted file system, but data is stored in volatile memory

            commandList.add("free");                 //memory usage

            long execCurrentTime = System.nanoTime();
//            System.out.println(SSHDevice.fireExecSSHCommandList("pavan", "Mind@123", "10.20.40.139", commandList));
//            System.out.println(SSHDevice.fireExecSSHCommandList("rahil", "Mind@123", "10.20.40.98", commandList));
            System.out.println(SSHDevice.fireExecSSHCommandList("root", "motadata", "172.16.8.68", commandList));
            long execTime = (System.nanoTime() - execCurrentTime) / 1000000;
            System.out.println(execTime);

            long shellCurrentTime = System.nanoTime();
//            System.out.println(SSHDevice.fireGenericShellSSHCommand("pavan", "Mind@123", "10.20.40.139", 22, commandList));
//            System.out.println(SSHDevice.fireGenericShellSSHCommand("rahil", "Mind@123", "10.20.40.98", 22, commandList));
            System.out.println(SSHDevice.fireGenericShellSSHCommand("root", "motadata", "172.16.8.68", 22, commandList));
            long shellTime = (System.nanoTime() - shellCurrentTime) / 1000000;
            System.out.println(shellTime);

            System.out.println("\n" + (execTime - shellTime));      //+ve means exec greater and -vs means shell greater
            //1127
            //-2168
            //910
            //1172
            //-2920
            //936
            //250
            //1180
            //1176

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
