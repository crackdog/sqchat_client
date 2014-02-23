package sqchat;

import connection.*;
import gui.IConversationFrame;
import gui.IContactListFrame;
import java.util.ArrayList;

/*
 * Chat implements the Conatct List and 
 */
public class Chat implements IContactList {
    
    private ArrayList<Contact> contacts;
    private ArrayList<Conversation> conversations;
    private IContactListFrame clFrame;
    private Contact user;
    private Connection con;
    private Encryption crypt;
    
    public Chat(IContactListFrame clf) {
        contacts = new ArrayList<>();
        conversations = new ArrayList<>();
        user = new Contact();
        
        clFrame = clf;
        
        con = new Connection();
        crypt = new Encryption();
    }

    @Override
    public void openChatWindow(IContact contact) {
        Conversation newC = new Conversation(user, contact, this);
        conversations.add(newC);
        newC.setFrame(clFrame.createNewConversationFrame(newC.getFrameName()));
    }

    @Override
    public IContact[] getContactArray() {
        return (IContact[]) contacts.toArray();
    }
    
    public boolean sendMsgToServer(String msg) {
        return false;
    }
}
