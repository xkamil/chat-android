package pl.com.kamilwrobel.czater.Api;

public abstract class ResponseHandler<T> {
        public abstract void handleResponse(T response);
        public void handleError(int error){};
        public void beforeRequest(){}
        public void afterRequest(){}
}
