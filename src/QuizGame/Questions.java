package QuizGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Questions {
        private final String question;
        private final String[] answers;
        private final int correctAnswerIndex;
        private final eCategoryType categoryType;
       // private List<Questions> gameQuestions = new ArrayList<Questions>();

        public Questions(String question, String[] answers, int correctAnswerIndex, eCategoryType categoryType) {
            this.question = question;
            this.answers = answers;
            this.correctAnswerIndex = correctAnswerIndex;
            this.categoryType = categoryType;
           // gameQuestions = new ArrayList<Questions>();
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

        public eCategoryType getCategoryType() {
            return categoryType;
        }

       /* public List<Questions> addQuestionsForCategory(String category) throws NullPointerException {
        this.category = category;

        switch (category) {
            case "Music":

                gameQuestions.add(new Questions("Which musical instrument does Elton John play?", new String[]{"Guitar", "Drums", "Saxophone", "Piano"}, 3, eCategoryType.MUSIC));
                gameQuestions.add(new Questions("Which classical composer became deaf later in life?", new String[]{"Mozart", "Bach", "Beethoven", "Tchaikovsky"}, 2, eCategoryType.MUSIC));
                gameQuestions.add(new Questions("Who is known as the King of Pop?", new String[]{"Freddie Mercury", "Elvis Presley", "Michael Jackson", "Prince"}, 2, eCategoryType.MUSIC));
                gameQuestions.add(new Questions("The 'Mamma Mia' Musical is based around the music of which band?", new String[]{"Take That", "Sarek", "ABBA", "Vikingarna"}, 2, eCategoryType.MUSIC));

            case "Sport":
                gameQuestions.add(new Questions("What sport is best known as the ‘king of sports?", new String[]{"Basket Ball", "Hockey", "Fotball", "Golf"}, 2, eCategoryType.SPORT));
                gameQuestions.add(new Questions("What do you call it when a bowler makes three strikes in a row?", new String[]{"Meatloaf", "Turkey", "Hole in one", "Strike"}, 1, eCategoryType.SPORT));
                gameQuestions.add(new Questions("How many dimples does an average golf ball have?", new String[]{"100-150", "300-500", "50-100", "350-600"}, 2, eCategoryType.SPORT));
                gameQuestions.add(new Questions("What type of race is the Tour de France", new String[]{"by Car", "by Bicycle", "by Skates", "by Horse"}, 1, eCategoryType.SPORT));


            default:
                System.out.println("Invalid category.");
        }
        Collections.shuffle(gameQuestions);
        return gameQuestions;
    }*/
}

