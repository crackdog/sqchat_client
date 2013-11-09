import sqchat.*;

public class Sqchat {

    public static void main(String args[]) {
        //System.out.println("Hello World!");
        Encryption crypt = new Encryption("ZmphZ2hlaWF1aGdhZnVlcXdxb3FwaHY=");
        //System.out.println(crypt.base64encodeMsg("fjagheiauhgafueqwqoqphv"));
        String test = crypt.encryptMsg("Hello World!");
        //System.out.println(test);
        //System.out.println(crypt.decryptMsg(test));
        
        test = "Hello World!!!!!";        
        System.out.println(test);
        test = crypt.encryptMsg(test);
        System.out.println(test);
        System.out.println(crypt.decryptMsg(test));
    }
}
