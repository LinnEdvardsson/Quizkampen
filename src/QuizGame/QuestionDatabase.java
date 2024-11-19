package QuizGame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

    public class QuestionDatabase {
        private List<Questions> musicQuestions;
        private List<Questions> sportQuestions;
       // private eCategoryType category;

        public QuestionDatabase() {
            musicQuestions = new ArrayList<>();
            sportQuestions = new ArrayList<>();
            addQuestionsForCategory();
        }

        private void addQuestionsForCategory() {
            musicQuestions.add(new Questions("Which musical instrument does Elton John play?", new String[]{"Guitar", "Drums", "Saxophone", "Piano"}, 3, eCategoryType.MUSIC));
            musicQuestions.add(new Questions("Which classical composer became deaf later in life?", new String[]{"Mozart", "Bach", "Beethoven", "Tchaikovsky"}, 2, eCategoryType.MUSIC));
            musicQuestions.add(new Questions("Who is known as the King of Pop?", new String[]{"Freddie Mercury", "Elvis Presley", "Michael Jackson", "Prince"}, 2, eCategoryType.MUSIC));
            musicQuestions.add(new Questions("The 'Mamma Mia' Musical is based around the music of which band?", new String[]{"Take That", "Sarek", "ABBA", "Vikingarna"}, 2, eCategoryType.MUSIC));

            sportQuestions.add(new Questions("What sport is best known as the ‘king of sports?", new String[]{"Basketball", "Hockey", "Football", "Golf"}, 2, eCategoryType.SPORT));
            sportQuestions.add(new Questions("What do you call it when a bowler makes three strikes in a row?", new String[]{"Meatloaf", "Turkey", "Hole in one", "Strike"}, 1, eCategoryType.SPORT));
            sportQuestions.add(new Questions("How many dimples does an average golf ball have?", new String[]{"100-150", "300-500", "50-100", "350-600"}, 1, eCategoryType.SPORT));
            sportQuestions.add(new Questions("What type of race is the Tour de France?", new String[]{"by Car", "by Bicycle", "by Skates", "by Horse"}, 1, eCategoryType.SPORT));
        }

       /* public List <Questions> addQuestionsForCategory(eCategoryType category){
            this.category = category;
            if(musicQuestions.isEmpty()) {
                musicQuestions.add(new Questions("Which musical instrument does Elton John play?", new String[]{"Guitar", "Drums", "Saxophone", "Piano"}, 3, eCategoryType.MUSIC));
                musicQuestions.add(new Questions("Which classical composer became deaf later in life?", new String[]{"Mozart", "Bach", "Beethoven", "Tchaikovsky"}, 2, eCategoryType.MUSIC));
                musicQuestions.add(new Questions("Who is known as the King of Pop?", new String[]{"Freddie Mercury", "Elvis Presley", "Michael Jackson", "Prince"}, 2, eCategoryType.MUSIC));
                musicQuestions.add(new Questions("The 'Mamma Mia' Musical is based around the music of which band?", new String[]{"Take That", "Sarek", "ABBA", "Vikingarna"}, 2, eCategoryType.MUSIC));
            } if(sportQuestions.isEmpty()) {
                sportQuestions.add(new Questions("What sport is best known as the ‘king of sports?", new String[]{"Basketball", "Hockey", "Football", "Golf"}, 2, eCategoryType.SPORT));
                sportQuestions.add(new Questions("What do you call it when a bowler makes three strikes in a row?", new String[]{"Meatloaf", "Turkey", "Hole in one", "Strike"}, 1, eCategoryType.SPORT));
                sportQuestions.add(new Questions("How many dimples does an average golf ball have?", new String[]{"100-150", "300-500", "50-100", "350-600"}, 1, eCategoryType.SPORT));
                sportQuestions.add(new Questions("What type of race is the Tour de France?", new String[]{"by Car", "by Bicycle", "by Skates", "by Horse"}, 1, eCategoryType.SPORT));
            }
            return getQuestionsByCategory(category);
        } */

        public List<Questions> getQuestionsByCategory(eCategoryType category) {
            switch (category) {
                case MUSIC:
                    return new ArrayList<>(musicQuestions);
                case SPORT:
                    return new ArrayList<>(sportQuestions);
                default:
                    return Collections.emptyList();
            }
        }
    }



