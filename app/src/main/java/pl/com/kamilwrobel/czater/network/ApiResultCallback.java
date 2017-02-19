package pl.com.kamilwrobel.czater.network;

public interface ApiResultCallback<T> {
    void beforeRequest();
    void onSuccess(int status, T response);
    void onFailure(int status, T response);
    void afterRequest();
}
