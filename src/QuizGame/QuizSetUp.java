package QuizGame;
import java.util.List;
import java.util.Scanner;

public class QuizSetUp {
    private QuestionDatabase questionDatabase;
    private ConfigGame configGame;
    Scanner scanner;

    public QuizSetUp() {
        this.questionDatabase = new QuestionDatabase();
        scanner = new Scanner(System.in);
        configGame = new ConfigGame();
    }

    public void startQuiz() {   //serverSidan???
        configGame.loadSettings();
        String category = getCategory();
        playAllRounds(category);
    }

    private String getCategory() {
        System.out.println("What Category? Music or Sport?");
        return scanner.nextLine();
    }

    public void playAllRounds(String category) {              //serverSidan?
        int roundsPerGame = configGame.getRoundsPerGame();
        for (int round = 1; round <= roundsPerGame; round++) {
            System.out.println("\nRound " + round + " of " + roundsPerGame);
            playRound(category);

        }
    }

    public int playRound(String category) {
        List<Questions> questions = questionDatabase.addQuestionsForCategory(category);
        int questionsToAsk = Math.min(configGame.getQuestionsPerRound(), questions.size());
        for (int q = 0; q < questionsToAsk; q++) {
            askQuestion(questions.get(q), q + 1, questionsToAsk);
        }
        return questionsToAsk;
    }

    public int askQuestion(Questions question, int questionNumber, int totalQuestions) {
        displayQuestion(question, questionNumber, totalQuestions);
        displayAnswerOptions(question.getAnswers());
        return processAnswer(question);
    }

    public void displayQuestion(Questions question, int questionNumber, int totalQuestions) {
        System.out.println("\nQuestion " + questionNumber + " of " + totalQuestions);
        System.out.println(question.getQuestiontext());
    }

    public void displayAnswerOptions(String[] answers) {
        for (int i = 0; i < answers.length; i++) {
            System.out.println((i + 1) + ". " + answers[i]);
        }
    }

    public int processAnswer(Questions question) {
        System.out.println("Enter your answer");
        int answered = Integer.parseInt(scanner.nextLine()) - 1;
        String[] answers = question.getAnswers();

        if (answered == question.getCorrectAnswerIndex()) {
            System.out.println("Correct!");
            return 1;
        } else {
            System.out.println("Wrong! The correct answer was " +
                    answers[question.getCorrectAnswerIndex()]);
            return 0;
        }
    }


    public static void main(String[] args) {
        QuizSetUp quizSetUp = new QuizSetUp();
        quizSetUp.startQuiz();
    }
}