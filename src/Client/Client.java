package Client;

import QuizApp.QuizFrame;
import QuizGame.PlayerScore;
import QuizGame.QuestionDatabase;
import server.ClientConnection;
import server.ResponseType;
import server.Server;
import server.ServerResponse;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;



public class Client {

    private final InetAddress ip = InetAddress.getLocalHost();
    private final int PORT = 6000;
    QuizFrame frame;
    QuestionDatabase database;
    ObjectOutputStream output;
    ObjectInputStream input;
    Socket socket;
    ResponseHandler responseHandler;
    
    String username;

    PlayerScore playerScore;

    public Client() throws IOException {
        socket = new Socket(ip, PORT);
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
        frame = new QuizFrame();
        database = new QuestionDatabase();
        responseHandler = new ResponseHandler();
        playerScore = new PlayerScore();

        listenForConnection();
        addActionListeners();
        database.addQuestionsForCategory("music");

    }


    public void listenForConnection(){
        new Thread(()->{

            System.out.println("Started listening to server");
            try{
                while(input.readObject() instanceof ServerResponse response){
                    System.out.println("Received response");
                    responseHandler.handleResponse(response, this);

                }
            }
            catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }

        }).start();
    }

    public void sendToServer(Object obj) {
        try{
            output.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   public PlayerScore getPlayerScore() {
        return playerScore;
   }



    public void addActionListeners(){ //Alla actionlyssnare för knappar osv läggs HÄR.
        frame.getLoginButton().addActionListener(e ->{
            System.out.println("Sending request to connect...");
            username = frame.getUserField().getText();
            sendToServer(new ClientRequest(RequestType.CONNECT_REQUEST, username));
        });
        frame.getStartGameButton().addActionListener(e ->{
            System.out.println("Sending request to start game");
            sendToServer(new ClientRequest(RequestType.START_GAME, username));
        });
        frame.getExitGameButton().addActionListener(e -> {
            closeConnection();
            System.exit(0);
            System.out.println("Want to disconnect");
        });
        frame.getCategory1Button().addActionListener(e ->{
            System.out.println("Sending request to music category");
            sendToServer(new ClientRequest(RequestType.CATEGORY_TYPE_REQUEST, username));
            frame.switchTo("Question");
        });
//        frame.getCategory2Button().addActionListener(e ->{
//            System.out.println("Sending request to sports category");
//            frame.setUserCategoryChoice("sport");
//            sendToServer(new ClientRequest(RequestType.CATEGORY_TYPE_REQUEST, username));
//            frame.switchTo("Question");
//        });

        frame.getAnswer1Button().addActionListener(e ->{
            if(frame.getCorrectAnswerIndex() == 0){
                frame.getAnswer1Button().setBackground(null);
                frame.getAnswer1Button().setBackground(Color.GREEN);
                playerScore.updateScore(1);
                frame.switchTo("UserResult");
            }else{
                frame.getAnswer1Button().setBackground(Color.RED);
            }
        });

        frame.getAnswer2Button().addActionListener(e ->{
            if(frame.getCorrectAnswerIndex() == 1){
                frame.getAnswer2Button().setBackground(null);
                frame.getAnswer2Button().setBackground(Color.GREEN);
                playerScore.updateScore(1);
                System.out.println("Current Score: " + playerScore.getScore());
                frame.switchTo("UserResult");
            }else{
                frame.getAnswer2Button().setBackground(Color.RED);
            }
        });

        frame.getAnswer3Button().addActionListener(e ->{
            if(frame.getCorrectAnswerIndex() == 2){
                frame.getAnswer3Button().setBackground(null);
                frame.getAnswer3Button().setBackground(Color.GREEN);
                playerScore.updateScore(1);
                System.out.println("Current Score: " + playerScore.getScore());
                frame.switchTo("UserResult");
            }else{
                frame.getAnswer3Button().setBackground(Color.RED);
            }
        });
        frame.getAnswer4Button().setBackground(null);
        frame.getAnswer4Button().addActionListener(e -> {
            if(frame.getCorrectAnswerIndex() == 3){
                frame.getAnswer3Button().setBackground(null);
                frame.getAnswer4Button().setBackground(Color.GREEN);
                playerScore.updateScore(1);
                System.out.println("Current Score: " + playerScore.getScore());
                frame.switchTo("UserResult");
            }else{
                frame.getAnswer4Button().setBackground(Color.RED);
            }
        });
        frame.getNextButton().addActionListener(e ->{
           frame.switchTo("WaitingForPlayer");
        });
    }

    public void closeConnection(){
        try{
            output.close();
            input.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    
    public static void main(String[] args) throws IOException {
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
    /*
            System.out.println("Attempting to connect...");
            try (Socket clientSocket = new Socket(ip, PORT)) {
                System.out.println("Socket created.");
                try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

                    System.out.println("Streams created, connection established.");
                    while(input.readObject() instanceof ServerResponse response){

                    }

                    Object objIn;
                    while ((objIn = in.readLine()) != null) {
                        if (objIn instanceof ServerResponse response){
                            System.out.println("Received response");
                        }
                    }
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                System.err.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
            }*/



    /*public void addEventListeners(){
        frame.getLoginButton().addActionListener();
    }*/
    /*
    public void sendRequest(JButton button) throws IOException {
        output = new ObjectOutputStream(clientSocket.getOutputStream());
        input = new ObjectInputStream(new ObjectInputStream(clientSocket.getInputStream()));
        if (button == frame.getLoginButton()) {
            System.out.println("Sending request to connect...");
            output.writeObject(new ClientRequest(RequestType.CONNECT_REQUEST, frame.getUserField().getText()));
        } else if (button == frame.getStartGameButton()) {
            output.writeObject(new ClientRequest(RequestType.START_GAME, frame.getUserField().getName()));
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        try {
            sendRequest(button);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }*/

