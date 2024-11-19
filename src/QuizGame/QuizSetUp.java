package QuizGame;
import java.util.List;
import java.util.Scanner;

public class QuizSetUp {
    private final QuestionDatabase questionDatabase;
    private List<Questions> questions;
    private final ConfigGame configGame;
    private final Scanner scanner;

    public QuizSetUp() {
        this.questionDatabase = new QuestionDatabase();
        this.scanner = new Scanner(System.in);
        this.configGame = new ConfigGame();
    }

    public void startQuiz() {
        configGame.loadSettings();
        eCategoryType category = getCategory();
        playAllRounds(category);
        scanner.close();
    }

    private eCategoryType getCategory() {
        System.out.println("What Category? Music or Sport?");
        while (true) {
            try {
                String input = scanner.nextLine().toUpperCase();
                return eCategoryType.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid category. Please choose again: Music or Sport? ");

            }
        }
    }

        private void playAllRounds (eCategoryType category){
            int roundsPerGame = configGame.getRoundsPerGame();
            int totalScore = 0;

            for (int round = 1; round <= roundsPerGame; round++) {
                System.out.println("\nRound " + round + " of " + roundsPerGame);
                totalScore += playRound(category);

            }
            System.out.println("Total Score: " + totalScore + "out of " + (roundsPerGame *
                    configGame.getQuestionsPerRound()));
        }

        private int playRound (eCategoryType category){
            List<Questions> questions = questionDatabase.getQuestionsByCategory(category);
            int questionsToAsk = Math.min(configGame.getQuestionsPerRound(), questions.size());
            int roundScore = 0;
            for (int q = 0; q < questionsToAsk; q++) {
                roundScore += askQuestion(questions.get(q), q + 1, questionsToAsk);
            }
            return roundScore;
        }

        private int askQuestion (Questions question,int questionNumber, int totalQuestions){
            displayQuestion(question, questionNumber, totalQuestions);
            displayAnswerOptions(question.getAnswers());
            return processAnswer(question);
        }

        private void displayQuestion (Questions question,int questionNumber, int totalQuestions){
            System.out.println("\nQuestion " + questionNumber + " of " + totalQuestions);
            System.out.println(question.getQuestiontext());
        }

        private void displayAnswerOptions (String[]answers){
            for (int i = 0; i < answers.length; i++) {
                System.out.println((i + 1) + ". " + answers[i]);
            }
        }

        private int processAnswer (Questions question) {
            System.out.println("Enter your answer");
            while (true) {
                try {
                    int answered = Integer.parseInt(scanner.nextLine()) - 1;
                    if (answered >= 0 && answered < question.getAnswers().length) {
                        if (answered == question.getCorrectAnswerIndex()) {
                            System.out.println("Correct!");
                            return 1;
                        } else {
                            System.out.println("Wrong! The correct answer was " +
                                    question.getAnswers()[question.getCorrectAnswerIndex()]);
                            return 0;
                        }
                    } else {
                        System.out.println("Invalid choice. Try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number");
                }
            }
        }




        public static void main (String[]args){
            QuizSetUp quizSetUp = new QuizSetUp();
            quizSetUp.startQuiz();
        }
    }

