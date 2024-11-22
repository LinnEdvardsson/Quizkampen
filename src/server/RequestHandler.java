package server;
import Client.ClientRequest;

import java.io.IOException;

//SERVER
public class RequestHandler {

    public static void handleRequest(ClientRequest request, ClientConnection client) throws IOException {
        switch (request.getRequestType()){
            case CONNECT_REQUEST -> {
                System.out.println("Wish to connect to server");
                Server.sendResponse(new ServerResponse(ResponseType.CONNECTION_ESTABLISHED), client);
            }
            case DISCONNECT -> {
                System.out.println("Wish to disconnect");
                Server.sendResponse(new ServerResponse(ResponseType.CONNECTION_TERMINATED), client);
            }

//            case FIND_OPPONENT_REQUEST -> {
//                System.out.println("Want to find an opponent");
//                Server.addClientToQueue(client);
//            }
            case START_GAME -> {
                System.out.println("Wish to start game");
                Server.addToGame(client);
                if (Server.playersInQueue() >= 2) {
                    Server.notifyGameStart();
                } else {
                    Server.sendResponse(new ServerResponse(ResponseType.PLAYER_QUEUED), client); //ändra GUI för klienten
                }

            }
            case CATEGORY_TYPE_REQUEST -> {
                System.out.println("Want to choose category type request");
                Server.sendResponse(new ServerResponse(ResponseType.CHOOSE_CATEGORY), client);

            }
        }
    }
}
