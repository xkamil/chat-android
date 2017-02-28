package pl.com.kamilwrobel.czater.Login;


public interface LoginContract {

    interface LoginView {

        void showLoginTooShortError();

        void showPasswordTooShortError();

        void showProgressBar();

        void hideProgressBar();

    }


    interface LoginPresenter {

        void validateCredentials(String username, String password);

        void onDestroy();
    }
}
