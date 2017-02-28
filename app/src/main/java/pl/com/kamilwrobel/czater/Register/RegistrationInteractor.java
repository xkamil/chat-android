package pl.com.kamilwrobel.czater.Register;


public interface RegistrationInteractor {
    int ERROR_LOGIN_ALREADY_TAKEN = 0;
    int ERROR_LOGIN_TOO_SHORT = 1;
    int ERROR_PASSWORD_TOO_SHORT = 2;
    int ERROR_PASSWORDS_NOT_MATCH = 3;

    interface RegistrationInteractorListener {
        void onSuccess();

        void onError(int errorCode);
    }

    void validateRegistration(String login,
                              String password,
                              String repeatPassword,
                              RegistrationInteractorListener listener);
}
