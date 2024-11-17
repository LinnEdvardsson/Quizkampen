package server;

public class Protocol {

    private static final int WAITING = 0;
    private static final int CHOOSE_CATEGORY = 1;
    private static final int SHOW_QUESTION = 2;
    private static final int SHOW_ANSWER = 3;

    private static final int NUMBER_OF_QUESTIONS = 2;

    private int state = WAITING;
    private int currentQuestion = 0;


}
