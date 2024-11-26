package Client;

import QuizGame.eCategoryType;
import server.ClientConnection;

import java.io.Serializable;

public class ClientRequest implements Serializable {

    RequestType type;
    String username;
    eCategoryType category;

    public ClientRequest(RequestType type, String username) {
        this.type = type;
        this.username = username;
    }

    public ClientRequest(RequestType type, eCategoryType category){
        this.type = type;
        this.category = category;
    }

    public RequestType getRequestType(){
        return type;
    }

    public String getUsername() {
        return username;
    }
}

