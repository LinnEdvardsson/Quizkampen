package Client;

import QuizGame.eCategoryType;

import server.ClientConnection;
import server.Instance;
import server.Server;
import server.ServerResponse;
import java.io.IOException;
import java.util.List;
import QuizGame.*;


//CLIENT

public class ResponseHandler {

    QuizSetUp quizSetUp;
    Server server;
    ClientConnection player1;
    ClientConnection player2;

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
                client.frame.getCategory1Button().setText(categories.get(0).name());
                client.frame.getCategory2Button().setText(categories.get(1).name());
                client.frame.getCategory3Button().setText(categories.get(2).name());
                client.frame.getCategory4Button().setText(categories.get(3).name());
                System.out.println("P1 Choosing Category");
            }
            case CHOOSEN_CATEGORY -> {
                if (response.isMyTurn()) {
                    client.frame.switchTo("Question");

                }
            }
            case WAITING -> {
                client.myTurn = false;
                client.frame.switchTo("Queue");
                System.out.println("P2 Queue");
            }
            case GAME_DONE ->{
                client.frame.switchTo("FinalResult");
                String opponentsName = response.getOpponentsName();
                client.frame.setScoreLabels(client.username, response.getMyScore(), opponentsName, response.getOpponentsScore());
            }

        }
    }
}

//            case GAME_STARTED -> {
//                if (response.isMyTurn()) {
//                    client.myTurn = true;
//                    client.frame.switchTo("Category");
//                    List<eCategoryType> categories = response.getCategories();
//                    client.frame.getCategory1Button().setText(categories.get(0).name());
//                    client.frame.getCategory2Button().setText(categories.get(1).name());
//                    System.out.println("P1 Choosing Category");
//                } else {
//                    client.myTurn = false;
//                    client.frame.switchTo("Queue");
//                    System.out.println("P2 Queue");
//                }
//            }

//            case PLAYER_ONE_DONE -> {
//                if (response.isMyTurn()) {
//                    client.myTurn = true;
//                    client.setCategories();
//                    System.out.println("P1 Choosing Category");
//                } else {
//                    client.myTurn = false;
//                    client.frame.switchTo("Queue");
//                    System.out.println("P2 Queue");
//                }
//            case GET_QUESTION -> {
//                server.switchCurrentPlayer(player1, player2);
//                if (response.isMyTurn()) {
//                    client.frame.switchTo("Question");
//                }
//            }