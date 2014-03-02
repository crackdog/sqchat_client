package sqchat;

import java.util.Objects;

/*
 * holds the information of a Contact
 */
public class Contact implements IContact {
    
    private String name;
    private String uId;
    private int clientId;
    private int channelId;
    private int clientDbId;

    public Contact(String nick, String uid, int clid, int cid, int cldbid) {
        this.name = nick;
        this.uId = uid;
        this.clientId = clid;
        this.channelId = cid;
        this.clientDbId = cldbid;
    }
    
    public Contact(String clientlistLine) {
        String values[], tmp[];
        
        values = clientlistLine.split(" ");
        
        for(int i = 0; i < values.length; i++) {
            if(values[i].contains("client_nickname")) {
                tmp = values[i].split("=");
                name = tmp[tmp.length-1];
            } else if(values[i].contains("clid")) {
                tmp = values[i].split("=");
                clientId = Integer.parseInt(tmp[tmp.length-1]);
            } else if(values[i].contains("cid")) {
                tmp = values[i].split("=");
                channelId = Integer.parseInt(tmp[tmp.length-1]);
            } else if(values[i].contains("client_database_id")) {
                tmp = values[i].split("=");
                clientDbId = Integer.parseInt(tmp[tmp.length-1]);
            } else if(values[i].contains("cluid")) {
                tmp = values[i].split("=");
                uId = tmp[tmp.length-1];
            }
        }
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
    
    public boolean equals(Contact c) {
        return this.uId.compareTo(c.getuId()) == 0;
    }

    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.uId);
        return hash;
    }
    
    public boolean equals(Object o) {
        if(o instanceof Contact) {
            return this.equals((Contact) o);
        } else {
            return false;
        }
    }
}
