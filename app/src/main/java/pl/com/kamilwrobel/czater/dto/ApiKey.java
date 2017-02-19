package pl.com.kamilwrobel.czater.dto;


public class ApiKey {
    private String key;
    private String id;

    public ApiKey() {
    }

    public ApiKey(String key, String user_id) {
        this.key = key;
        this.id = user_id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ApiKey{" +
                "key='" + key + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
