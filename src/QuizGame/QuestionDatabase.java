package QuizGame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

    public class QuestionDatabase {
        private List<Questions> musicQuestions;
        private List<Questions> sportQuestions;

        public QuestionDatabase() {
            musicQuestions = new ArrayList<>();
            sportQuestions = new ArrayList<>();
        }

        public List <Questions> addQuestionsForCategory(eCategoryType categoryType){

            if(musicQuestions.isEmpty()) {
                musicQuestions.add(new Questions("Which musical instrument does Elton John play?", new String[]{"Guitar", "Drums", "Saxophone", "Piano"}, "Piano", eCategoryType.MUSIC));
                musicQuestions.add(new Questions("Which classical composer became deaf later in life?", new String[]{"Mozart", "Bach", "Beethoven", "Tchaikovsky"}, "Beethoven", eCategoryType.MUSIC));
                musicQuestions.add(new Questions("Who is known as the King of Pop?", new String[]{"Freddie Mercury", "Elvis Presley", "Michael Jackson", "Prince"}, "Michael Jackson", eCategoryType.MUSIC));
                musicQuestions.add(new Questions("The 'Mamma Mia' Musical is based around the music of which band?", new String[]{"Take That", "Sarek", "ABBA", "Vikingarna"}, "ABBA", eCategoryType.MUSIC));
            } if(sportQuestions.isEmpty()) {
                sportQuestions.add(new Questions("What sport is best known as the ‘king of sports?", new String[]{"Basketball", "Hockey", "Football", "Golf"}, "Football", eCategoryType.SPORT));
                sportQuestions.add(new Questions("What do you call it when a bowler makes three strikes in a row?", new String[]{"Meatloaf", "Turkey", "Hole in one", "Strike"}, "Turkey", eCategoryType.SPORT));
                sportQuestions.add(new Questions("How many dimples does an average golf ball have?", new String[]{"100-150", "300-500", "50-100", "350-600"}, "300-500", eCategoryType.SPORT));
                sportQuestions.add(new Questions("What type of race is the Tour de France?", new String[]{"by Car", "by Bicycle", "by Skates", "by Horse"}, "by Bicycle", eCategoryType.SPORT));
            }
            return getQuestionsByCategory(categoryType);
        }

        public List<Questions> getQuestionsByCategory(eCategoryType categoryType) {
            switch (categoryType) {
                case MUSIC:
                    return musicQuestions;
                case SPORT:
                   return sportQuestions;
                default:
                    return Collections.emptyList();
            }
        }
    }

