import sqchat.*;

public class Sqchat {

    public static void main(String args[]) {
        System.out.println("Hello World!");
        Connection test = new Connection();
        test.sendToServer("Hello World!");
    }
}
