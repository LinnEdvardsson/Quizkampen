package QuizGame;

public class Questions {
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

}

