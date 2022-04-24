package service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SSHTask extends RecursiveTask<HashMap<String, String>>
{
    private String username;

    private String password;

    private String host;

    private List<String> commandList;

    public SSHTask(String username, String password, String host, List<String> commandList)
    {
        this.username = username;

        this.password = password;

        this.host = host;

        this.commandList = commandList;
    }

    @Override
    protected HashMap<String, String> compute()
    {
        return DiscoveryService.fireGenericSSHCommand(username, password, host, commandList);
    }
}
