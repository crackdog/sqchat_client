package connection;

import java.io.*;
import java.net.Socket;

/**
 * This class represents the connection to the server.
 * @author Florian Kahllund
 */
public class Connection {

    private final int stdPort = 6666;
    private String serverIp;
    private int serverPort;
    private Socket serverSocket;
    private Encryption crypt;

    /**
     * standart constructor
     */
    public Connection() {
        serverIp = "";
        serverPort = 0;
        serverSocket = null;
        crypt = null;
    }

    /**
     * 
     * @param ip the ip-adress of the server
     * @return Returnt the connection status.
     */
    public boolean connect(String ip) {
        return connect(ip, stdPort);
    }

    /**
     * 
     * @param ip the ip-adress of the server
     * @param port the port of the server
     * @return Returns the connection status.
     */
    public boolean connect(String ip, int port) {
        serverIp = ip;
        serverPort = port;

        try {
            serverSocket = new Socket(serverIp, serverPort);
        } catch (IOException e) {
            System.err.println("e: " + e);
            return false;
        }

        return true;
    }

    /**
     * 
     * @return Returns the connection status.
     */
    public boolean isConnected() {
        return serverSocket != null;
    }

    /**
     * 
     * @return Returns the encryption status.
     */
    public boolean isEncrypted() {
        return crypt != null;
    }

    /**
     * Close the socket connection.
     */
    public void close() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Sets the Encryption.
     * @param c the Encryption to use
     */
    public void setEncryption(Encryption c) {
        this.crypt = c;
    }

    /**
     * Sends a message to the server
     * @param msg Themessage to be sent.
     * @return Returns if the operation was successfull.
     */
    public boolean sendToServer(String msg) {
        if (crypt != null) {
            msg = crypt.encryptMsg(msg);
        }

        System.out.println("send encrypted msg: '" + msg + "'");

        if (!msg.endsWith("\n")) {
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

    /**
     * <p> Receives a message from the server. </p>
     * <p> This method is blocking. </p>
     * @return Returns the received message as a string.
     */
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

                if (crypt != null) {
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
