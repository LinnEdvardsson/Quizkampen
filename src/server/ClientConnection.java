package server;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnection implements Runnable {

    private final InetAddress ip = InetAddress.getLocalHost();
    private final int PORT = 6000;
    private Socket socket;

    public ClientConnection(Socket socket) throws UnknownHostException {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            initializingConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializingConnection() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.println("connected to server");
        String fromClient;
        while ((fromClient = in.readLine()) != null) {
            out.println("u wrote: " + fromClient);
            System.out.println(fromClient);
        }
    }

}
