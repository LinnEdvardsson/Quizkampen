package QuizGame;

import java.io.Serializable;

public class PlayerScore implements ScoreKeeping {
private int currentScore = 0;


    @Override
    public void updateScore(int points) {
    currentScore = currentScore + points;
    }

    @Override
    public int getScore() {
        return currentScore;
    }

    @Override
    public void resetScore() {
    currentScore = 0;
    }
}
