package server;

public class Instance {

    ClientConnection clientOne;
    ClientConnection clientTwo;
    ClientConnection currentPlayer;

    public Instance(ClientConnection clientOne, ClientConnection clientTwo){
        this.clientOne = clientOne;
        this.clientTwo = clientTwo;
        this.currentPlayer = clientOne;
    }
    public ClientConnection getClientOne() {
        return clientOne;
    }

    public ClientConnection getClientTwo() {
        return clientTwo;
    }
    
    public ClientConnection getCurrentPlayer() {return currentPlayer;}
}
