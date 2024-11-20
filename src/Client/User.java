package Client;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String status;
    List<User> users;

    public User(String username, String status) {
        this.username = username;
        this.status = status;
        users = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getStatus() {
        return status;
    }

    public List<User> addUser(String username, String status) {

    }

}
