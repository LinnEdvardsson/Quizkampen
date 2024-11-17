package QuizApp;

import javax.swing.*;
import java.awt.*;

public class QuizAppFrame extends JFrame {

    public void baseFrame() {
        setSize(500,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    JPanel basePanel = new JPanel();
    JPanel playerPanel = new JPanel();
    JPanel scorePanel = new JPanel();
    JPanel connectionPanel = new JPanel();


    JLabel player1 = new JLabel("Player 1");
    JLabel player2 = new JLabel("Player 2");
    JLabel connection = new JLabel("Waiting for all players to connect");


    public void mainFrame() {
        baseFrame();
        this.add(basePanel);
        basePanel.setLayout(new BorderLayout());
        basePanel.add(playerPanel, BorderLayout.NORTH);
        basePanel.add(scorePanel, BorderLayout.CENTER);
        basePanel.add(connectionPanel, BorderLayout.SOUTH);
        playerPanel.setLayout(new BorderLayout());
        playerPanel.add(player1, BorderLayout.WEST);
        playerPanel.add(player2, BorderLayout.EAST);
        connectionPanel.add(connection);


    }

//    public static void main(String[] args) {
//        new QuizAppFrame();
//    }
}
