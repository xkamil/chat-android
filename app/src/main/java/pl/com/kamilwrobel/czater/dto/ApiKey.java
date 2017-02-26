package pl.com.kamilwrobel.czater.dto;


public class ApiKey {
    private String token;
    private String user_id;

    public ApiKey() {
    }

    public ApiKey(String token, String user_id) {
        this.token = token;
        this.user_id = user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "ApiKey{" +
                "token='" + token + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
