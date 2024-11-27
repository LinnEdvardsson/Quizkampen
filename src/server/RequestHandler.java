package server;
import Client.ClientRequest;

import java.io.IOException;

//SERVER hanterar klientens requests
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
                    ClientConnection playerOne = Server.onGoingGame.removeFirst();
                    ClientConnection playerTwo = Server.onGoingGame.removeFirst();
                    Instance instance = new Instance(playerOne, playerTwo);
                    Server.notifyInstance(instance);

                } else {
                    Server.sendResponse(new ServerResponse(ResponseType.PLAYER_QUEUED), client);
                }

            }
            case CATEGORY_TYPE_REQUEST -> {
                System.out.println("Want to choose category type request");
                Server.sendResponse(new ServerResponse(ResponseType.CHOOSEN_CATEGORY), client);

            }

            case PLAYER1_PLAYING -> {
                Server.sendResponse(new ServerResponse(ResponseType.GET_QUESTION), client);
            }

        }
    }
}
