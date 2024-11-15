package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    private static final int PORT = 6000;

    public Server() {
            while (true) {
                try (ServerSocket serverSocket = new ServerSocket(PORT)){
                    Socket clientSocket = serverSocket.accept();
                    ClientConnection clientConnection = new ClientConnection(clientSocket);
                    new Thread(clientConnection).start();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
    }

                    /* PrintWriter clientWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {



                    System.out.println("Client connected: ");
                    String inputLine;
                    while ((inputLine = clientReader.readLine()) != null) {
                        System.out.println("Received from client: ");
                        clientWriter.println("Echo: ");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) throws UnknownHostException {
        Server server = new Server();
    }
}
