package QuizGame;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigGame {

    private int roundsPerGame;
    private int questionsPerRound;

        public void loadSettings() {
            Properties prop = new Properties();
            try {
               // String filePath = "server" + File.separator + "setting.properties";
                //prop.load(new FileInputStream(filePath));
                prop.load(new FileInputStream("src/server/setting.properties"));
                roundsPerGame = Integer.parseInt(prop.getProperty("roundsPerGame"));
                questionsPerRound = Integer.parseInt(prop.getProperty("questionsPerRound"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public int getRoundsPerGame() {
        return roundsPerGame;
    }

    public int getQuestionsPerRound() {
        return questionsPerRound;
    }
}
