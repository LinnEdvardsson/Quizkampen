package server;

public class ServerResponse {

    ResponseType type;

    public ServerResponse(ResponseType type){
        this.type = type;
    }

    public ResponseType getResponseType(){
        return type;
    }


}
