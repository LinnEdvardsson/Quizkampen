package QuizGame;

import java.io.Serializable;

public class Questions implements Serializable {
        private String question;
        private String[] answers;
        private String correctAnswer;

    public Questions(String question, String[] answers, String correctAnswer, eCategoryType categoryType) {
            this.question = question;
            this.answers = answers;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestion() {
            return question;
        }

        public String[] getAnswers() {
            return answers;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public boolean isCorrect(String answer){
            return answer.equals(correctAnswer);
        }

}

