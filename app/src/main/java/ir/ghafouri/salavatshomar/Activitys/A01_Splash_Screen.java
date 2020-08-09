package ir.ghafouri.salavatshomar.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import app.app;
import ir.ghafouri.salavatshomar.R;

public class A01_Splash_Screen extends AppCompatActivity {

    String lan;
    boolean FL;
    static SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a01_splash_screen);

        preferences = PreferenceManager.getDefaultSharedPreferences(A01_Splash_Screen.this);
        preferences = getSharedPreferences(app.TAG, Context.MODE_PRIVATE);

        FL = preferences.getBoolean("FIRST_LOGIN", false);
        app.VIBR = preferences.getBoolean("VIBRATE", false);
        getAppLanguage();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!FL) {
                    attemptNewLogin();
                }
                startActivity(new Intent(A01_Splash_Screen.this, A02_Home.class));
                finish();
            }
        }, 1500);
    }

    private void attemptNewLogin() {

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("LANGUAGE", lan);
        editor.putString("FONT", "segoe_ui");
        editor.putBoolean("LANGUAGE_CHECKBOX", false);
        editor.putBoolean("DarkMode", false);
        editor.putBoolean("UPDATE", false);
        editor.putBoolean("COMEBACK", false);
        editor.putBoolean("VIBRATE", true);
        editor.putBoolean("FIRST_LOGIN", true);
        editor.apply();
    }

    public void getAppLanguage() {
       // lan = Locale.getDefault().getLanguage();
        lan = "fa";
    }

}
