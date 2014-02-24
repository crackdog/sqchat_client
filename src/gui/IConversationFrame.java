package gui;

public interface IConversationFrame {
    
    void setConversation(sqchat.IConversation c);
    void printMsg(sqchat.IMessage msg);
    void printAllMsg(java.util.ArrayList<sqchat.IMessage> msg);
    void closeFrame();
    
}
