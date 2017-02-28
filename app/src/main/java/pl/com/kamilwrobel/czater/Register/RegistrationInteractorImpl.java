package pl.com.kamilwrobel.czater.Register;

import android.os.Handler;
import android.text.TextUtils;

//TODO replace with real api
public class RegistrationInteractorImpl implements RegistrationInteractor {

    @Override
    public void validateRegistration(final String login, final String password, String repeatPassword, final RegistrationInteractorListener listener) {
        if (password != repeatPassword) {
            listener.onError(ERROR_PASSWORDS_NOT_MATCH);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(TextUtils.isEmpty(login) || login.length() < 3){
                        listener.onError(ERROR_LOGIN_TOO_SHORT);
                        return;
                    }

                    if(TextUtils.isEmpty(password) || password.length() < 3){
                        listener.onError(ERROR_PASSWORD_TOO_SHORT);
                        return;
                    }

                    if(login.equals("kamil")){
                        listener.onError(ERROR_LOGIN_ALREADY_TAKEN);
                    }
                }
            }, 2000);
        }
    }
}
