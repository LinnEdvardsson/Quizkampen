package QuizGame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static QuizGame.eCategoryType.MUSIC;
import static QuizGame.eCategoryType.SPORT;

public class QuestionDatabase {
    private List<Questions> musicQuestions;
    private List<Questions> sportQuestions;
    private List<Questions> movieQuestions;
    private List<Questions> itQuestions;
    private List<Questions> animalsQuestions;
    private List<Questions> geographyQuestions;

    public QuestionDatabase() {
        musicQuestions = new ArrayList<>();
        sportQuestions = new ArrayList<>();
        movieQuestions = new ArrayList<>();
        itQuestions = new ArrayList<>();
        animalsQuestions = new ArrayList<>();
        geographyQuestions = new ArrayList<>();
    }

    public List<Questions> addQuestionsForCategory(eCategoryType categoryType) {

        musicQuestions.add(new Questions("Which musical instrument does Elton John play?", new String[]{"Guitar", "Drums", "Saxophone", "Piano"}, "Piano", MUSIC));
        musicQuestions.add(new Questions("Which classical composer became deaf later in life?", new String[]{"Mozart", "Bach", "Beethoven", "Tchaikovsky"}, "Beethoven", MUSIC));
        musicQuestions.add(new Questions("Who is known as the King of Pop?", new String[]{"Freddie Mercury", "Elvis Presley", "Michael Jackson", "Prince"}, "Michael Jackson", MUSIC));
        musicQuestions.add(new Questions("The 'Mamma Mia' Musical is based around the music of which band?", new String[]{"Take That", "Sarek", "ABBA", "Vikingarna"}, "ABBA", MUSIC));


        sportQuestions.add(new Questions("What sport is best known as the ‘king of sports?", new String[]{"Basketball", "Hockey", "Football", "Golf"}, "Football", SPORT));
        sportQuestions.add(new Questions("What do you call it when a bowler makes three strikes in a row?", new String[]{"Meatloaf", "Turkey", "Hole in one", "Strike"}, "Turkey", SPORT));
        sportQuestions.add(new Questions("How many dimples does an average golf ball have?", new String[]{"100-150", "300-500", "50-100", "350-600"}, "300-500", SPORT));
        sportQuestions.add(new Questions("What type of race is the Tour de France?", new String[]{"by Car", "by Bicycle", "by Skates", "by Horse"}, "by Bicycle", SPORT));


        movieQuestions.add(new Questions("What is the name of the foley artist who designed the famous sounds of Star Wars, including Chewbacca's roar and R2D2's beeps and whistles?", new String[]{"Hank Bowley", "Tom Sagan", "Ben Burtt", "Luke Edward"}, "Ben Burtt", eCategoryType.MOVIES));
        movieQuestions.add(new Questions("Which actor played the main character in the 1990 film &quot;Edward Scissorhands&quot;?", new String[]{"Clint Eastwood", "Leonardo Dicaprio", "Ben Stiller", "Johnny Depp"}, "Johnny Depp", eCategoryType.MOVIES));
        movieQuestions.add(new Questions("Which of the following films was NOT directed by Quentin Tarantino?", new String[]{"From Dusk till Dawn", "Jackie Brown", "Pulp Fiction", "Reservoir Dogs"}, "From Dusk till Dawn", eCategoryType.MOVIES));
        movieQuestions.add(new Questions("What was another suggested name for, the 1985 film, Back to the Future?", new String[]{"Hill Valley Time Travelers", "The Time Travelers", "Spaceman From Pluto", "The Lucky Man"}, "Spaceman From Pluto", eCategoryType.MOVIES));

        itQuestions.add(new Questions("Which of the following languages is used as a scripting language in the Unity 3D game engine?", new String[]{"C++", "Objective-C", "C#", "Java"}, "C#", eCategoryType.IT));
        itQuestions.add(new Questions("Which coding language was the #1 programming language in terms of usage on GitHub in 2015?", new String[]{"PHP", "Python", "C#", "JavaScript"}, "JavaScript", eCategoryType.IT));
        itQuestions.add(new Questions("Which programming language was developed by Sun Microsystems in 1995?", new String[]{"Solaris OS", "C++", "Python", "Java"}, "Java", eCategoryType.IT));
        itQuestions.add(new Questions("Generally, which component of a computer draws the most power?", new String[]{"Hard Drive", "Video Card", "Processor", "Power Supply"}, "Video Card", eCategoryType.IT));

        animalsQuestions.add(new Questions("Cashmere is the wool from which kind of animal?", new String[]{"Goat", "Sheep", "Camel", "Llama"}, "Goat", eCategoryType.ANIMALS));
        animalsQuestions.add(new Questions("What dog breed is one of the oldest breeds of dog and has flourished since before 400 BCE.", new String[]{"Bulldogs", "Boxers", "Chihuahua", "Pugs"}, "Pugs", eCategoryType.ANIMALS));
        animalsQuestions.add(new Questions("What is the name for a male bee that comes from an unfertilized egg?", new String[]{"Drone", "Soldier", "Worker", "Male"}, "Drone", eCategoryType.ANIMALS));
        animalsQuestions.add(new Questions("What color/colour is a polar bear&#039;s skin?", new String[]{"White", "Green", "Black", "Pink"}, "Black", eCategoryType.ANIMALS));

        geographyQuestions.add(new Questions("What is the largest Muslim country in the world?", new String[]{"Pakistan", "Saudi Arabia", "Iran", "Indonesia"}, "Indonesia", eCategoryType.GEOGRAPHY));
        geographyQuestions.add(new Questions("Which of the following countries banned the use of personal genetic ancestry tests?", new String[]{"Austria", "Germany", "Canada", "Denmark"}, "Germany", eCategoryType.GEOGRAPHY));
        geographyQuestions.add(new Questions("What is the name of the former country that was succeeded by countries such as Serbia, Croatia and Slovenia?", new String[]{"Yugoslavia", "Abkhazia", "South Ossetia", "Yugoslavia"}, "Yugoslavia", eCategoryType.GEOGRAPHY));
        geographyQuestions.add(new Questions("What is the capital city of New Zealand?", new String[]{"Auckland", "Christchurch", "Melbourne", "Wellington"}, "Wellington", eCategoryType.GEOGRAPHY));


        return getQuestionsByCategory(categoryType);
    }

    public List<Questions> getQuestionsByCategory(eCategoryType categoryType) {
        switch (categoryType) {
            case MUSIC:
                return musicQuestions;
            case SPORT:
                return sportQuestions;
            case ANIMALS:
                return animalsQuestions;
            case GEOGRAPHY:
                return geographyQuestions;
            case IT:
                return itQuestions;
            case MOVIES:
                return movieQuestions;

            default:
                return Collections.emptyList();
        }
    }
}





