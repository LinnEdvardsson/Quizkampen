package QuizGame;

public class Answer {
    private String answer;
    private boolean isCorrect; //gjorde denna till bool

    public Answer(String answer, boolean isCorrect) {
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public String toString() {
        return isCorrect ? "Correct" : "Incorrect";
    }
}
