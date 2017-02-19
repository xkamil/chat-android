package pl.com.kamilwrobel.czater;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.kamilwrobel.czater.Api.ApiClient;
import pl.com.kamilwrobel.czater.Api.ResponseHandler;
import pl.com.kamilwrobel.czater.dto.ApiKey;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_CONFLICT;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

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
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        allowBrowseWhenLoggedOut();
    }

    @OnClick(R.id.activity_register_btn_register)
    public void onRegisterButtonClicked(View view){
        Log.i(TAG, "RERFDSFDSFDSFDSFDSFDSFDSFSDF");
        String login = this.etLogin.getText().toString();
        String password = this.etPassword.getText().toString();
        String repeatPassword = this.etRepeatPassword.getText().toString();

        if(login.trim().length() < 3){
            etLogin.setBackgroundResource(R.color.colorError);
            showToast("Login musi składać się z przynajmniej 3 znaków");
            return;
        }

        if(password.trim().length() < 3){
            etPassword.setBackgroundResource(R.color.colorError);
            showToast("Hasło musi składać się z przynajmniej 3 znaków");
            return;
        }

        if(!password.equals(repeatPassword)){
            etPassword.setBackgroundResource(R.color.colorError);
            etRepeatPassword.setBackgroundResource(R.color.colorError);
            showToast("Podane hasła nie są różne");
            return;
        }

        ApiClient.getInstance().register(login, password, new ResponseHandler<ApiKey>() {
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
                }else if(error == HTTP_BAD_REQUEST){
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

    public void openLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void openConversationListActivity(){
        Intent intent = new Intent(this, ConversationListActivity.class);
        startActivity(intent);
    }

    public void enableButtons(){
        this.btnRegister.setEnabled(true);
    }

    public void disableButtons(){
        this.btnRegister.setEnabled(false);
    }
}
