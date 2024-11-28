package QuizGame;
import QuizApp.QuizFrame;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class QuizSetUp {

    /// Sitter på viss spellogik, text hämtning frågor/kategorier och settings från properties

    private QuestionDatabase questionDatabase;
    Scanner scanner;
    List <Questions> questions;
    private int roundsPerGame;
    private int questionsPerRound;

    public QuizSetUp() {
        this.questionDatabase = new QuestionDatabase();
        scanner = new Scanner(System.in);
        loadSettings();
    }

//    public void loadGameSettings() {
//        roundsPerGame = getRoundsPerGame();
//        questionsPerRound = getQuestionsPerRound();
//        questionsPerRound = Math.min(getQuestionsPerRound(), questions.size());
//
//    }

    public static List<eCategoryType> getCategories(){
        List<eCategoryType> allAvailableCategories = new ArrayList<>(List.of(eCategoryType.values()));
        Collections.shuffle(allAvailableCategories);

        List<eCategoryType> categorySet = new ArrayList<>(allAvailableCategories.subList(0, 4));
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
