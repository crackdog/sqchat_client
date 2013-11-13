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

    public void setEncryption(Encryption c) {
        this.crypt = c;
    }

    public boolean sendToServer(String msg) {
        if (crypt != null) {
            msg = crypt.encryptMsg(msg);
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

        System.out.println("send encrypted msg: '" + msg + "'");

        return false;
    }

    public String recvFromServer() {
        String msg = "";

        msg = crypt.decryptMsg(msg);
        return msg;
    }
}
