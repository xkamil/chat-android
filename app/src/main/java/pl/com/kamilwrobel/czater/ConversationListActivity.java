package pl.com.kamilwrobel.czater;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import pl.com.kamilwrobel.czater.Api.ApiClient;
import pl.com.kamilwrobel.czater.Api.ResponseHandler;
import pl.com.kamilwrobel.czater.dto.ApiKey;
import pl.com.kamilwrobel.czater.dto.User;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

public class ConversationListActivity extends BaseActivity {
    private TextView tvUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_list);

        this.tvUserInfo = (TextView) findViewById(R.id.activity_conversation_list_tv_user_info);

        tvUserInfo.setText("User id: " + getToken() + "\nApiKey: " + getToken());
    }

    @Override
    protected void onResume() {
        super.onResume();

        ApiClient.getInstance().getUsers(getToken(), new ResponseHandler<List<User>>() {
            @Override
            public void beforeRequest() {
                super.beforeRequest();
            }

            @Override
            public void handleResponse(List<User> users) {
                for (User user : users) {
                    Log.i(TAG, user.toString());
                }
            }

            @Override
            public void handleError(int error) {
                if (error == HTTP_NOT_FOUND) {
                    showToast("Błędne hasło lub login");
                } else {
                    showToast("Wystąpił nieznany błąd: " + error);
                }
            }

            @Override
            public void afterRequest() {
                super.afterRequest();
            }
        });
    }

    public void logOut(View view) {
        logOut();
        onResume();
    }
}
