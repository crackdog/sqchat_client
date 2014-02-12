package sqchat;

import gui.*;

public class Sqchat_client {

    public static void main(String[] args) {
        
        Encryption crypt = new Encryption("ZmphZ2hlaWF1aGdhZnVlcXdxb3FwaHY=");
        Connection con = new Connection();

        con.setEncryption(crypt);
        System.out.println(con.connect("localhost", 7000));

        con.sendToServer("Hello World!");
        System.out.println(con.recvFromServer());

        con.close();
        
        Frame test = new Frame();
        test.init();
        Client testclient = new Client();
        
        testclient.printContacts();
        
        //chat class test
        Chat testchat = new Chat();
        testchat.perform_action(Chat.Enter);
    }
}
