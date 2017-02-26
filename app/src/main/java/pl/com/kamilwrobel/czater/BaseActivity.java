package pl.com.kamilwrobel.czater;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import pl.com.kamilwrobel.czater.dto.ApiKey;

public class BaseActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static final String TAG = BaseActivity.class.getSimpleName();
    public static final String USER_SESSION_SP = "user_session_sp";
    public static final String API_KEY = "token";
    public static final String USER_ID = "usre_id";

    private static Toast toast;
    private boolean allowBrowseWhenLoggedOut = false;
    private SharedPreferences sharedPreferences;

    public void logOut() {
        SharedPreferences.Editor spEditor = getSharedPreferences(USER_SESSION_SP, MODE_PRIVATE).edit();
        spEditor.putString(API_KEY, null);
        spEditor.putString(USER_ID, null);
        spEditor.apply();
    }

    public void logIn(String token, String userId){
        if(token != null && userId != null){
            SharedPreferences.Editor spEditor = getSharedPreferences(USER_SESSION_SP, MODE_PRIVATE).edit();
            spEditor.putString(API_KEY, token);
            spEditor.putString(USER_ID, userId);
            spEditor.apply();
        }
    }

    private String token;
    private String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences(USER_SESSION_SP, MODE_PRIVATE);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        userId = sharedPreferences.getString("user_id", null);
        token = sharedPreferences.getString(API_KEY, null);

        if(token == null){
            logOut();
        }
    }

    protected SharedPreferences getSharedPreferences(){
        return this.sharedPreferences;
    }

    public void allowBrowseWhenLoggedOut() {
        this.allowBrowseWhenLoggedOut = true;
    }

    public void showToast(String message) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_logout:
                logOut();
                break;
            case R.id.main_menu_chat: {
                Intent intent = new Intent(this, ChatActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.main_menu_my_conversations: {
                Intent intent = new Intent(this, ConversationListActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.main_menu_search_people: {
                Intent intent = new Intent(this, UsersListActivity.class);
                startActivity(intent);
            }
            break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        token = sharedPreferences.getString(API_KEY, null);
        userId = sharedPreferences.getString(USER_ID, null);

        if (token == null && !this.allowBrowseWhenLoggedOut) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
