package QuizGame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

    public class QuestionDatabase {
        private List<Questions> musicQuestions;
        private List<Questions> sportQuestions;
        private List<Questions> filmQuestions;
        private List<Questions> itQuestions;
        private List<Questions> animalsQuestions;
        private List<Questions> geographyQuestions;

        public QuestionDatabase() {
            musicQuestions = new ArrayList<>();
            sportQuestions = new ArrayList<>();
            filmQuestions = new ArrayList<>();
            itQuestions = new ArrayList<>();
            animalsQuestions = new ArrayList<>();
            geographyQuestions = new ArrayList<>();
        }

        public List <Questions> addQuestionsForCategory(String category){

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
            }if(filmQuestions.isEmpty()){
                filmQuestions.add(new Questions("What is the name of the foley artist who designed the famous sounds of Star Wars, including Chewbacca's roar and R2D2's beeps and whistles?", new String []{"Hank Bowley", "Tom Sagan", "Ben Burtt", "Luke Edward"}, 3, eCategoryType.MOVIES));
                filmQuestions.add(new Questions("Which actor played the main character in the 1990 film &quot;Edward Scissorhands&quot;?", new String[]{"Clint Eastwood", "Leonardo Dicaprio", "Ben Stiller", "Johnny Depp"}, 4, eCategoryType.MOVIES));
                filmQuestions.add(new Questions("Which of the following films was NOT directed by Quentin Tarantino?", new String[]{"From Dusk till Dawn", "Jackie Brown","Pulp Fiction", "Reservoir Dogs"}, 1, eCategoryType.MOVIES));
                filmQuestions.add(new Questions("What was another suggested name for, the 1985 film, Back to the Future?", new String[]{"Hill Valley Time Travelers	", "The Time Travelers","Spaceman From Pluto", 	"The Lucky Man"}, 3, eCategoryType.MOVIES));
            }if(itQuestions.isEmpty()){
                itQuestions.add(new Questions("Which of the following languages is used as a scripting language in the Unity 3D game engine?", new String []{"C++", "Objective-C", "C#", "Java"}, 3, eCategoryType.IT));
                itQuestions.add(new Questions("Which coding language was the #1 programming language in terms of usage on GitHub in 2015?", new String[]{"PHP", "Python", "C#", "JavaScript"} , 4, eCategoryType.IT));
                itQuestions.add(new Questions(	"Which programming language was developed by Sun Microsystems in 1995?", new String[]{"Solaris OS", "C++", "Python", "Java"},4, eCategoryType.IT));
                itQuestions.add(new Questions("Generally, which component of a computer draws the most power?", new String[]{	"Hard Drive", "Video Card",	"Processor","Power Supply"},2, eCategoryType.IT));
            }if (animalsQuestions.isEmpty()){
                animalsQuestions.add(new Questions("Cashmere is the wool from which kind of animal?", new String[]{"Goat", "Sheep", "Camel", "Llama"}, 1, eCategoryType.ANIMALS));
                animalsQuestions.add(new Questions("What dog breed is one of the oldest breeds of dog and has flourished since before 400 BCE.", new String[]{"Bulldogs", "Boxers", "Chihuahua", "Pugs"}, 4, eCategoryType.ANIMALS));
                animalsQuestions.add(new Questions(	"What is the name for a male bee that comes from an unfertilized egg?", new String[]{"Drone", "Soldier", "Worker", "Male"}, 1, eCategoryType.ANIMALS));
                animalsQuestions.add(new Questions("What color/colour is a polar bear&#039;s skin?", new String[]{"White", "Green", "Black", "Pink"}, 3, eCategoryType.ANIMALS));
            }if(geographyQuestions.isEmpty()){
                geographyQuestions.add(new Questions("What is the largest Muslim country in the world?", new String[]{"Pakistan", "Saudi Arabia", "Iran", "Indonesia"}, 4, eCategoryType.GEOGRAPHY));
                geographyQuestions.add(new Questions("Which of the following countries banned the use of personal genetic ancestry tests?", new String[]{"Austria", "Germany", "Canada", "Denmark"}, 2, eCategoryType.GEOGRAPHY));
                geographyQuestions.add(new Questions("What is the name of the former country that was succeeded by countries such as Serbia, Croatia and Slovenia?", new String[]{"Yugoslavia", "Abkhazia", "South Ossetia", "Yugoslavia"}, 4, eCategoryType.GEOGRAPHY));
                geographyQuestions.add(new Questions("What is the capital city of New Zealand?", new String[]{"Auckland", "Christchurch", "Melbourne", "Wellington"}, 4, eCategoryType.GEOGRAPHY));
            }
            return getQuestionsByCategory(category);
        }

        public List<Questions> getQuestionsByCategory(String category) {
            List<Questions> shuffleQuestions;
            switch (category) {
                case "music":
                    shuffleQuestions = new ArrayList<>(musicQuestions);
                    break;
                case "sport":
                    shuffleQuestions = new ArrayList<>(sportQuestions);
                    break;
                case "film":
                    shuffleQuestions = new ArrayList<>(filmQuestions);
                    break;
                case "it":
                    shuffleQuestions = new ArrayList<>(itQuestions);
                    break;
                case "animals":
                      shuffleQuestions = new ArrayList<>(animalsQuestions);
                      break;
                case "geography":
                      shuffleQuestions = new ArrayList<>(geographyQuestions);
                      break;
                default:
                    return Collections.emptyList();
            }
            Collections.shuffle(shuffleQuestions);
            return shuffleQuestions;
        }
    }


