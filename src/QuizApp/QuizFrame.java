package QuizApp;

import javax.swing.*;
import java.awt.*;

public class QuizFrame {

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JButton loginButton;
    private JTextField userField;
    private String usernamne;

    public QuizFrame() { //alla som ska ha actionlyssnare deklaraeras här och implemeter i egna metoder.
        setupGUI();
    }

    private void setupGUI() {
        frame = new JFrame("Quiz Game");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createLoginFrame(), "Login"); //mot server?
        mainPanel.add(createOpponentFrame(), "Opponent");
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


    public JPanel createLoginFrame() {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        userField = new JTextField("Enter your username:", SwingConstants.CENTER);
        loginButton = new JButton("Login");

        panel.add(userField);
        panel.add(loginButton);
        return panel;
    }

    private JPanel createOpponentFrame() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Finding an opponent...", SwingConstants.CENTER);
        JButton proceedButton = new JButton("Next");

        panel.add(label, BorderLayout.CENTER);
        panel.add(proceedButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createCategoryFrame() {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        JLabel label = new JLabel("Choose a category:", SwingConstants.CENTER);
        JButton category1Button = new JButton("Music");
        JButton category2Button = new JButton("Sports");

        panel.add(label);
        panel.add(category1Button);
        panel.add(category2Button);
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

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizFrame::new); //vad betyder det här?
    }*/

    public JButton getLoginButton() {
        return loginButton;
    }
}