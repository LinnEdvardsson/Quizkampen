package Client;

import QuizApp.QuizFrame;
import server.ServerResponse;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;



public class Client {

    private final InetAddress ip = InetAddress.getLocalHost();
    private final int PORT = 6000;
    QuizFrame frame;
    ObjectOutputStream output;
    ObjectInputStream input;
    Socket socket;
    ResponseHandler responseHandler;
    
    String username;

    public Client() throws IOException {
        socket = new Socket(ip, PORT);
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
        frame = new QuizFrame();
        responseHandler = new ResponseHandler();
        listenForConnection();
        addActionListeners();
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

    public void addActionListeners(){ ///Alla actionlyssnare för knappar osv läggs HÄR.
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
            System.out.println("Sending request to .... category");
            sendToServer(new ClientRequest(RequestType.CATEGORY_TYPE_REQUEST, username));
        });
        frame.getCategory2Button().addActionListener(e ->{
            System.out.println("Sending request to .... category");
            sendToServer(new ClientRequest(RequestType.CATEGORY_TYPE_REQUEST, username));
        });
        frame.getCategory3Button().addActionListener(e ->{
            System.out.println("Sending request to .... category");
            sendToServer(new ClientRequest(RequestType.CATEGORY_TYPE_REQUEST, username));
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
