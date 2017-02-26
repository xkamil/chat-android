package pl.com.kamilwrobel.czater.dto;


import java.util.ArrayList;
import java.util.List;

public class User {
    public static final int LOGIN_TOO_SHORT = 1;
    public static final int PASSWORD_TOO_SHORT = 2;
    public static final int PASSWORDS_NOT_MATCH = 3;

    private String _id;
    private String login;
    private String password;
    private String repeatedPassword;

    public User(String _id, String login) {
        this._id = _id;
        this.login = login;
    }

    public User() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatPassword) {
        this.repeatedPassword = repeatPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Integer> validate(){
        List<Integer> validationErrors = new ArrayList<>();

        if(this.login == null || this.login.length() < 5){
            validationErrors.add(LOGIN_TOO_SHORT);
        }

        if(this.password == null || this.password.length() < 5){
            validationErrors.add(PASSWORD_TOO_SHORT);
        }

        if(!this.password.equals(this.repeatedPassword)){
            validationErrors.add(PASSWORDS_NOT_MATCH);
        }

        return validationErrors;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
