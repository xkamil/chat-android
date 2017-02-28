package pl.com.kamilwrobel.czater.Login;


import pl.com.kamilwrobel.czater.Api.ApiService;
import pl.com.kamilwrobel.czater.Api.dto.Token;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenterImpl implements LoginContract.LoginPresenter {

    private LoginContract.LoginView loginView;


    public LoginPresenterImpl(LoginContract.LoginView loginView) {

        this.loginView = loginView;
    }

    @Override
    public void validateCredentials(String username, String password) {

        if (loginView != null) {
            loginView.showProgressBar();

            ApiService.getChaterApi().login(username, password).enqueue(new Callback<Token>() {
                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {
                    if(response.isSuccessful()){
                        loginView.showPasswordTooShortError();
                    }else{
                        loginView.showProgressBar();
                    }
                }

                @Override
                public void onFailure(Call<Token> call, Throwable t) {
                    loginView.showLoginTooShortError();
                }
            });
        }
    }

    @Override
    public void onDestroy() {

        loginView = null;
    }

}
