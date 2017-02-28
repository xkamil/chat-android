package pl.com.kamilwrobel.czater;

import android.app.Activity;
import android.widget.Toast;

public class BaseActivity extends Activity {
    private static Toast toast;


    protected void showMessage(String message) {
        if (toast != null) {
            toast.cancel();
        }

        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
