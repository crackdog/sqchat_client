import sqchat.*;

public class Sqchat {

    public static void main(String args[]) {
        System.out.println("Hello World!");
        Encryption crypt = new Encryption("R2pjY2AvWGB9Y2su");
        String test = crypt.encryptMsg("Hello World!");
        System.out.println(test);
        System.out.println(crypt.decryptMsg(test));
        
        test = "Hello World!!!";
        System.out.println(crypt.base64encode(test.getBytes()));
        System.out.println(crypt.encode(test));
        //System.out.println(crypt.decryptMsg(test));
        //System.out.println(crypt.decode(test));
    }
}
