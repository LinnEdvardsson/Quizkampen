package Client;

import server.ClientConnection;
import server.Server;
import server.ServerResponse;
import QuizGame.*;
import server.ClientConnection.*;
import java.util.List;

//CLIENT

public class ResponseHandler {
    private ClientConnection clientConnection;

        public void handleResponse(ServerResponse response, Client client){
            switch (response.getResponseType()){
                case CONNECTION_ESTABLISHED -> {
                    System.out.println("Want to connect");
                    client.frame.switchTo("Welcome");
                }

                case PLAYER_QUEUED -> {
                    System.out.println("Player waiting to connect with other player");
                    client.frame.switchTo("Opponent");
                    clientConnection.getGamePlayers(); //vet Inte hur man ska gå vidare efter att man plockat ut två st...
                    //Få till så alla som connectar till servern läggs till i listan som finns i servern och här paras ihop så dom möter varandra.
                }

                case GAME_STARTED -> {
                    client.frame.switchTo("Category");
                    System.out.println("Choosing Category");
                }

                case CHOOSE_CATEGORY -> {
                    client.frame.switchTo("Question");
                        //Tanken är att gui't på något jävla vänster ska uppdateras här så akutell fråga läggs in + resp. svar
                    }


                }
            }
        }



