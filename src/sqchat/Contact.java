package sqchat;

/*
 * holds the information of a Contact
 */
public class Contact implements IContact {
    
    private String name;
    private String uId;
    private int clientId;
    private int channelId;
    private int clientDbId;
    
    public Contact() {
        
    }

    @Override
    public int getChannelId() {
        return channelId;
    }

    @Override
    public int getClientDbId() {
        return clientDbId;
    }

    @Override
    public int getClientId() {
        return clientId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getuId() {
        return uId;
    }
    
    
}
