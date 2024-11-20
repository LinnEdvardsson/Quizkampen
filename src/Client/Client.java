package Client;

import QuizApp.QuizFrame;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

public class Client {

    private final InetAddress ip = InetAddress.getLocalHost();
    private final int PORT = 6000;
    QuizFrame frame;

    public Client() throws UnknownHostException {
        listenForConnection();
        //initializingConnection2();
    }

    public void initializingConnection2(){
        //socket = new Socket(ip, PORT);
        //out = new ObjectOutputStream(socket.getOutputStream());
        //in = new ObjectInputStream(socket.getInputStream());
    }

    public void sendToServer(Object obj, ObjectOutputStream out) throws IOException {
        out.writeObject(obj);
    }

    public void listenForConnection(){
        new Thread(()->{
            System.out.println("Attempting to connect...");
            try (Socket clientSocket = new Socket(ip, PORT)) {
                System.out.println("Socket created.");
                try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                     ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {

                    System.out.println("Streams created, connection established.");
                    sendToServer(new Object(), out);
                    Object objIn;
                    while ((objIn = in.readObject()) != null) { //skicka ny frame?
                        if (objIn instanceof Object obj){
                            System.out.println("Received response");
                        }
                    }
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                System.err.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
            }

        }).start();
    }
    
    public static void main(String[] args) throws UnknownHostException {
        Client client = new Client();
    }
}

/*package Client;

import QuizApp.QuizFrame;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

public class Client {

    private final InetAddress ip = InetAddress.getLocalHost();
    private final int PORT = 6000;
    QuizFrame frame;

    public Client() throws UnknownHostException {
        listenForConnection();
        //initializingConnection2();
    }

   // public void initializingConnection2(){
        //socket = new Socket(ip, PORT);
        //out = new ObjectOutputStream(socket.getOutputStream());
        //in = new ObjectInputStream(socket.getInputStream());
    }

    public void sendToServer(Object obj, ObjectOutputStream out) throws IOException {
        out.writeObject(obj);
    }

    public void listenForConnection(){
        new Thread(()->{
            System.out.println("Attempting to connect...");
            try (Socket clientSocket = new Socket(ip, PORT)) {
                System.out.println("Socket created.");
                try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                     ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {

                    System.out.println("Streams created, connection established.");
                    sendToServer(new Object(), out);
                    Object objIn;
                    while ((objIn = in.readObject()) != null) { //skicka ny frame?
                        if (objIn instanceof Object obj){
                            System.out.println("Received response");
                        }
                    }
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                System.err.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
            }

        }).start();
    }

    public static void main(String[] args) throws UnknownHostException {
        Client client = new Client();
    }
}

/*public void initializingConnection(){
    System.out.println("Attempting to connect...");
    try (Socket clientSocket = new Socket(ip, PORT)) {
        System.out.println("Socket created.");
        try (PrintWriter serverWriter = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader serverReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            System.out.println("Streams created, connection established.");
            String temp = serverReader.readLine();
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

    /*public void addEventListeners(){
        frame.getLoginButton().addActionListener();
    }*/

