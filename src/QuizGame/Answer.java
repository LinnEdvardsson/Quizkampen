package QuizGame;

public class Answer {
    private String answer;
    private String isCorrect;

    public Answer(String answer, String isCorrect) {
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    public String getAnswer() {
        return answer;
    }

    public String isCorrect() {
        return isCorrect;
    }

    /*public String toString() {
        return (isCorrect() ? "Correct" : "Incorrect";
    }*/
}
