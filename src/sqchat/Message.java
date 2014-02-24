package sqchat;

public class Message implements IMessage {

    private String msg;
    private boolean user;

    public Message(String msg, boolean user) {
        this.msg = msg;
        this.user = user;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public boolean isUser() {
        return user;
    }
    
    public static IMessage createMsg(String msg, boolean user) {
        return (IMessage) new Message(msg, user);
    }
}