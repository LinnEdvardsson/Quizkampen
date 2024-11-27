package QuizApp;

import Client.Client;
import Client.ResponseHandler;
import QuizGame.QuestionDatabase;
import QuizGame.Questions;
import server.ClientConnection;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class QuizFrame {

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JButton loginButton;
    private JTextField userField;
    private JButton startGameButton;
    private JButton exitGameButton;
    private JButton category1Button;
    private JButton category2Button;
    private JButton category3Button;
    private JButton category4Button;
    private JButton answer1Button;
    private JButton answer2Button;
    private JButton answer3Button;
    private JButton answer4Button;
    private int randomQuestion;
    private int correctAnswerIndex;
    private String userCategoryChoice;
    private JLabel questionLabel = new JLabel();
    private JButton nextButton;
    private JLabel isCorrectlabel;

    public QuizFrame() { //alla som ska ha actionlyssnare deklaraeras här och implemeter i egna metoder.
        randomQuestion = (int) (Math.random() * 4);
        setupGUI();
    }

    public void setupGUI() {
        frame = new JFrame("Quiz Game");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);


        mainPanel.add(createLoginFrame(), "Login");
        mainPanel.add(createWelcomeFrame(), "Welcome");
        mainPanel.add(createQueueFrame(), "Queue");
       // mainPanel.add(createOpponentFrame(), "Opponent");
        mainPanel.add(createCategoryFrame(), "Category");
        mainPanel.add(createQuestionFrame(), "Question");
         mainPanel.add(createUserResultFrame(), "UserResult");
//        mainPanel.add(createOpponentResultFrame(), "OpponentResult");
        mainPanel.add(createFinalResultFrame(), "FinalResult");
        mainPanel.add(createWaitingForPlayer(), "WaitingForPlayer");

        frame.add(mainPanel);
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        cardLayout.show(mainPanel, "Login");
    }


    public void switchTo(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public List<JButton> getCategoryButtons(){
        List<JButton> categoryButtons = new ArrayList<>();
        categoryButtons.add(category1Button);
        categoryButtons.add(category2Button);
        categoryButtons.add(category3Button);
        categoryButtons.add(category4Button);
        return categoryButtons;
    }

    public List<JButton> getAnswerButtons(){
        List<JButton> answerButtons = new ArrayList<>();
        answerButtons.add(answer1Button);
        answerButtons.add(answer2Button);
        answerButtons.add(answer3Button);
        answerButtons.add(answer4Button);
        return answerButtons;
    }

    public JPanel createLoginFrame() {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        JLabel lable = new JLabel("Enter username");
        userField = new JTextField( SwingConstants.CENTER);

        loginButton = new JButton("Login");

        panel.add(lable);
        panel.add(userField);
        panel.add(loginButton);

        return panel;
    }

    private JPanel createWelcomeFrame() {
        JPanel welcomePanel = new JPanel(new BorderLayout());
        startGameButton = new JButton("Start Game");
        exitGameButton = new JButton("Exit Game");
        welcomePanel.add(startGameButton, BorderLayout.NORTH);
        welcomePanel.add(exitGameButton, BorderLayout.SOUTH);
        return welcomePanel;
    }

    private JPanel createWaitingForPlayer(){
        JPanel waitingPanel = new JPanel(new BorderLayout());
        waitingPanel.add(new JLabel("Waiting for player"), BorderLayout.CENTER);
        waitingPanel.setSize(100,100);
        return waitingPanel;
    }

    private JPanel createOpponentFrame() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Finding an opponent...", SwingConstants.CENTER);
        JButton proceedButton = new JButton("Next");

        panel.add(label, BorderLayout.CENTER);
        panel.add(proceedButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createQueueFrame(){
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Waiting for the other player");
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }


    private JPanel createCategoryFrame() {
        JPanel panel = new JPanel(new GridLayout(4, 1));
        JLabel label = new JLabel("Choose a category:", SwingConstants.CENTER);
        category1Button = new JButton();
        category2Button = new JButton();
        category3Button = new JButton();
        category4Button = new JButton();

        panel.add(label);
        panel.add(category1Button);
        panel.add(category2Button);
        panel.add(category3Button);
        panel.add(category4Button);
        return panel;
    }

    private JPanel createQuestionFrame() {
        QuestionDatabase qdb = new QuestionDatabase();
        JPanel panel = new JPanel(new FlowLayout());
        questionLabel = new JLabel("Question: ", SwingConstants.CENTER);
        JPanel answerPanel = new JPanel(new GridLayout(4, 1));

        answerPanel.add(answer1Button = new JButton());
        answerPanel.add(answer2Button = new JButton());
        answerPanel.add(answer3Button = new JButton());
        answerPanel.add(answer4Button = new JButton());


        panel.add(questionLabel, BorderLayout.NORTH);
        panel.add(answerPanel, BorderLayout.CENTER);
        return panel;
    }

    public void populateQuestionPanel(Questions questionObj){
        String question = questionObj.getQuestion();
        String[] alternatives = questionObj.getAnswers();

        questionLabel.setText(question);

        answer1Button.setText(alternatives[0]);
        answer2Button.setText(alternatives[1]);
        answer3Button.setText(alternatives[2]);
        answer4Button.setText(alternatives[3]);
    }

    private JPanel createUserResultFrame() {
        JPanel panel = new JPanel(new BorderLayout());
        isCorrectlabel = new JLabel("Your Answer: ", SwingConstants.CENTER);

        nextButton = new JButton("Next");

        panel.add(isCorrectlabel , BorderLayout.CENTER);
        panel.add(nextButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createOpponentResultFrame() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Your answer: Correct! Opponent: Waiting...", SwingConstants.CENTER);
        JButton nextButton = new JButton("Next");

        panel.add(label, BorderLayout.CENTER);
        panel.add(nextButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createFinalResultFrame() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Final Results: ", SwingConstants.CENTER);
        JButton restartButton = new JButton("Play Again");

        panel.add(label, BorderLayout.CENTER);
        panel.add(restartButton, BorderLayout.SOUTH);
        return panel;
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizFrame::new); //vad betyder det här?
    }*/

    public JButton getLoginButton() {
        return loginButton;
    }

    public JTextField getUserField() {
        return userField;
    }

    public JButton getStartGameButton(){ return startGameButton;}

    public JButton getExitGameButton(){ return exitGameButton;}

    public JButton getNextButton(){return nextButton;}

    public JButton getCategory1Button() {return category1Button;}

    public JButton getCategory2Button() {return category2Button;}

    public JButton getCategory3Button() {return category3Button;}

    public JButton getCategory4Button() {return category4Button;}

    public JLabel getIsCorrectlabel() {return isCorrectlabel;}

    public JButton getAnswer1Button() {return answer1Button;}

    public JButton getAnswer2Button() {return answer2Button;}

    public JButton getAnswer3Button() {return answer3Button;}

    public JButton getAnswer4Button() {return answer4Button;}

    public int getRandomQuestion(){return randomQuestion;}

    public int getCorrectAnswerIndex(){return correctAnswerIndex;}

    public void setUserCategoryChoice(String category){this.userCategoryChoice = category;}

    public String getUserCategoryChoice(){return userCategoryChoice;}

}