package sqchat;

import connection.*;
import gui.IConversationFrame;
import gui.IContactListFrame;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * Chat implements the Conatct List and 
 */
public class Chat implements IContactList, Runnable {
    
    private ArrayList<Contact> contacts;
    private ArrayList<Conversation> conversations;
    private IContactListFrame clFrame;
    private Connection con;
    private boolean running;
    
    public Chat(IContactListFrame clf) {
        contacts = new ArrayList<>();
        conversations = new ArrayList<>();
        
        clFrame = clf;
        
        con = new Connection();
    }
    
    public void init() {
        String key;
        key = "Encryption Key";
        
        con.connect("localhost");
        con.setEncryption(new Encryption(key));
        
        running = true;
        this.run();
    }

    @Override
    public void openChatWindow(IContact contact) {
        Conversation newC = new Conversation(contact, this);
        conversations.add(newC);
        newC.setFrame(clFrame.createNewConversationFrame((IConversationFrame) newC));
    }

    @Override
    public IContact[] getContactArray() {
        return (IContact[]) contacts.toArray();
    }
    
    public boolean sendMsgToServer(String msg) {
        return con.sendToServer(msg);
    }
    
    private void createContacts(String clientlist) {
        String clients[];
        ArrayList<Contact> offline = new ArrayList<>();
        Contact tmp;
        
        clients = clientlist.split("|");
        
        //check for every contact in contacts if it is still in clients and 
        //check if every contact in clients is in contacts
        offline = contacts;
        contacts = new ArrayList<>();
        
        for(int i = 0; i < clients.length; i++) {
            tmp = new Contact(clients[i]);
            contacts.add(tmp);
        }
        
        offline.removeAll(contacts);
        
        if(!offline.isEmpty()) {
            //there are offline contacts...
            
        }
        
    }

    @Override
    public void run() {
        while(running) {
            String msg, uid;
            Conversation conversation;
            Iterator iter;
            
            msg = con.recvFromServer();
            
            //search for the correct conversation...
            //getting uid...
            //comparing uid with converstaions...
            uid = "";
            iter = conversations.iterator();
            while(iter.hasNext()) {
                conversation = (Conversation) iter.next();
                if(conversation.getContact().getuId().compareTo(uid) == 0) {
                    break;
                }
            }
            
            //if not there create...
            conversation = new Conversation(null, this);
            
            conversation.outputMsg(msg);
        }
    }
}
