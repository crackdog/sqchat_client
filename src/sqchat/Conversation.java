package sqchat;

import gui.IConversationFrame;

/*
 * Todo: printAllMsg() and save every msg in an ArrayList...
 */

public class Conversation implements IConversation {
    
    private Contact user, contact;
    private Chat chat;
    private IConversationFrame frame;
    
    public Conversation(IContact user, IContact contact, Chat chat) {
        this.user = (Contact) user;
        this.contact = (Contact) contact;
        this.chat = chat;
    }
    
    public String getFrameName() {
        return contact.getName();
    }
    
    public void setFrame(IConversationFrame frame) {
        this.frame = frame;
        this.frame.setConversation((IConversation) this);
    }

    @Override
    public void inputMsg(String msg) {
        //create full message
        String m;
        
        m = "sendtextmessage targetmode=1 target=";
        m += contact.getClientId();
        m += " msg=";
        m += this.escapeSpaces(msg);
        m += "\n";
        
        //send message
        if(chat.sendMsgToServer(m)) {
            //no error
        } else {
            //error
        }
    }
    
    public void outputMsg(String fullMsg) {
        //recreate text message
        String msg;
        String tmp[];
        
        tmp = fullMsg.split("msg=");
        msg = this.reEscapeSpaces(tmp[tmp.length-1]);
        
        //print message in conversation frame
        if(frame != null) {
            frame.printMsg(msg);
        }
    }
    
    private String escapeSpaces(String s) {
        return s.replaceAll(" ", "\\\\s");
    }
    
    private String reEscapeSpaces(String s) {
        return s.replaceAll("\\\\s", " ");
    }

    @Override
    public void closeConversation() {
        frame.closeFrame();
    }
    
}
