package sqchat;

import java.io.*;
import java.net.Socket;

public class Connection {

    private final int stdPort = 6666;
    private String serverIp;
    private int serverPort;
    private Socket serverSocket;
    private Encryption crypt;

    public Connection() {
    }

    public boolean connect(String ip) {
        return connect(ip, stdPort);
    }

    public boolean connect(String ip, int port) {
        serverIp = ip;
        serverPort = port;

        try {
            serverSocket = new Socket(ip, port);
        } catch (IOException e) {
            System.err.println(e);
            return false;
        }

        return true;
    }

    public boolean isConnected() {
        return serverSocket != null;
    }

    public boolean isEncrypted() {
        return crypt != null;
    }

    public void close() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    public void setEncryption(Encryption c) {
        this.crypt = c;
    }

    public boolean sendToServer(String msg) {
        if (crypt != null) {
            msg = crypt.encryptMsg(msg);
        }
        
        System.out.println("send encrypted msg: '" + msg + "'");
        
        if(!msg.endsWith("\n")) {
            msg += "\n";
        }

        if (serverSocket != null) {
            try {
                PrintWriter serverWriter =
                        new PrintWriter(
                        new OutputStreamWriter(
                        serverSocket.getOutputStream()));

                serverWriter.print(msg);
                serverWriter.flush();
            } catch (IOException e) {
                System.err.println("no connection to server...");
            }
        }

        return false;
    }

    public String recvFromServer() { //this function is blocking...
        if (serverSocket != null) {
            try {
                BufferedReader bufferedReader =
                        new BufferedReader(
                        new InputStreamReader(
                        serverSocket.getInputStream()));

                char[] msgbuffer = new char[4096];
                int msglen;

                msglen = bufferedReader.read(msgbuffer, 0, 4096);

                String msg = new String(msgbuffer, 0, msglen);

                if(crypt != null) {
                    msg = crypt.decryptMsg(msg);
                }
                
                return msg;
            } catch (IOException e) {
                System.err.println("no connection to server...");
                return null;
            }
        } else {
            return null;
        }
    }
}
