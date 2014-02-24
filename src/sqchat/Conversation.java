package sqchat;

import gui.IConversationFrame;
import java.util.ArrayList;

/*
 * Todo: printAllMsg() and save every msg in an ArrayList...
 */

public class Conversation implements IConversation {
    
    private Contact user, contact;
    private Chat chat;
    private IConversationFrame frame;
    ArrayList<IMessage> msgList;
    
    public Conversation(IContact user, IContact contact, Chat chat) {
        this.user = (Contact) user;
        this.contact = (Contact) contact;
        this.chat = chat;
        this.msgList = new ArrayList<>();
    }
    
    public String getFrameName() {
        return contact.getName();
    }
    
    public void setFrame(IConversationFrame frame) {
        this.frame = frame;
        this.frame.setConversation((IConversation) this);
    }

    @Override
    public void inputMsg(String msg) { //message that comes from user...
        //create full message
        String mtmp;
        Message m;
        
        mtmp = "sendtextmessage targetmode=1 target=";
        mtmp += contact.getClientId();
        mtmp += " msg=";
        mtmp += this.escapeSpaces(msg);
        mtmp += "\n";
        
        m = new Message(mtmp, true);
        
        //store message to msgList
        msgList.add((IMessage) m);
        
        //send message
        if(chat.sendMsgToServer(mtmp)) {
            //no error
        } else {
            //error
        }
    }
    
    public void outputMsg(String fullMsg) {
        //recreate text message
        String msg;
        String tmp[];
        Message m;
        
        tmp = fullMsg.split("msg=");
        msg = this.reEscapeSpaces(tmp[tmp.length-1]);
        
        m = new Message(msg, false);
        
        //store message to msgList
        msgList.add((IMessage) m);
        
        //print message in conversation frame
        if(frame != null) {
            frame.printMsg(m);
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
