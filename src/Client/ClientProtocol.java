package Client;
import server.ResponseType;
import server.ResponseType;

import server.*;

public class ClientProtocol {

    public ClientProtocol(ServerResponse response, Client client){
        switch (response.getResponseType()){
            case CONNECTION_ESTABLISHED -> {
                client.frame.createLoginFrame();

            }
        }
    }
}
