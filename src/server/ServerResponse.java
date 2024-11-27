package server;
import QuizGame.Questions;
import QuizGame.eCategoryType;

import java.io.Serializable;
import java.util.*;

public class ServerResponse implements Serializable {

    ResponseType type;
    boolean myTurn;
    List<eCategoryType> categories;
    List<Questions> questions;
    int myScore;
    int opponentsScore;
    String opponentsName;

    public ServerResponse(ResponseType type){
        this.type = type;
    }

    public ServerResponse(ResponseType type, boolean myTurn, List<eCategoryType> categories){ /// Extended constructor
        this.type = type;
        this.myTurn = myTurn;
        this.categories = categories;
    }

    public ServerResponse(ResponseType type, int myScore, int opponentsScore, String opponentsName){
        this.type = type;
        this.myScore = myScore;
        this.opponentsScore = opponentsScore;
        this.opponentsName = opponentsName;
    }

    public ServerResponse(ResponseType type, List<Questions> questions){
        this.type = type;
        this.questions = questions;
    }

    public ResponseType getResponseType(){
        return type;
    }

    public boolean isMyTurn(){
        return myTurn;
    }

    public List<eCategoryType> getCategories(){
        return categories;
    }

    public List<Questions> getQuestions(){return questions;}

    public String getOpponentsName(){
        return opponentsName;
    }

    public int getMyScore() {
        return myScore;
    }

    public int getOpponentsScore() {
        return opponentsScore;
    }
}