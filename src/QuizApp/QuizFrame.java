package QuizApp;

import QuizGame.Questions;
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
    private JButton answer4Button;;
    private JLabel questionLabel;
    private JButton nextButton;
    private JLabel isCorrectlabel;
    private JLabel player1label;
    private JLabel player2label;
    private JButton exitGameButton1;

    public QuizFrame() {
        setupGUI();
    }

    public void setupGUI() {
        frame = new JFrame("Quiz Game");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);


        mainPanel.add(createLoginFrame(), "Login");
        mainPanel.add(createWelcomeFrame(), "Welcome");
        mainPanel.add(createQueueFrame(), "Queue");
        mainPanel.add(createCategoryFrame(), "Category");
        mainPanel.add(createQuestionFrame(), "Question");
        mainPanel.add(createUserResultFrame(), "UserResult");
        mainPanel.add(createFinalResultFrame(), "FinalResult");


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
        JLabel lable = new JLabel("Enter username", SwingConstants.CENTER);
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

    private JPanel createQueueFrame(){
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Waiting for the other player");
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createCategoryFrame() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
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
        JPanel panel = new JPanel(new BorderLayout());
        JPanel northPanel = new JPanel();
        JPanel centerpanel = new JPanel(new BorderLayout());
        questionLabel = new JLabel();
        JPanel answerPanel = new JPanel(new GridLayout(4, 1));

        answerPanel.add(answer1Button = new JButton());
        answerPanel.add(answer2Button = new JButton());
        answerPanel.add(answer3Button = new JButton());
        answerPanel.add(answer4Button = new JButton());

        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(centerpanel, BorderLayout.CENTER);
        northPanel.add(questionLabel, BorderLayout.NORTH);
        centerpanel.add(answerPanel, BorderLayout.CENTER);

        return panel;

    }
        /// Metod för att fylla svarsknappar, hämtas i client-klassen
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

    private JPanel createFinalResultFrame() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Final Results: ", SwingConstants.CENTER);
        player1label = new JLabel();
        player2label = new JLabel();
        exitGameButton1 = new JButton("Exit Game");

        panel.add(player1label, BorderLayout.WEST);
        panel.add(player2label, BorderLayout.EAST);
        panel.add(label, BorderLayout.CENTER);
        panel.add(exitGameButton1, BorderLayout.SOUTH);
        return panel;
    }

    public void setScoreLabels(String myName, int myScore, String opponentsName, int opponentsScore){
        player1label.setText(myName + ": " + myScore);
        player2label.setText(opponentsName + ": " + opponentsScore);
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JTextField getUserField() {return userField; }

    public JButton getStartGameButton(){ return startGameButton;}

    public JButton getExitGameButton(){ return exitGameButton;}

    public JButton getNextButton(){return nextButton;}

    public JButton getCategory1Button() {return category1Button;}

    public JButton getCategory2Button() {return category2Button;}

    public JButton getCategory3Button() {return category3Button;}

    public JButton getCategory4Button() {return category4Button;}

    public JLabel getIsCorrectlabel() {return isCorrectlabel;}

    public JButton getExitGameButton1() {return exitGameButton1;}
}