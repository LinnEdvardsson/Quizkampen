package Client;

import QuizApp.QuizFrame;
import QuizGame.QuestionDatabase;
import QuizGame.Questions;
import QuizGame.QuizSetUp;
import QuizGame.eCategoryType;
import server.ClientConnection;
import server.ServerResponse;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class Client {

    private final InetAddress ip = InetAddress.getLocalHost();
    private final int PORT = 5000;
    QuizFrame frame;
    QuestionDatabase database;
    ObjectOutputStream output;
    ObjectInputStream input;
    Socket socket;
    ResponseHandler responseHandler;
    QuizSetUp quizSetUp;
    int onRound = 0;
    int score = 0;
    boolean myTurn;
    public Questions currentQuestion;
    public eCategoryType currentCategory;
    public List<Questions> currentQuestions;
    ClientConnection playerOne;
    ClientConnection playerTwo;
    public int onQuestion = 0;

    String username;

    public Client() throws IOException {
        socket = new Socket(ip, PORT);
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
        frame = new QuizFrame();
        database = new QuestionDatabase();
        responseHandler = new ResponseHandler();
        quizSetUp = new QuizSetUp();

        listenForConnection();
        addActionListeners();
    }


    public void listenForConnection() {
        new Thread(() -> {

            System.out.println("Started listening to server");
            try {
                while (input.readObject() instanceof ServerResponse response) {
                    System.out.println("Received response");
                    responseHandler.handleResponse(response, this);

                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }).start();
    }

    public void sendToServer(Object obj) {
        try {
            output.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addActionListeners() { // actionlyssnare för knappar
        frame.getLoginButton().addActionListener(e -> {
            System.out.println("Sending request to connect...");
            username = frame.getUserField().getText();
            sendToServer(new ClientRequest(RequestType.CONNECT_REQUEST, username));
        });
        frame.getStartGameButton().addActionListener(e -> {
            System.out.println("Sending request to start game");
            sendToServer(new ClientRequest(RequestType.START_GAME, username));
        });
        frame.getExitGameButton().addActionListener(e -> {
            closeConnection();
            System.exit(0);
            System.out.println("Want to disconnect");
        });
        frame.getPlayAgainButton().addActionListener(e -> {
            if (e.getSource() == playerOne && e.getSource() == playerTwo) {
                frame.switchTo("Welcome");
            } else if (e.getSource() == playerOne || e.getSource() == playerTwo) {
                frame.switchTo("Queue"); ///Funkar inte
            }
        });
        frame.getNextButton().addActionListener(e -> {
            onQuestion++;
            resetButtons();
            if (onQuestion == quizSetUp.getQuestionsPerRound()) { /// spelet styrs via properies (antalet frågor).
                System.out.println("Sending round finished");
                sendToServer(new ClientRequest(RequestType.ROUND_FINISHED, currentQuestions));
                onQuestion = 0;                                 ///variabel styr vilken fråga man är på, ökar efter varje fråga för att komma till nästa.
                if (onRound == quizSetUp.getRoundsPerGame()){ /// spelet styrs via properies (antalet rundor).
                    sendToServer(new ClientRequest(RequestType.MY_SCORE, score));
                }
            }
            else{
                currentQuestion = currentQuestions.get(onQuestion);
                resetButtons();
                frame.populateQuestionPanel(currentQuestion);
                frame.switchTo("Question");
            }

        });
                /// Lagt kategoriknappar i lista och går igenom listan för att tilldela knapparna en kategoritext
        List<JButton> categoryButtons = frame.getCategoryButtons();
        for (JButton categoryButton : categoryButtons) {
            categoryButton.addActionListener(e -> {
                if (myTurn) {
                    System.out.println("Sending request to music category");

                    eCategoryType chosenCategory = eCategoryType.valueOf(categoryButton.getText());
                    currentCategory = chosenCategory;
                    System.out.println(currentCategory);

                    currentQuestions = quizSetUp.getQuestions(currentCategory);

                    currentQuestion = currentQuestions.get(onQuestion);
                    frame.populateQuestionPanel(currentQuestion);
                    frame.switchTo("Question");
                    addActionListenersToAnswerButtons();

                }
            });
        }

    }

            /// Lägger till lyssnare till svarsknappar från en lista, och kollar om korrekt svar stämmer med texten på knappen. + byter fönster till userresult
            public void addActionListenersToAnswerButtons() {
                List<JButton> answerButtons = frame.getAnswerButtons();
                for (JButton button : answerButtons) {
                    button.addActionListener(e -> {
                        String answer = button.getText();
                        boolean correct = currentQuestion.isCorrect(answer);
                        System.out.println("scoreTest: " + score);

                        if (correct) {
                            score++;
                            System.out.println("score1: " + score);
                            button.setBackground(Color.GREEN);
                            frame.getIsCorrectlabel().setText("You answered " + answer + " and you were correct");
                        } else {
                            button.setBackground(Color.RED);
                            for (JButton otherButton : answerButtons) {
                                if (currentQuestion.isCorrect(otherButton.getText())) {
                                    otherButton.setBackground(Color.GREEN);
                                    System.out.println("score2: " + score);
                                }
                            }
                            frame.getIsCorrectlabel().setText("You answered " + answer + " and you were wrong!");
                        }

                        Timer timer = new Timer(1500, evt -> {
                            frame.switchTo("UserResult");
                        });
                        timer.setRepeats(false);
                        timer.start();
                    });
                }
            }


        /// metod som resettar färg på knappar. hämtas efter varje fråga
    public void resetButtons() {
        List<JButton> answerButtons = frame.getAnswerButtons();
        for (JButton button : answerButtons) {
            button.setBackground(null);
        }
    }


    public void closeConnection() {
        try {
            output.close();
            input.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws IOException {
        Client client = new Client();
    }
}