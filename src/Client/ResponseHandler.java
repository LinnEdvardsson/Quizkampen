package Client;

import server.ServerResponse;

public class ResponseHandler {

        //Testa att ha som statisk metod, annars skapa upp ett clientprotocol-objekt och anropa denna metod
        public static void handleResponse(ServerResponse response, Client client){
            switch (response.getResponseType()){
                case CONNECTION_ESTABLISHED -> {
                    System.out.println("Want to connect");
                    client.frame.switchTo("Welcome");
                }

                case PLAYER_QUEUED -> {}

                case GAME_STARTED -> {}

                case CHOOSE_CATEGORY -> {
                    client.frame.switchTo("Category");
                    System.out.println("Choosing Category");
                }
            }
        }
    }


