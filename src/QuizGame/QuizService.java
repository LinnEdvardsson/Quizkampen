package QuizGame;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizService {

    private String categoryname;
    private List<Questions> questions;

    public QuizService() {
        this.questions = new ArrayList<>();
    }

    public void addQuestionsForCategory(String categoryname) {
        this.categoryname = categoryname;

        switch (categoryname) {
            case "Music":

                questions.add(new Questions("Which musical instrument does Elton John play?", new String[]{"Guitar", "Drums", "Saxophone", "Piano"}, 3, eCategoryType.MUSIC));
                questions.add(new Questions("Which classical composer became deaf later in life?", new String[]{"Mozart", "Bach", "Beethoven", "Tchaikovsky"}, 2, eCategoryType.MUSIC));
                questions.add(new Questions("Who is known as the King of Pop?", new String[]{"Freddie Mercury", "Elvis Presley", "Michael Jackson", "Prince"}, 2, eCategoryType.MUSIC));
                questions.add(new Questions("The 'Mamma Mia' Musical is based around the music of which band?", new String[]{"Take That", "Sarek", "ABBA", "Vikingarna"}, 2, eCategoryType.MUSIC));

            case "Sport":
                questions.add(new Questions("What sport is best known as the ‘king of sports?", new String[]{"Basket Ball", "Hockey", "Fotball", "Golf"}, 2, eCategoryType.SPORT));
                questions.add(new Questions("What do you call it when a bowler makes three strikes in a row?", new String[]{"Meatloaf", "Turkey", "Hole in one", "Strike"}, 1, eCategoryType.SPORT));
                questions.add(new Questions("How many dimples does an average golf ball have?", new String[]{"100-150", "300-500", "50-100", "350-600"}, 2, eCategoryType.SPORT));
                questions.add(new Questions("What type of race is the Tour de France", new String[]{"by Car", "by Bicycle", "by Skates", "by Horse"}, 1, eCategoryType.SPORT));

            /*default:
                System.out.println("Invalid category.");*/
        }

    }

    //Metod för att starta frågesporten

    public void startQuiz(){
        Scanner scanner = new Scanner(System.in);

        while(true) {
        System.out.println("What Category? Music or Sport?");
        String input = scanner.nextLine();
        addQuestionsForCategory(input);

        if(!input.isEmpty()) {
            for(Questions question : questions) {
                System.out.println(question.getQuestiontext());
                String[] answers = question.getAnswers();
                for(int i = 0; i < answers.length; i++) {
                    System.out.println((i+1) + ". " + answers[i]);
                }
                System.out.println("Enter your answer");
                int answered = Integer.parseInt(scanner.nextLine()) - 1;
                if (answered == question.getCorrectAnswerIndex()) {
                    System.out.println("Correct!");
                } else {
                    System.out.println("Wrong! The correct answer was " + answers[question.getCorrectAnswerIndex()]);
                }
            }
        }
    }
}


    public static void main(String[] args) {
        QuizService quizService = new QuizService();
        quizService.startQuiz();
    }
    //metod frågor ska kopplas till svar med isCorrect boolean. Hämta index från lista till svarsalternativ
}

