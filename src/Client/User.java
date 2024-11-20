package Client;

public class User {
    private String username;
    private String status;

    public User(String username, String status) {
        this.username = username;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public String getStatus() {
        return status;
    }

}
