package sqchat;

import sqchat.*;

public class Contact {
    
    private String name, uid;

    public Contact() {
        name = "";
        uid = "";
    }

    public void importContact(String contactInfo) {
        name = "...";
        uid = "...";
    }
    
    public String print() {
        String tmp = "";

        tmp += "name: " + name;
        tmp += "uid: " + uid;

        return tmp;
    }
}

