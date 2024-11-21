package server;

import Client.ClientRequest;
import QuizApp.QuizFrame;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnection extends Thread implements Runnable {

//    private final InetAddress ip = InetAddress.getLocalHost();
//    private final int PORT = 6000;
//    public QuizFrame frame;
    private Socket socket;
    ObjectOutputStream out;
    ObjectInputStream in;

    public ClientConnection(Socket socket) throws UnknownHostException {
        this.socket = socket;
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

            /*
            while ((objIn = in.readObject()) != null) {
                if (objIn instanceof Object obj){
                    System.out.println("Received object from client");
                    out.writeObject(new Object());
                }
            }*/
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally{
            closeConnection();
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


    /*private void initializingConnection() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.println("connected to server");
        String fromClient;
        while ((fromClient = in.readLine()) != null) {
            out.println("u wrote: " + fromClient);
            System.out.println(fromClient);
        }
    }*/
//    private void initializingConnection2() throws IOException {
//        try {
//            out = new ObjectOutputStream(socket.getOutputStream());
//            in = new ObjectInputStream(socket.getInputStream());
//
//            //System.out.println("Connected to server");
//            Object objIn;
//            while ((objIn = in.readObject()) != null) {
//                if (objIn instanceof Object obj){
//                    System.out.println("Received object from client");
//                    out.writeObject(new Object());
//                }
//            }
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
