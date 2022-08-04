package model;

public class User {

    private int Id;

    private String username;

    private String password;

    private String email;

    private int hasEditPermission;

    public User() {

    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int isHasEditPermission() {
        return hasEditPermission;
    }

    public void setHasEditPermission(int hasEditPermission) {
        this.hasEditPermission = hasEditPermission;
    }

    @Override
    public String toString() {
        return username;
    }
}
