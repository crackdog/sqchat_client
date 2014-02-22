package sqchat;

public interface IConversation {

    void inputMsg(String msg);  //to send a message
    void closeConversation();   //when the chat window is closed
    
    
}
