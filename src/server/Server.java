package server;

import QuizGame.PlayerScore;
import QuizGame.Questions;
import QuizGame.QuizSetUp;
import QuizGame.eCategoryType;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Server {

    private static final int PORT = 6000;
    static List<ClientConnection> onGoingGame;
    ClientConnection currentPlayer;
    QuizSetUp setUp;
    List<PlayerScore> playerOneScore;
    List<PlayerScore> playerTwoScore;


    public Server() {
        onGoingGame = new ArrayList<>();
        playerOneScore = new ArrayList<>();
        playerTwoScore = new ArrayList<>();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started");
            while (true) {
                Socket socket = serverSocket.accept();
                ClientConnection connectedClient = new ClientConnection(socket);
                new Thread(connectedClient).start();

                 ///Lägger till varje ny uppkoppling i en lista
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void addToGame(ClientConnection client) {
        onGoingGame.add(client);
    }

    public static void notifyInstance(Instance instance) throws IOException {
        ClientConnection playerOne = instance.getClientOne();
        ClientConnection playerTwo = instance.getClientTwo();

        playerOne.setOpponent(playerTwo);
        playerTwo.setOpponent(playerOne);


        List<eCategoryType> categories = QuizSetUp.getCategories();

        instance.currentRound++;

        sendResponse(new ServerResponse(ResponseType.MY_TURN_CHOOSING, true, categories), playerOne);
        sendResponse(new ServerResponse(ResponseType.WAITING), playerTwo);
        System.out.println("Game started");

    }

    public static void switchCurrentPlayer(ClientConnection playerOne, ClientConnection playerTwo, List<Questions> questions) throws IOException {
        System.out.println("Switching players");
        if (playerOne.hasAnsweredThisRound && playerTwo.hasAnsweredThisRound){
            System.out.printf("Sending categories again");
            List<eCategoryType> categories = QuizSetUp.getCategories();
            sendResponse(new ServerResponse(ResponseType.MY_TURN_CHOOSING, true, categories), playerOne);
            sendResponse(new ServerResponse(ResponseType.WAITING), playerTwo);
            playerOne.hasAnsweredThisRound = false;
            playerTwo.hasAnsweredThisRound = false;
        }
        else{
            System.out.println("Sending same questions");
            sendResponse(new ServerResponse(ResponseType.WAITING), playerOne);
            sendResponse(new ServerResponse(ResponseType.MY_TURN_ANSWERING, questions), playerTwo);
            System.out.println("Player2 playing turn");
        }
    }

    public static void sendFinalResult(ClientConnection playerOne, ClientConnection playerTwo) throws IOException {
        Server.sendResponse(new ServerResponse(ResponseType.WAITING), playerOne);
        if (playerOne.hasFinishedGame && playerTwo.hasFinishedGame){
            sendResponse(new ServerResponse(ResponseType.GAME_DONE, playerOne.score, playerTwo.score, playerTwo.getUsername()), playerOne);
            sendResponse(new ServerResponse(ResponseType.GAME_DONE, playerTwo.score, playerOne.score, playerOne.getUsername()), playerTwo);
        }
    }

    public static void sendResponse(ServerResponse response, ClientConnection client) throws IOException {
        client.getOutputStream().writeObject(response);
    }

    public static int playersInQueue() {
        return onGoingGame.size();
    }

    public static void main(String[] args) throws UnknownHostException {
        new Server();

    }
}
  /*
        if(currentPlayer == playerOne) {
            sendResponse(new ServerResponse(ResponseType.PLAYER_ONE_DONE, false, QuizSetUp.getCategories()), playerOne);
            sendResponse(new ServerResponse(ResponseType.PLAYER_TWO_TURN, true, QuizSetUp.getCategories()), playerTwo);
            System.out.println("Player2 playing turn");
        } else{
            currentPlayer = playerOne;
        }*/