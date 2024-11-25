package server;

public class Instance {

    ClientConnection clientOne;
    ClientConnection clientTwo;

    public Instance(ClientConnection clientOne, ClientConnection clientTwo){
        this.clientOne = clientOne;
        this.clientTwo = clientTwo;
    }
    public ClientConnection getClientOne() {
        return clientOne;
    }

    public ClientConnection getClientTwo() {
        return clientTwo;
    }
}