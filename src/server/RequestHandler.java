package server;
import Client.ClientRequest;
import QuizGame.Questions;

import java.io.IOException;
import java.util.List;

//SERVER hanterar klientens requests
public class RequestHandler {


    public static void handleRequest(ClientRequest request, ClientConnection client) throws IOException {

        switch (request.getRequestType()){
            case CONNECT_REQUEST -> { // om detta skickar med username till servern
                System.out.println("Wish to connect to server");
                client.username = request.getUsername();
                Server.sendResponse(new ServerResponse(ResponseType.CONNECTION_ESTABLISHED), client);
            }
            case DISCONNECT -> {
                System.out.println("Wish to disconnect");
                Server.sendResponse(new ServerResponse(ResponseType.CONNECTION_TERMINATED), client);
            }

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

            case ROUND_FINISHED -> {
                client.hasAnsweredThisRound = true; ///sätts till trye för att byta spelare och visa samma frågor.
                List<Questions> questions = request.getQuestions();
                Server.switchCurrentPlayer(client, client.getOpponent(), questions);
            }
            case MY_SCORE ->{
                client.hasFinishedGame = true;
                client.score = request.getScore();
                Server.sendFinalResult(client, client.getOpponent());
            }
        }
    }
}
