package sqchat;

import java.util.*;

public class Client {

    private Contact identity;
    private ArrayList<Contact> contacts;

    public Client() {
        identity = new Contact();
        contacts = new ArrayList<Contact>();
        
        contacts.add(new Contact());
        contacts.add(new Contact());
    }
    
    public void printContacts() {
        for(Contact x: contacts) {
            System.out.println(x.print());
        }
    }

    public void importClientList(String msg) {
        /* import a message with a clientlist and convert it into an array list
         * of contact's and store it into contacs.
         */
    }
}
