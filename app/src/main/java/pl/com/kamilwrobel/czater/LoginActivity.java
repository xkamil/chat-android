package pl.com.kamilwrobel.czater;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.kamilwrobel.czater.Api.ApiClient;
import pl.com.kamilwrobel.czater.Api.ResponseHandler;
import pl.com.kamilwrobel.czater.dto.ApiKey;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

public class LoginActivity extends BaseActivity {
    public static final String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.activity_login_et_login)
    EditText etLogin;
    @BindView(R.id.activity_login_et_password)
    EditText etPassword;
    @BindView(R.id.activity_login_btn_login)
    Button btnLogin;
    @BindView(R.id.activity_login_tv_register)
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        allowBrowseWhenLoggedOut();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.activity_login_btn_login)
    public void onLoginButtonClicked(View view) {
        String login = this.etLogin.getText().toString();
        String password = this.etPassword.getText().toString();

        ApiClient.getInstance().login(login, password, new ResponseHandler<ApiKey>() {
            @Override
            public void beforeRequest() {
                super.beforeRequest();
                disableButtons();
            }

            @Override
            public void handleResponse(ApiKey apikey) {
                LoginActivity.apikey = apikey;
                Intent intent = new Intent(LoginActivity.this, ConversationListActivity.class);
                startActivity(intent);
            }

            @Override
            public void handleError(int error) {
                if (error == HTTP_NOT_FOUND) {
                    etLogin.setBackgroundResource(R.color.colorError);
                    etPassword.setBackgroundResource(R.color.colorError);
                    showToast("Błędne hasło lub login");
                } else {
                    showToast("Wystąpił nieznany błąd");
                }
            }

            @Override
            public void afterRequest() {
                super.afterRequest();
                enableButtons();
            }
        });
    }

    @OnClick(R.id.activity_login_tv_register)
    public void onRegisterButtonClicked(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        Log.i(TAG, "register button clicked");
    }

    public void disableButtons() {
        this.tvRegister.setClickable(false);
        this.btnLogin.setClickable(false);
    }

    public void enableButtons() {
        this.tvRegister.setClickable(true);
        this.btnLogin.setClickable(true);
    }
}
