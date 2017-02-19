package pl.com.kamilwrobel.czater.Api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import pl.com.kamilwrobel.czater.dto.ApiKey;

public class ApiClient extends AbstractApiClient {
    private static ApiClient instance;

    private AsyncHttpClient client = new AsyncHttpClient();
    private static final String HOST = "http://januszeapi.herokuapp.com/api";


    public static ApiClient getInstance() {
        if(instance == null){
            synchronized (ApiClient.class){
                if(instance == null){
                    instance = new ApiClient();
                }
            }
        }
        return instance;
    }

    public void login(String login, String password, final ResponseHandler<ApiKey> responseHandler) {
        String path = "/login";

        RequestParams requestParams = new RequestParams();
        requestParams.put("login", login);
        requestParams.put("password", password);

        this.sendPost(HOST + path, requestParams, new TypeReference<ApiKey>(){} ,responseHandler );
    }

    public void register(String login, String password, final ResponseHandler<ApiKey> responseHandler) {
        String path = "/register";

        RequestParams requestParams = new RequestParams();
        requestParams.put("login", login);
        requestParams.put("password", password);

        sendPost(HOST + path, requestParams, new TypeReference<ApiKey>(){} ,responseHandler );
    }

    private ApiClient() {
        super();
    }

}
