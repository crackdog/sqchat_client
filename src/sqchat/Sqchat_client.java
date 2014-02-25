package sqchat;

import connection.Encryption;
import connection.Connection;
//import gui.*;

public class Sqchat_client {

    public static void main(String[] args) {
        
        Encryption crypt = new Encryption("ZmphZ2hlaWF1aGdhZnVlcXdxb3FwaHY=");
        Connection con = new Connection();

        con.setEncryption(crypt);
        System.out.println(con.connect("localhost", 7000));

        con.sendToServer("Hello World!");
        System.out.println(con.recvFromServer());

        con.close();
        
        Conversation c = new Conversation(null, null);
    }
}
