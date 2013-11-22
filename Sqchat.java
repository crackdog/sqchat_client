
import sqchat.*;

public class Sqchat {

    public static void main(String args[]) {

        int i;
        Encryption crypt = new Encryption("ZmphZ2hlaWF1aGdhZnVlcXdxb3FwaHY=");
        Connection con = new Connection();

        con.setEncryption(crypt);
        System.out.println(con.connect("localhost", 7000));

        con.sendToServer("Hello World!");
        System.out.println(con.recvFromServer());

        con.close();
        

    }
}
