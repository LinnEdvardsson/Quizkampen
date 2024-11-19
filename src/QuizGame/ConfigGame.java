package QuizGame;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigGame {

    private int roundsPerGame;
    private int questionsPerRound;

    public void loadSettings() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("server/setting.properties"));
            roundsPerGame = Integer.parseInt(prop.getProperty("roundsPerGame"));
            questionsPerRound = Integer.parseInt(prop.getProperty("questionsPerRound"));

        } catch (IOException e) {
            e.printStackTrace();
            roundsPerGame = 2; //om filen inte kan laddas är det här ett default-värde
            questionsPerRound = 4;
        }
    }

    public int getRoundsPerGame() {

        return roundsPerGame;
    }

    public int getQuestionsPerRound() {

        return questionsPerRound;
    }

}
