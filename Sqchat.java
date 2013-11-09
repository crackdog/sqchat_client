import sqchat.*;

public class Sqchat {

    public static void main(String args[]) {
        
        int i;
        //System.out.println("Hello World!");
        Encryption crypt = new Encryption("ZmphZ2hlaWF1aGdhZnVlcXdxb3FwaHY=");
        //System.out.println(crypt.base64encodeMsg("fjagheiauhgafueqwqoqphv"));
        String test = crypt.encryptMsg("Hello World!");
        //System.out.println(test);
        //System.out.println(crypt.decryptMsg(test));
        
        test = "Hello World!";
        System.out.println(test);
        test = crypt.encryptMsg(test);
        System.out.println(test);
        System.out.println(crypt.decryptMsg(test));
        
        test = ".";
        for(i = 0; i < 100; i++) {
            if(test.compareToIgnoreCase(crypt.decryptMsg(crypt.encryptMsg(test))) != 0) {
                System.err.println("fail at: " + test);
            }
            test += String.valueOf((int) (Math.random() * 10));
        }
        if(i == 100) {
            System.out.println("+++");
            System.out.println(test);
            System.out.println(crypt.encryptMsg(test));
        }
    }
}
