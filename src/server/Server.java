package server;

import QuizApp.QuizFrame;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static final int PORT = 6000;
    static List<ClientConnection> connectedClients; ///Kan användas för att para ihop spelare. Oklart om den ska ligga i själva servern eller utanför.
    static List<ClientConnection> onGoingGame;

    public Server() {
        connectedClients = new ArrayList<>();
        onGoingGame = new ArrayList<>();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started");
            while (true) {
                Socket socket = serverSocket.accept();
                ClientConnection connectedClient = new ClientConnection(socket);
                new Thread(connectedClient).start();
                connectedClients.add(connectedClient); ///Lägger till varje ny uppkoppling i en lista
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void addToGame(ClientConnection client) {
        onGoingGame.add(client);
    }

    public static void notifyGameStart() throws IOException{
        for (ClientConnection client : onGoingGame) {
            Server.sendResponse(new ServerResponse(ResponseType.GAME_STARTED), client);
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
