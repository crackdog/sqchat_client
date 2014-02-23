package sqchat;

public class Conversation implements IConversation {
    
    private Contact contact;
    
    public Conversation(IContact contact) {
        this.contact = (Contact) contact;
    }

    @Override
    public void inputMsg(String msg) {
        
    }

    @Override
    public void closeConversation() {
        
    }
    
}
