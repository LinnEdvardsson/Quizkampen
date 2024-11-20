package Client;

public class ClientRequest {

    RequestType type;
    String username;

    public ClientRequest(RequestType type, String username){
        this.type = type;
        this.username = username;
    }

    public RequestType getRequestType(){
        return type;
    }
}

//
