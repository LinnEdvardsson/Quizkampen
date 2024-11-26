package server;

import QuizGame.PlayerScore;
import QuizGame.QuizSetUp;
import QuizGame.eCategoryType;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

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


        List<eCategoryType> categories = QuizSetUp.getCategories();

        sendResponse(new ServerResponse(ResponseType.GAME_STARTED, true, categories), playerOne);
        sendResponse(new ServerResponse(ResponseType.GAME_STARTED, false, categories), playerTwo);
        System.out.println("Game started");

    }

    public void switchCurrentPlayer(ClientConnection playerOne, ClientConnection playerTwo) throws IOException {
        if(currentPlayer == playerOne) {
            sendResponse(new ServerResponse(ResponseType.PLAYER_ONE_DONE, false, QuizSetUp.getCategories()), playerOne);
            sendResponse(new ServerResponse(ResponseType.PLAYER_TWO_TURN, true, QuizSetUp.getCategories()), playerTwo);
            System.out.println("Player2 playing turn");
        } else{
            currentPlayer = playerOne;
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
