package Client;

import QuizGame.eCategoryType;
import server.ServerResponse;
import java.io.IOException;
import java.util.List;


//CLIENT

public class ResponseHandler {

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
            case MY_TURN_ANSWERING -> {
                client.onRound++;
                client.currentQuestions = response.getQuestions();

                client.onQuestion = 0;
                client.currentQuestion = client.currentQuestions.get(client.onQuestion);
                client.frame.populateQuestionPanel(client.currentQuestion);
                client.frame.switchTo("Question");
                client.addActionListenersToAnswerButtons();

            }
            case MY_TURN_CHOOSING -> {
                client.onRound++;
                client.myTurn = true;
                client.frame.switchTo("Category");
                List<eCategoryType> categories = response.getCategories();
                client.frame.getCategory1Button().setText(categories.get(0).name()); ///Kan läggas i egen metod
                client.frame.getCategory2Button().setText(categories.get(1).name());
                client.frame.getCategory3Button().setText(categories.get(2).name());
                client.frame.getCategory4Button().setText(categories.get(3).name());
                System.out.println("Choosing Category");
            }

            case WAITING -> {
                client.myTurn = false;
                client.frame.switchTo("Queue");
                System.out.println("Queue");
            }
            case GAME_DONE ->{
                client.frame.switchTo("FinalResult");
                String opponentsName = response.getOpponentsName();
                client.frame.setScoreLabels(client.username, response.getMyScore(), opponentsName, response.getOpponentsScore());
            }

        }
    }
}