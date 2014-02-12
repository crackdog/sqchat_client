package sqchat;

import gui.*;

public class Chat {
    
    public static final int Enter = 1;
    
    public Chat() {
        
    }
    
    public void perform_action(int action) {
        switch(action) {
            case Chat.Enter: 
                System.out.println("perform action: Enter");
                break;
            default:
                break;
        }
    }
    
    public void setOutput() { //the output needs an interface...
        
    }
}