package server;

import Client.ClientRequest;
import QuizApp.QuizFrame;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;



public class ClientConnection extends Thread implements Runnable {

    private Socket socket;
    ObjectOutputStream out;
    ObjectInputStream in;
//    private ClientConnection player1;
//    private ClientConnection Player2;
//    List<ClientConnection> players;

    public ClientConnection(Socket socket) throws UnknownHostException {
        this.socket = socket;
//        players = new ArrayList<>();

    }

    @Override
    public void run() {
        try {
            initializingConnection();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializingConnection() throws IOException {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            System.out.println("Connected to server");
            while(in.readObject() instanceof ClientRequest request){
                RequestHandler.handleRequest(request, this);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally{
            //closeConnection();
        }
    }

    public ObjectOutputStream getOutputStream(){
        return out;
    }

    public void closeConnection(){
        try{
            out.close();
            in.close();
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}

