package pl.com.kamilwrobel.czater;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import pl.com.kamilwrobel.czater.dto.ApiKey;

public class BaseActivity extends AppCompatActivity {
    public static final String TAG = BaseActivity.class.getSimpleName();
    private static Toast toast;
    public static ApiKey apikey;
    protected boolean allowBrowseWhenLoggedOut = false;

    public void logOut() {
        apikey = null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(apikey == null && !allowBrowseWhenLoggedOut){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            showToast("Wylogowano");
        }
    }

    public void allowBrowseWhenLoggedOut(){
        this.allowBrowseWhenLoggedOut = true;
    }

    public void showToast(String message) {
        if(toast != null){
            toast.cancel();
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
