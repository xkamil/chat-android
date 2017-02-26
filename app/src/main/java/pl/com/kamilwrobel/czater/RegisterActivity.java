package pl.com.kamilwrobel.czater;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.kamilwrobel.czater.Api.ApiClient;
import pl.com.kamilwrobel.czater.Api.ResponseHandler;
import pl.com.kamilwrobel.czater.dto.ApiKey;
import pl.com.kamilwrobel.czater.dto.User;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_CONFLICT;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.activity_register_et_login)
    EditText etLogin;
    @BindView(R.id.activity_register_et_password)
    EditText etPassword;
    @BindView(R.id.activity_register_et_repeat_password)
    EditText etRepeatPassword;
    @BindView(R.id.activity_register_btn_register)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allowBrowseWhenLoggedOut();
        ButterKnife.bind(this);

        setContentView(R.layout.activity_register);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @OnClick(R.id.activity_register_btn_register)
    public void onRegisterButtonClicked(View view) {

        User user = new User();
        user.setLogin(this.etLogin.getText().toString());
        user.setPassword(this.etPassword.getText().toString());
        user.setRepeatedPassword(this.etRepeatPassword.getText().toString());

        for (Integer validationError : user.validate()) {
            switch (validationError) {
                case User.LOGIN_TOO_SHORT:
                    showToast("Login za krótki. Min 5 znaków");
                    break;
                case User.PASSWORD_TOO_SHORT:
                    showToast("Hasło za krótkie. Min 5 znaków");
                    break;
                case User.PASSWORDS_NOT_MATCH:
                    showToast("Hasłą nie są takie same");
                    break;
            }
        }

        if (user.validate().size() > 0) {
            return;
        }

        ApiClient.getInstance().register(user.getLogin(), user.getPassword(), new ResponseHandler<ApiKey>() {
            @Override
            public void beforeRequest() {
                super.beforeRequest();
                disableButtons();
            }

            @Override
            public void handleResponse(ApiKey apikey) {
                showToast("Pomyślnie zarejestrowano użytkownika. ");
                openLoginActivity();
            }

            @Override
            public void handleError(int error) {
                if (error == HTTP_CONFLICT) {
                    etLogin.setBackgroundResource(R.color.colorError);
                    showToast("Użytkownik o podanym loginie już istnieje");
                } else if (error == HTTP_BAD_REQUEST) {
                    showToast("Podane dane do rejestracji są nieprawidłowe");
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

    public void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void openConversationListActivity() {
        Intent intent = new Intent(this, ConversationListActivity.class);
        startActivity(intent);
    }

    public void enableButtons() {
        this.btnRegister.setEnabled(true);
    }

    public void disableButtons() {
        this.btnRegister.setEnabled(false);
    }
}
