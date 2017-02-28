package pl.com.kamilwrobel.czater.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.kamilwrobel.czater.BaseActivity;
import pl.com.kamilwrobel.czater.R;
import pl.com.kamilwrobel.czater.Register.RegistrationActivity;

public class LoginActivity extends BaseActivity implements LoginContract.LoginView {

    @BindView(R.id.activity_login_et_login)
    EditText etLogin;

    @BindView(R.id.activity_login_et_password)
    EditText etPassword;

    @BindView(R.id.activity_login_progress_bar)
    ProgressBar pbLogin;

    private LoginPresenterImpl loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        loginPresenter = new LoginPresenterImpl(this);
    }

    @Override
    public void showLoginTooShortError() {
        showMessage("Login too short");
    }

    @Override
    public void showPasswordTooShortError() {
        showMessage("Password too short");
    }

    @Override
    public void showProgressBar() {
        pbLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        pbLogin.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.activity_login_btn_login)
    public void onLoginButtonClick(View view) {
        String login = etLogin.getText().toString();
        String password = etPassword.getText().toString();

        loginPresenter.validateCredentials(login, password);
    }

    @OnClick(R.id.activity_login_tv_register)
    public void navigateToRegistration(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

}
