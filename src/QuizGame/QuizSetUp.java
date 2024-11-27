package QuizGame;
import QuizApp.QuizFrame;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class QuizSetUp {

    private QuestionDatabase questionDatabase;
    Scanner scanner;
    List <Questions> questions;
    private int roundsPerGame;
    private int questionsPerRound;


    private PlayerScore playerScore;

    public QuizSetUp() {
        this.questionDatabase = new QuestionDatabase();
        scanner = new Scanner(System.in);
        loadSettings();
    }

    public void loadGameSettings() {
        roundsPerGame = getRoundsPerGame();
        questionsPerRound = getQuestionsPerRound();
        questionsPerRound = Math.min(getQuestionsPerRound(), questions.size());

    }

    public static List<eCategoryType> getCategories(){
        List<eCategoryType> allAvailableCategories = new ArrayList<>(List.of(eCategoryType.values()));
        Collections.shuffle(allAvailableCategories);

        List<eCategoryType> categorySet = new ArrayList<>(allAvailableCategories.subList(0, 2));
        return categorySet;
    }

    public void loadSettings() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/server/setting.properties"));
            roundsPerGame = Integer.parseInt(prop.getProperty("roundsPerGame"));
            questionsPerRound = Integer.parseInt(prop.getProperty("questionsPerRound"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Questions> getQuestions(eCategoryType category){
        return questionDatabase.addQuestionsForCategory(eCategoryType.valueOf(String.valueOf(category)));
    }

    public int getRoundsPerGame() {
        return roundsPerGame;
    }

    public int getQuestionsPerRound() {
        return questionsPerRound;
    }
}



//    public void playAllRounds(List<eCategoryType> category) {              //serverSidan?
//        int roundsPerGame = configGame.getRoundsPerGame();
//        for (int round = 1; round <= roundsPerGame; round++) {
//            System.out.println("\nRound " + round + " of " + roundsPerGame);
//            playRound(category);
//
//        }
//    }
//
//    public int playRound(List<eCategoryType> category) {
//        List<Questions> questions = questionDatabase.addQuestionsForCategory(category.toString());
//        int questionsToAsk = Math.min(configGame.getQuestionsPerRound(), questions.size());
//        for (int q = 0; q < questionsToAsk; q++) {
//            askQuestion(questions.get(q), q + 1, questionsToAsk);
//        }
//        return questionsToAsk;
//    }

//    public void askQuestion(Questions question, int questionNumber, int totalQuestions) {
//        displayQuestion(question, questionNumber, totalQuestions);
//        displayAnswerOptions(question.getAnswers());
//        processAnswer(question);
//    }



//
//    public void displayQuestion(Questions question, int questionNumber, int totalQuestions) {
//        System.out.println("\nQuestion " + questionNumber + " of " + totalQuestions);
//        System.out.println(question.getQuestiontext());
//    }
//
//    public void displayAnswerOptions(String[] answers) {
//        for (int i = 0; i < answers.length; i++) {
//            System.out.println((i + 1) + ". " + answers[i]);
//        }
//    }
//
//    public void processAnswer(Questions question) {
//        System.out.println("Enter your answer");
//        int answered = Integer.parseInt(scanner.nextLine()) - 1;
//        String[] answers = question.getAnswers();
//
//        if (answered == question.getCorrectAnswerIndex()) {
//            System.out.println("Correct!");
//            playerScore.updateScore(1);
//
//        } else {
//            System.out.println("Wrong! The correct answer was " +
//                    answers[question.getCorrectAnswerIndex()]);
//        }
//    }
