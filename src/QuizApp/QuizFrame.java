package QuizApp;

import javax.swing.*;
import java.awt.*;

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
    private JButton category3Button = new JButton();
    private JButton category4Button = new JButton();

    public QuizFrame() { //alla som ska ha actionlyssnare deklaraeras här och implemeter i egna metoder.
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
        mainPanel.add(createOpponentResultFrame(), "OpponentResult");
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


    public JPanel createLoginFrame() {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        JLabel lable = new JLabel("Enter username");
        userField = new JTextField();
        loginButton = new JButton("Login");

        panel.add(lable, BorderLayout.NORTH);
        panel.add(userField, BorderLayout.CENTER);
        panel.add(loginButton, BorderLayout.SOUTH);
        return panel;
    }


    private JPanel createWelcomeFrame() {
        JPanel welcomePanel = new JPanel(new BorderLayout());
        startGameButton = new JButton("Start Game");
        JTextArea textArea = new JTextArea("WELCOME TO QUIZ GAME");
        exitGameButton = new JButton("Exit Game");
        welcomePanel.add(textArea, BorderLayout.CENTER);


        welcomePanel.add(startGameButton, BorderLayout.NORTH);
        welcomePanel.add(exitGameButton, BorderLayout.SOUTH);
        return welcomePanel;
    }


    private JPanel createQueueFrame(){
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("In queue.....");
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }


    private JPanel createCategoryFrame() {
        JPanel panel = new JPanel(new GridLayout(3, 1));
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
        JLabel questionLabel = new JLabel("Question: ", SwingConstants.CENTER);
        JPanel answerPanel = new JPanel(new GridLayout(4, 1));

        for (int i = 1; i <= 4; i++) {
            JButton answerButton = new JButton("Answer " + i);
            answerPanel.add(answerButton);
        }

        panel.add(questionLabel, BorderLayout.NORTH);
        panel.add(answerPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createUserResultFrame() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Your Answer: Correct!", SwingConstants.CENTER);
        JButton nextButton = new JButton("Next");

        panel.add(label, BorderLayout.CENTER);
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
        JLabel label = new JLabel("Final Results:\nYou: 2 | Opponent: 1", SwingConstants.CENTER);
        JButton restartButton = new JButton("Play Again");

        panel.add(label, BorderLayout.CENTER);
        panel.add(restartButton, BorderLayout.SOUTH);
        return panel;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JTextField getUserField() {
        return userField;
    }

    public JButton getStartGameButton(){ return startGameButton;}

    public JButton getExitGameButton(){ return exitGameButton;}

    public JButton getCategory1Button() {return category1Button;}

    public JButton getCategory2Button() {return category2Button;}

    public JButton getCategory3Button() {return category3Button;}

    public JButton getCategory4Button() {return category4Button;}
}