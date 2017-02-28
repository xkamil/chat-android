package pl.com.kamilwrobel.czater.Api.dto;

import java.util.Date;

public class User {
    private String _id;
    private String login;
    private Date created;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String get_id() {
        return _id;
    }
}
