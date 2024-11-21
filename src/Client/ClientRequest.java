package Client;

import java.io.Serializable;

public class ClientRequest implements Serializable {

    RequestType type;
    String username;

    public ClientRequest(RequestType type, String username){
        this.type = type;
        this.username = username;
    }

    public RequestType getRequestType(){
        return type;
    }

    public String getUsername() {
        return username;
    }
}

//
