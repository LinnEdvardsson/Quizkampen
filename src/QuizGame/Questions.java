package QuizGame;

public class Questions {

        private String questiontext;
        private String[] answers;
        private int correctAnswerIndex;
        private eCategoryType category;

        public Questions(String questiontext, String[] answers, int correctAnswerIndex, eCategoryType category) {
            this.questiontext = questiontext;
            this.answers = answers;
            this.correctAnswerIndex = correctAnswerIndex;
            this.category = category;
        }

        public String getQuestiontext() {
            return questiontext;
        }

        public String[] getAnswers() {
            return answers;
        }

        public int getCorrectAnswerIndex() {
            return correctAnswerIndex;
        }
    }

