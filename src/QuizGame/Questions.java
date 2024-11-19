package QuizGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Questions {
        private String category;
        private String question;
        private String[] answers;
        private int correctAnswerIndex;
        private eCategoryType categoryType;
        private List<Questions> gameQuestions = new ArrayList<Questions>();

        public Questions(String question, String[] answers, int correctAnswerIndex, eCategoryType categoryType) {
            this.question = question;
            this.answers = answers;
            this.correctAnswerIndex = correctAnswerIndex;
            this.categoryType = categoryType;
            gameQuestions = new ArrayList<Questions>();
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

