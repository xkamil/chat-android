package pl.com.kamilwrobel.czater.network;


import android.os.AsyncTask;

import java.net.HttpURLConnection;

import pl.com.kamilwrobel.czater.dto.ApiKey;

public class Api {
    private static Api instance;

    public static Api getInstance() {
        if (instance == null) {
            synchronized (Api.class) {
                if (instance == null) {
                    instance = new Api();
                }
            }
        }
        return instance;
    }

    public void login(final String username, final String password, final ApiResultCallback<ApiKey> callback) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                callback.beforeRequest();
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException ignore) {
                }
                ApiKey apiKey = new ApiKey("asdfasdfasfds", "234");

                if (username.equals("kamil") && password.equals("limak")) {
                    callback.onSuccess(HttpURLConnection.HTTP_OK, apiKey);
                } else {
                    callback.onFailure(HttpURLConnection.HTTP_NOT_FOUND, null);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                callback.afterRequest();
            }
        }.execute();
    }
}
