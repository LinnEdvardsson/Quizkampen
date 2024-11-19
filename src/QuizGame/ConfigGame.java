package QuizGame;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigGame {

    private int roundsPerGame;
    private int questionsPerRound;

    public int getRoundsPerGame() {
        return roundsPerGame;
    }

    public int getQuestionsPerRound() {
        return questionsPerRound;
    }

    private void loadSettings() {
            Properties prop = new Properties();
            try {
                prop.load(new FileInputStream("server/setting.properties"));
                roundsPerGame = Integer.parseInt(prop.getProperty("roundsPerGame"));
                questionsPerRound = Integer.parseInt(prop.getProperty("questionsPerRound"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public void initializeGameSettings() {
        loadSettings();
        for (int round = 1; round <= getRoundsPerGame(); round++) {
            for(int question = 0; question <= getQuestionsPerRound(); question++) {

            }
        }
    }

}
