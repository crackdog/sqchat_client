package sqchat;

/**
 * 
 * @author florian
 */
public interface IContactList {
    
    /**
     * open a chat window for a given contact
     * @param c the contact for whom the chat window shourld be opened
     */
    void openChatWindow(IContact c);
    /**
     * get the array of contacts
     * @return an array of contacts
     */
    IContact[] getContactArray();
    
}
