package QuizGame;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigGame {

    private int roundsPerGame;
    private int questionsPerRound;

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

        public void setGameRounds() {
            loadSettings();
            for (int round = 1; round <= roundsPerGame; round++) {
                System.out.println("Round " + round + " of " + roundsPerGame);
                for (int q = 1; q <= questionsPerRound; q++) {
                    if (q == 0) {
                        System.out.println("No more questions available.");
                        break;
                    }
                }
            }
        }

}
