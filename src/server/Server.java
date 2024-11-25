package server;

import QuizApp.QuizFrame;
import QuizGame.PlayerScore;
import QuizGame.QuizSetUp;
import QuizGame.eCategoryType;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {

    private static final int PORT = 6000;
    static List<ClientConnection> onGoingGame;
    ClientConnection currentPlayer;
    ClientConnection playerOne;
    ClientConnection playerTwo;
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

    public void notifyInstance(Instance instance) throws IOException {
         playerOne = instance.getClientOne();
         playerTwo = instance.getClientTwo();
         currentPlayer = playerOne;

        List<eCategoryType> categories = setUp.getCategories();

        sendResponse(new ServerResponse(ResponseType.GAME_STARTED, true, categories), playerOne);
        sendResponse(new ServerResponse(ResponseType.GAME_STARTED, false, categories), playerTwo);
        System.out.println("Game started");
    }

    public void switchCurrentPlayer() throws IOException {
        if(currentPlayer == playerOne) {
            sendResponse(new ServerResponse(ResponseType.PLAYER_ONE_DONE, false, setUp.getCategories()), playerOne);
            sendResponse(new ServerResponse(ResponseType.PLAYER_TWO_TURN, true, setUp.getCategories()), playerTwo);
        } else{
            currentPlayer = playerOne;
        }
    }

    public void printScoreToPlayers() throws IOException {
        playerOne = playerOneScore;
        playerTwo = playerTwoScore;
        //for(each)
        //PlayerOneScoreList = userfield.setText();
        System.out.println("Final score: " + playerScore.getScore()); //--> Läggas i finalpanel?

        sendResponse(new ServerResponse(ResponseType.GAME_DONE), playerOne);
        sendResponse(new ServerResponse(ResponseType.GAME_DONE), playerTwo);
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
