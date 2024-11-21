package server;


import java.io.Serializable;

public class ServerResponse implements Serializable {

    ResponseType type;

    public ServerResponse(ResponseType type){
        this.type = type;
    }

    public ResponseType getResponseType(){
        return type;
    }


}
