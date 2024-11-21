package server;

import Client.Client;
import QuizApp.QuizFrame;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    private static final int PORT = 6000;

    public Server() {

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

    }

    public static void sendResponse(ServerResponse response, ClientConnection client) throws IOException {
        client.getOutputStream().writeObject(response);
    }

    public static void main(String[] args) throws UnknownHostException {
        Server server = new Server();

    }
}
