package Client;

import QuizGame.eCategoryType;

import server.Instance;
import server.Server;
import server.ServerResponse;
import java.io.IOException;
import java.util.List;
import QuizGame.*;


//CLIENT

public class ResponseHandler {

    QuizSetUp quizSetUp;


    public void handleResponse(ServerResponse response, Client client) throws IOException {

        switch (response.getResponseType()) {
            case CONNECTION_ESTABLISHED -> {
                System.out.println("Want to connect");
                client.frame.switchTo("Welcome");
            }

            case PLAYER_QUEUED -> {
                client.frame.switchTo("Queue");
                System.out.println("Player waiting to connect with other player");

            }

            case GAME_STARTED -> {
                if (response.isMyTurn()) {
                    client.frame.switchTo("Category");
                    List<eCategoryType> categories = response.getCategories();
                    client.frame.getCategory1Button().setText(categories.get(0).name());
                    client.frame.getCategory2Button().setText(categories.get(1).name());
                    client.frame.getCategory3Button().setText(categories.get(2).name());
                    client.frame.getCategory4Button().setText(categories.get(3).name());
                    System.out.println("P1 Choosing Category");
                } else {
                    client.frame.switchTo("Queue");
                    System.out.println("P2 Queue");
                }
            }

            case CHOOSEN_CATEGORY -> {
                if (response.isMyTurn()) {
                    client.frame.switchTo("Question");
                    quizSetUp.startQuiz();


                }
            }

            case GET_QUESTION -> {

                /// Frågor ska in här + frågepanel. KIM ÄR HÄR.

            }

            case GAME_DONE
        }
    }
}



