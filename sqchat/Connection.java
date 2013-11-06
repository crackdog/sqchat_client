package sqchat;

import java.net.Socket;

public class Connection {

    private Socket server;
    Encryption crypt = new Encryption();

    public Connection() {
        System.out.println("===Connection constructor===");
    }

    public boolean sendToServer(String msg) {
        msg = crypt.encryptMsg(msg);
        
        System.out.println("send encrypted msg: '" + msg + "'");
        
        return false;
    }

    public String recvFromServer() {
        String msg = "";
        
        msg = crypt.decryptMsg(msg);
        return msg;
    }
}
