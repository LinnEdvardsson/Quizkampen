package Client;

import QuizGame.Questions;
import QuizGame.eCategoryType;
import server.ClientConnection;

import java.io.Serializable;
import java.util.List;

public class ClientRequest implements Serializable {

    RequestType type;
    String username;
    eCategoryType category;
    List<Questions> answeredQuestions;
    int score;

    public ClientRequest(RequestType type, String username) {
        this.type = type;
        this.username = username;
    }

    public ClientRequest(RequestType type, eCategoryType category){
        this.type = type;
        this.category = category;
    }

    public ClientRequest(RequestType type, List<Questions> questions){
        this.type = type;
        this.answeredQuestions = questions;
    }

    public ClientRequest(RequestType type, int score){
        this.type = type;
        this.score = score;
    }

    public RequestType getRequestType(){
        return type;
    }

    public String getUsername() {
        return username;
    }

    public List<Questions> getQuestions(){
        return answeredQuestions;
    }

    public int getScore(){
        return score;
    }
}

