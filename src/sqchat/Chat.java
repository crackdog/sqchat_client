package sqchat;

import java.util.ArrayList;

/*
 * Chat implements the Conatct List and 
 */
public class Chat implements IContactList {
    
    private ArrayList<Contact> contacts;
    private ArrayList<Conversation> conversations;
    
    public Chat() {
        contacts = new ArrayList<>();
        conversations = new ArrayList<>();
    }

    @Override
    public void openChatWindow(IContact c) {
        conversations.add(new Conversation(c));
    }

    @Override
    public IContact[] getContactArray() {
        return (IContact[]) contacts.toArray();
    }
    
}
