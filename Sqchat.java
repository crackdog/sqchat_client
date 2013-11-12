
import sqchat.*;

public class Sqchat {

    public static void main(String args[]) {

        int i;
        Encryption crypt = new Encryption("ZmphZ2hlaWF1aGdhZnVlcXdxb3FwaHY=");
        Connection con = new Connection();

        con.sendToServer("Hello World!");
        con.setEncryption(crypt);
        con.sendToServer("Hello World!");

        for (i = 0; i < args.length; i++) {
            con.sendToServer(args[i]);
        }

        /*String test = crypt.encryptMsg("Hello World!");
        System.out.println(test);
        System.out.println(crypt.decryptMsg(test));*/
    }
}
