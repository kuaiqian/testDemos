package jdk.study;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHHelper {
    public static void main(String[] args) throws IOException, JSchException {
        // TODO Auto-generated method stub
        String host = "192.168.6.29";
        int port = 22;
        String user = "root";
        String password = "mas99bill";
        String command = "ps -ef|grep -v grep|grep balance|grep 7665";
        String res = exeCommand(host, port, user, password, command);
        System.out.println(res);
    }

    public static String exeCommand(String host, int port, String user, String password, String command)
            throws JSchException, IOException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, port);
        session.setConfig("StrictHostKeyChecking", "no");
        // java.util.Properties config = new java.util.Properties();
        // config.put("StrictHostKeyChecking", "no");
        session.setPassword(password);
        session.connect();
        ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
        InputStream in = channelExec.getInputStream();
        channelExec.setCommand(command);
        channelExec.setErrStream(System.err);
        channelExec.connect();
        String out = IOUtils.toString(in, "UTF-8");
        channelExec.disconnect();
        session.disconnect();
        return out;
    }
}
