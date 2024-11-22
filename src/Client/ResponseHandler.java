package Client;

import server.ClientConnection;
import server.Server;
import server.ServerResponse;
import QuizGame.*;
import server.ClientConnection.*;
import java.util.List;

//CLIENT

public class ResponseHandler {
    ClientConnection clientConnection;

        public void handleResponse(ServerResponse response, Client client){
            //SENDTOSERVER METOD?
            switch (response.getResponseType()){
                case CONNECTION_ESTABLISHED -> {
                    System.out.println("Want to connect");
                    client.frame.switchTo("Welcome"); //new game actionListener
                }

                case PLAYER_QUEUED -> {
                    client.frame.switchTo("PlayerQueue");
                    System.out.println("Player waiting to connect with other player");
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



