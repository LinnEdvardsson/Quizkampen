package QuizGame;

public class Questions {
        private String question;
        private String[] answers;
        private int correctAnswerIndex;

    public Questions(String question, String[] answers, int correctAnswerIndex, eCategoryType categoryType) {
            this.question = question;
            this.answers = answers;
            this.correctAnswerIndex = correctAnswerIndex;
        }

        public String getQuestiontext() {
            return question;
        }

        public String[] getAnswers() {
            return answers;
        }

        public int getCorrectAnswerIndex() {
            return correctAnswerIndex;
        }

}

