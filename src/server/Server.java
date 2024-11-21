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
            while (true) {
//                Socket clientSocket = serverSocket.accept();
                ClientConnection clientConnection = new ClientConnection(serverSocket.accept());
                new QuizFrame();
                //JOptionPane.showMessageDialog(null,"Waiting for another player!");
                System.out.println("Waiting for another player");
                ClientConnection clientConnection2 = new ClientConnection(serverSocket.accept());
                clientConnection.start();
                clientConnection2.start();
                new QuizFrame();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        Server server = new Server();

    }
}
