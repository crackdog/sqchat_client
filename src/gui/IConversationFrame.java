package gui;

public interface IConversationFrame {
    
    void setConversation(sqchat.IConversation c);
    void printMsg(String msg);
    void printAllMsg(java.util.ArrayList<String> msg);
    void closeFrame();
    
}
