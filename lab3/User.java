package lab3;

public class User {
    protected String userName;
    protected String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public boolean Login(String userName, String password) {
        return this.userName.equals(userName) && this.password.equals(password);
    }
}
