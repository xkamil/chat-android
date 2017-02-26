package pl.com.kamilwrobel.czater.Api;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.IOException;
import java.util.Map;

import cz.msebera.android.httpclient.*;
import cz.msebera.android.httpclient.HttpResponse;
import pl.com.kamilwrobel.czater.dto.ApiKey;

public abstract class AbstractApiClient {
    public static final String TAG = AbstractApiClient.class.getSimpleName();

    private AsyncHttpClient client = new AsyncHttpClient();
    private ObjectMapper mapper;



    protected AbstractApiClient() {
        this.mapper = new ObjectMapper();
    }

    protected void sendGet(String url, RequestParams params, Map<String, String> headers, final TypeReference type, final ResponseHandler responseHandler) {
        client.removeAllHeaders();

        if(headers != null){
            for (String key : headers.keySet()) {
                Log.i(TAG, "Adding header: " + key + " : " + headers.get(key));
                client.addHeader(key, headers.get(key));
            }
        }

        client.get(url, params, new TextHttpResponseHandler() {
            @Override
            public void onPostProcessResponse(ResponseHandlerInterface instance, HttpResponse response) {
                super.onPostProcessResponse(instance, response);
                responseHandler.beforeRequest();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                responseHandler.handleError(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    Object posts = mapper.readValue(responseString, type);
                    responseHandler.handleResponse(posts);
                } catch (IOException e) {
                    onFailure(-1, null, "", e);
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                responseHandler.afterRequest();
            }
        });
    }

    protected void sendPost(String url,
                            RequestParams params,
                            Map<String, String> headers,
                            final TypeReference type,
                            final ResponseHandler responseHandler) {
        client.removeAllHeaders();

        if(headers != null){
            for (String key : headers.keySet()) {
                client.addHeader(key, headers.get(key));
            }
        }

        client.post(url, params, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode,
                                  Header[] headers,
                                  String responseString,
                                  Throwable throwable) {
                responseHandler.handleError(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    Object posts = mapper.readValue(responseString, new TypeReference<ApiKey>() {
                    });
                    responseHandler.handleResponse(posts);
                } catch (Exception e) {
                    Log.e(TAG, "Error when parsing response. " + e.getMessage());
                    responseHandler.handleError(-1);
                }
            }

            @Override
            public void onStart() {
                responseHandler.beforeRequest();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                responseHandler.afterRequest();
            }
        });
    }
}
