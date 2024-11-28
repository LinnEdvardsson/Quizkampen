package Client;

import QuizGame.Questions;
import java.io.Serializable;
import java.util.List;

public class ClientRequest implements Serializable {

    RequestType type;
    String username;
    List<Questions> answeredQuestions;
    int score;

    public ClientRequest(RequestType type, String username) {
        this.type = type;
        this.username = username;
    }

    /// Klient so valt kategori skickar svarade frågor till den andra spelaren så denne svarar på samma.
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