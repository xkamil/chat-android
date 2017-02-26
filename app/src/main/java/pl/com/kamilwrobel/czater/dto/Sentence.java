package pl.com.kamilwrobel.czater.dto;


import java.util.Date;

public class Sentence {
    private String conversation_id;
    private String user_id;
    private String content;
    private Date created;

    public Sentence(String conversation_id, String user_id, String content, Date created) {
        this.conversation_id = conversation_id;
        this.user_id = user_id;
        this.content = content;
        this.created = created;
    }

    public Sentence() {
    }

    public String getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(String conversation_id) {
        this.conversation_id = conversation_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
