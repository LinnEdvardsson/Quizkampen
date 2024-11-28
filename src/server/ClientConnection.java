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
    RequestHandler requestHandler;
    public boolean hasAnsweredThisRound = false;
    public boolean hasFinishedGame = false;
    public int score = 0;
    String username;

    ClientConnection opponent;

    public ClientConnection(Socket socket) throws UnknownHostException {
        this.socket = socket;
        this.requestHandler = new RequestHandler();

    }

    public String getUsername(){
        return username;
    }

    public void setOpponent(ClientConnection opponentClient){
        this.opponent = opponentClient;
    }

    public ClientConnection getOpponent(){
        return opponent;
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
                requestHandler.handleRequest(request, this);
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

