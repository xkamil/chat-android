package pl.com.kamilwrobel.czater.Api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.com.kamilwrobel.czater.dto.ApiKey;
import pl.com.kamilwrobel.czater.dto.User;

public class ApiClient extends AbstractApiClient {
    private static ApiClient instance;

    private AsyncHttpClient client = new AsyncHttpClient();
    private static final String HOST = "http://chat-api2.herokuapp.com";


    public static ApiClient getInstance() {
        if (instance == null) {
            synchronized (ApiClient.class) {
                if (instance == null) {
                    instance = new ApiClient();
                }
            }
        }
        return instance;
    }

    public void login(String login, String password, final ResponseHandler<ApiKey> responseHandler) {
        String path = "/users/login";

        RequestParams requestParams = new RequestParams();
        requestParams.put("login", login);
        requestParams.put("password", password);

        this.sendPost(HOST + path, requestParams, null, new TypeReference<ApiKey>() {
        }, responseHandler);
    }

    public void register(String login, String password, final ResponseHandler<ApiKey> responseHandler) {
        String path = "/users/register";

        RequestParams requestParams = new RequestParams();
        requestParams.put("login", login);
        requestParams.put("password", password);

        sendPost(HOST + path, requestParams, null, new TypeReference<ApiKey>() {
        }, responseHandler);
    }

    public void getUsers(String token, final ResponseHandler<List<User>> responseHandler) {
        String path = "/users";

        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);

        sendGet(HOST + path, null, headers, new TypeReference<List<User>>() {
        }, responseHandler);
    }

    private ApiClient() {
        super();
    }

}
