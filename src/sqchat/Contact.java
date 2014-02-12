package sqchat;

public class Contact {
    
    private String name, uid;
    private int clid;

    public Contact() {
        name = "";
        uid = "";
        clid = 0;
    }

    public void importContact(String contactInfo) {
        name = "...";
        uid = "...";
        clid = 0;
    }

    public String getName() {
        return this.name;
    }

    public String getUID() {
        return this.uid;
    }

    public boolean equals(Contact otherContact) {
        return this.uid.compareTo(otherContact.getUID()) == 0;
    }
    
    public String print() {
        String tmp = "";

        tmp += "name: " + name;
        tmp += "uid: " + uid;

        return tmp;
    }
}
