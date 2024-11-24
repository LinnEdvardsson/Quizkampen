package server;

import QuizApp.QuizFrame;
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

    public Server() {
        onGoingGame = new ArrayList<>();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started");
            while (true) {
                Socket socket = serverSocket.accept();
                ClientConnection connectedClient = new ClientConnection(socket);
                new Thread(connectedClient).start();
               // onGoingGame.add(connectedClient);
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


/*try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {

//               Socket clientSocket = serverSocket.accept();
                ClientConnection clientConnection = new ClientConnection(serverSocket.accept());
                new QuizFrame(); //felplacerad?
                //JOptionPane.showMessageDialog(null,"Waiting for another player!");
                System.out.println("Waiting for another player");
                ClientConnection clientConnection2 = new ClientConnection(serverSocket.accept());
                clientConnection.start();
                clientConnection2.start();
                new QuizFrame();//felplacerad??
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

// Säger vems tur det är, i paketet, så dom kan urskilja på klientsidan
// isMyTurn if  true > kategoripanel, annars queuepanel
// skicka med kategorier

//List<eCategoryType> categories = new ArrayList<>();
//        categories.add(eCategoryType.MUSIC);
//        categories.add(eCategoryType.SPORT);
