package server;

import QuizGame.Questions;
import QuizGame.QuizSetUp;
import QuizGame.eCategoryType;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static final int PORT = 5000;
    static List<ClientConnection> onGoingGame;

    public Server() {
        onGoingGame = new ArrayList<>();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started");
            while (true) {
                Socket socket = serverSocket.accept();
                ClientConnection connectedClient = new ClientConnection(socket);
                new Thread(connectedClient).start();

            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    ///Lägger till varje ny uppkoppling i en lista
    public static void addToGame(ClientConnection client) {
        onGoingGame.add(client);
    }

    /// Metod för att särskilja spelare och lägga i tur-ordning.
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

    /// Metod för att byta spelare efter en runda. Klient som valt kategori skickar då med sig frågor den svarat på till den andra klienten.
    /// Skickar rätt respons till client(responsehandler) beroende på status på spelare.
    ///
    public static void switchCurrentPlayer(ClientConnection playerOne, ClientConnection playerTwo, List<Questions> questions) throws IOException {
        System.out.println("Switching players");
        if (playerOne.hasAnsweredThisRound && playerTwo.hasAnsweredThisRound){ //om BÅDA spelarna har svarat på frågor i en runda
            System.out.printf("Sending categories again");
            List<eCategoryType> categories = QuizSetUp.getCategories();
            sendResponse(new ServerResponse(ResponseType.MY_TURN_CHOOSING, true, categories), playerOne);
            sendResponse(new ServerResponse(ResponseType.WAITING), playerTwo);
            playerOne.hasAnsweredThisRound = false;           ///Sätts till falskt här då ny "omgång" inleds och ingen har svarat än, ändras i requestHandler till true när första spelaren svarar.
            playerTwo.hasAnsweredThisRound = false;
        }
        else{                                                  ///om INTE båda spelarna har svarat på frågor skickas samma frågor till andra spelaren, och första spelaren hamnar i kö.
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