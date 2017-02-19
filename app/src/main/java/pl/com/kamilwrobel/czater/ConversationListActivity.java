package pl.com.kamilwrobel.czater;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ConversationListActivity extends BaseActivity {
    private TextView tvUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_list);

        this.tvUserInfo = (TextView) findViewById(R.id.activity_conversation_list_tv_user_info);

        tvUserInfo.setText("User id: " + apikey.getId() + "\nApiKey: " + apikey.getKey());
    }

    public void logOut(View view) {
        apikey = null;
        onResume();
    }
}
