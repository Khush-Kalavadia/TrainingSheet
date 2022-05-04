package helper;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

            commandList.add("-w 3");

            commandList.add(ipHostname);

            ProcessBuilder build = new ProcessBuilder(commandList);

            Process process = build.start();

            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String pingOutputLine;

            parsedPingOutput = new HashMap<>();

            while ((pingOutputLine = input.readLine()) != null)
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
                    parsedPingOutput.put("rttMin", Float.parseFloat(pingOutputLine.substring(rttCommandOutputString.length(), pingOutputLine.indexOf("/", rttCommandOutputString.length()))));
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

        Channel channel = null;

        BufferedWriter commandWriter = null;

        BufferedReader responseReader = null;

        HashMap<String, String> responseMap = null;

        boolean error = true;

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
                            while (!response.contains(":~$ " + commandList.get(commandIndex)));

                            commandIndex++;

                            do
                            {
                                commandString = new StringBuilder();

                                response = responseReader.readLine();

                                while (!response.contains(":~$ " + commandList.get(commandIndex)))
                                {
                                    commandString.append(response.concat("\n"));

                                    response = responseReader.readLine();
                                }

                                responseMap.put(commandList.get(commandIndex - 1), commandString.toString());

                                commandIndex++;
                            }
                            while (commandIndex != commandList.size());

                            error = false;
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

        if (error)
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

            commandList.add("df -lh --total -x tmpfs | grep total");                 //disk usage. lh for local and human readable format. tmpfs intended to appear as a mounted file system, but data is stored in volatile memory

            commandList.add("free -h");                 //memory usage

            HashMap<String, String> commandOutput = NetworkingCommandHelper.fireSSHCommands(username, password, host, commandList);

            if (commandOutput != null)
            {
                parsedDeviceDetailResponse = new HashMap<>();

                if (commandOutput.get("error") == null)
                {
                    System.out.println(commandOutput);

                    String commandOutputString = commandOutput.get(commandList.get(0));

                    String splitedOutput[];

                    if (commandOutputString != null && commandOutputString.contains("up "))
                    {
                        parsedDeviceDetailResponse.put("upTime", commandOutputString.substring(3, commandOutputString.length()).trim());
                    }

                    commandOutputString = commandOutput.get(commandList.get(1));

                    if (commandOutputString != null && commandOutputString.contains("Cpu"))
                    {
                        splitedOutput = commandOutputString.substring(commandOutputString.lastIndexOf("Cpu"), commandOutputString.length()).split(", ");

                        parsedDeviceDetailResponse.put("idleCpuPercent", Float.parseFloat(splitedOutput[3].substring(0, splitedOutput[3].indexOf(" id"))));
                    }

                    commandOutputString = commandOutput.get(commandList.get(2));

                    if (commandOutputString != null && commandOutputString.contains("total"))
                    {
                        splitedOutput = commandOutputString.substring(commandOutputString.indexOf("total"), commandOutputString.length()).split("\\s+");

                        parsedDeviceDetailResponse.put("totalDiskGB", Float.parseFloat(splitedOutput[1].replaceAll("[^\\d.]", "")));

                        parsedDeviceDetailResponse.put("usedDiskPercent", Float.parseFloat(splitedOutput[4].replaceAll("[^\\d.]", "")));
                    }

                    commandOutputString = commandOutput.get(commandList.get(3));

                    if (commandOutputString != null && commandOutputString.contains("Mem:"))
                    {
                        splitedOutput = commandOutputString.substring(commandOutputString.indexOf("Mem:"), commandOutputString.length()).split("\\s+");

                        parsedDeviceDetailResponse.put("totalMemoryGB", Float.parseFloat(splitedOutput[1].replaceAll("[^\\d.]", "")));

                        parsedDeviceDetailResponse.put("usedMemoryGB", Float.parseFloat(splitedOutput[2].replaceAll("[^\\d.]", "")));
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

    public static void main(String[] args)
    {
        //using pingoutput
//        HashMap<String, Object> pingOutput = NetworkingCommandHelper.getParsedPingDeviceDetail("10.20.40.139");
//
//        if (pingOutput != null)
//        {
//            System.out.println(pingOutput.get("packetsTransmitted"));
//
//            System.out.println(pingOutput.get("packetsReceived"));
//
//            System.out.println(pingOutput.get("packetLoss"));
//
//            if (pingOutput.get("rttMin") != null)
//            {
//                System.out.println(pingOutput.get("rttMin"));
//            }
//        }

        //using sshoutput
//        HashMap<String, Object> sshOutput = NetworkingCommandHelper.getParsedSSHDeviceDetail("rahil", "Mind@123", "10.20.40.98");
//        HashMap<String, Object> sshOutput = NetworkingCommandHelper.getParsedSSHDeviceDetail("pavan", "Mind@123", "10.20.40.139");
//
//        if (sshOutput != null)
//        {
//            if (sshOutput.get("error") == null)
//            {
//                System.out.println(sshOutput);
//            }
//        }

        System.out.println(NetworkingCommandHelper.getParsedPingDeviceDetail("10.20.42.142"));
    }
}
