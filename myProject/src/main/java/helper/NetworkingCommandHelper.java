package helper;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NetworkingCommandHelper
{
    public static HashMap<String, Object> getParsedPingDeviceDetail(String ipHostname)
    {
        HashMap<String, Object> parsedPingOutput = null;

        final String rttCommandOutputString = "rtt min/avg/max/mdev = ";

        try
        {
            ArrayList<String> commandList = new ArrayList<>();

            commandList.add("ping");

            commandList.add("-c 3");

            commandList.add(ipHostname);

            ProcessBuilder build = new ProcessBuilder(commandList);

            Process process = build.start();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String pingOutputLine;

            parsedPingOutput = new HashMap<>();

            while ((pingOutputLine = bufferedReader.readLine()) != null)
            {
                if (pingOutputLine.contains("% packet loss"))
                {
                    String[] pingOutputArray = pingOutputLine.split(", ");

                    parsedPingOutput.put("packetsTransmitted", Integer.parseInt(pingOutputArray[0].replaceAll("[^\\d.]", "")));     //java compiles \\ as one \. Now \d means a digit and ^\d means not a digit

                    parsedPingOutput.put("packetsReceived", Integer.parseInt(pingOutputArray[1].replaceAll("[^\\d.]", "")));

                    if (pingOutputArray[2].contains("packet loss"))
                    {
                        parsedPingOutput.put("packetLoss", Float.parseFloat(pingOutputArray[2].replaceAll("[^\\d.]", "")));
                    }
                    else if (pingOutputArray[3].contains("packet loss"))
                    {
                        parsedPingOutput.put("packetLoss", Float.parseFloat(pingOutputArray[3].replaceAll("[^\\d.]", "")));
                    }
                }
                else if (pingOutputLine.contains(rttCommandOutputString))
                {
                    parsedPingOutput.put("rttAvg", Float.parseFloat(pingOutputLine.substring(rttCommandOutputString.length(), pingOutputLine.length()).split("/")[1]));
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return parsedPingOutput;
    }

    public static HashMap<String, String> fireSSHCommands(String username, String password, String host, List<String> commandList)
    {
        int port = 22;

        Session session = null;

        ChannelExec channel = null;

        HashMap<String, String> responseMap = null;

        boolean loginError = true;

        try
        {
            if (username != null && host != null && password != null)
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

                        BufferedReader bufferedReader = null;

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

                                    bufferedReader = new BufferedReader(new InputStreamReader((channel.getInputStream())));

                                    channel.connect(10000);

                                    while (channel.isConnected())
                                    {
                                        Thread.sleep(100);
                                    }

                                    String commandResultLine;

                                    StringBuilder completeCommandResult = new StringBuilder();

                                    while ((commandResultLine = bufferedReader.readLine()) != null)
                                    {
                                        completeCommandResult.append(commandResultLine);
                                    }

                                    responseMap.put(command, completeCommandResult.toString());
                                }
                            }
                            catch (Exception ex)
                            {
                                ex.printStackTrace();
                            }
                            finally
                            {
                                if (bufferedReader != null)
                                {
                                    bufferedReader.close();
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

    public static HashMap<String, Object> getParsedSSHDeviceDetail(String username, String password, String host)
    {
        HashMap<String, Object> parsedDeviceDetailResponse = null;

        try
        {
            List<String> commandList = new ArrayList<>();

            commandList.add("uptime -p");               //uptime data

            commandList.add("top -bn 2 | grep %Cpu");   //cpu usage. 1st iteration of top -b returns the percentages since boot, we therefore need at least two iterations (-n 2) to get the current percentage. So, as long as you run top once before using it to gather your data you will be fine.

            commandList.add("df -l --total -x tmpfs | grep total");                 //disk usage. lh for local and human readable format. tmpfs intended to appear as a mounted file system, but data is stored in volatile memory

            commandList.add("free");                 //memory usage

            HashMap<String, String> commandOutput = NetworkingCommandHelper.fireSSHCommands(username, password, host, commandList);

            if (commandOutput != null && !commandOutput.isEmpty())
            {
                parsedDeviceDetailResponse = new HashMap<>();

                if (commandOutput.get("error") == null)
                {
                    String splitedOutput[];

                    String commandOutputString;

                    try
                    {
                        commandOutputString = commandOutput.get(commandList.get(0));

                        if (commandOutputString != null && commandOutputString.contains("up "))
                        {
                            parsedDeviceDetailResponse.put("upTime", commandOutputString.substring(3, commandOutputString.length()).trim());
                        }
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }

                    try
                    {
                        commandOutputString = commandOutput.get(commandList.get(1));

                        if (commandOutputString != null && commandOutputString.contains("Cpu"))
                        {
                            splitedOutput = commandOutputString.substring(commandOutputString.lastIndexOf("Cpu"), commandOutputString.length()).split(",");

                            parsedDeviceDetailResponse.put("idleCpuPercent", Float.parseFloat(splitedOutput[3].substring(0, splitedOutput[3].indexOf(" id"))));
                        }
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }

                    DecimalFormat decimalFormat = new DecimalFormat("#.##");

                    try
                    {
                        commandOutputString = commandOutput.get(commandList.get(2));

                        if (commandOutputString != null && commandOutputString.contains("total"))
                        {
                            splitedOutput = commandOutputString.substring(commandOutputString.indexOf("total"), commandOutputString.length()).split("\\s+");

                            parsedDeviceDetailResponse.put("totalDiskGB", Float.parseFloat(decimalFormat.format(Float.parseFloat(splitedOutput[1]) / (1024 * 1024))));

                            parsedDeviceDetailResponse.put("usedDiskPercent", Float.parseFloat(splitedOutput[4].replaceAll("[^\\d.]", "")));
                        }
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }

                    try
                    {
                        commandOutputString = commandOutput.get(commandList.get(3));

                        if (commandOutputString != null && commandOutputString.contains("Mem:"))
                        {
                            splitedOutput = commandOutputString.substring(commandOutputString.indexOf("Mem:"), commandOutputString.length()).split("\\s+");

                            parsedDeviceDetailResponse.put("totalMemoryGB", Float.parseFloat(decimalFormat.format(Float.parseFloat(splitedOutput[1]) / (1024 * 1024))));

                            parsedDeviceDetailResponse.put("usedMemoryGB", Float.parseFloat(decimalFormat.format((Float.parseFloat(splitedOutput[2])) / (1024 * 1024))));
                        }
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
                else
                {
                    parsedDeviceDetailResponse.put("error", commandOutput.get("error"));
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return parsedDeviceDetailResponse;
    }
}

