package Client;

import QuizApp.QuizAppFrame;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private final InetAddress ip = InetAddress.getLocalHost();
    private final int PORT = 6000;
    QuizAppFrame frame = new QuizAppFrame();


    public Client() throws UnknownHostException {
        initializingConnection();
    }

    public void initializingConnection(){
        System.out.println("Attempting to connect...");
        try (Socket clientSocket = new Socket(ip, PORT)) {
            System.out.println("Socket created.");
            try (PrintWriter serverWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in));
                 BufferedReader serverReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                System.out.println("Streams created, connection established.");
                frame.setPlayer1Name(JOptionPane.showInputDialog(null,"Enter your name"));
                frame.setPlayer2Name(JOptionPane.showInputDialog(null,"Enter your name"));
                frame.mainFrame();

                String fromUser;
                while ((fromUser = userReader.readLine()) != null) {
                    serverWriter.println(fromUser);
                    String fromServer = serverReader.readLine();

                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        Client client = new Client();
    }
}
