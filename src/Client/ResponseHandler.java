package Client;

import QuizGame.eCategoryType;
import server.Server;
import server.ServerResponse;
import java.io.IOException;
import java.util.List;


//CLIENT

public class ResponseHandler {
    Server server;


        public void handleResponse(ServerResponse response, Client client) throws IOException {


            switch (response.getResponseType()){
                case CONNECTION_ESTABLISHED -> {
                    System.out.println("Want to connect");
                    client.frame.switchTo("Welcome");
                }

                /// ongoing client ska hämtas här, för att kunna sätta vilken skärm som ska visas! Lägga till response/request your_turn/other_player_turn?
                case PLAYER_QUEUED -> {
                    client.frame.switchTo("PlayerQueue");
                    System.out.println("Player waiting to connect with other player");
                }

                case GAME_STARTED -> {
                    if (response.isMyTurn()){
                        client.frame.switchTo("Category");
                        List<eCategoryType> categories = response.getCategories();
                        client.frame.getCategory1Button().setText(categories.get(0).name());
                        client.frame.getCategory2Button().setText(categories.get(1).name());
                        System.out.println("P1 Choosing Category");
                    }
                    else{
                        client.frame.switchTo("Queue");
                        System.out.println("P2 Queue");
                    }
                }

                case CHOOSE_CATEGORY -> {
                   // client.frame.switchTo("Question");

                        //Tanken är att gui't på något jävla vänster ska uppdateras här så akutell fråga läggs in + resp. svar
                    }


                }
            }
        }



