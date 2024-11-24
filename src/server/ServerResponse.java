package server;
import QuizGame.eCategoryType;

import java.io.Serializable;
import java.util.*;

public class ServerResponse implements Serializable {

    ResponseType type;
    boolean myTurn;
    List<eCategoryType> categories;

    public ServerResponse(ResponseType type){
        this.type = type;
    }

    public ServerResponse(ResponseType type, boolean myTurn, List<eCategoryType> categories){ /// Extended constructor
        this.type = type;
        this.myTurn = myTurn;
        this.categories = categories;
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


}
