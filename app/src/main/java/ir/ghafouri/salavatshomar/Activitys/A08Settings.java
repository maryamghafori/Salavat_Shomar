package ir.ghafouri.salavatshomar.Activitys;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;

import app.app;
import app.Application;
import ir.ghafouri.salavatshomar.R;

import android.app.AlertDialog;

import app.db;

public class A08Settings extends AppCompatActivity implements View.OnClickListener {

    SwitchCompat SwLango;
    AppCompatCheckBox VIBRATE;
    AppCompatTextView ClearAll;
    static SharedPreferences preferences;
    db DB;
    String lan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a08_settings);
        preferences = Application.getContext().getSharedPreferences(app.TAG, Context.MODE_PRIVATE);
        DB = new db(this, null, null, 1);

        init();
    }

    private void init() {
        SwLango = findViewById(R.id.SwLango);
        VIBRATE = findViewById(R.id.VIBRATE);
        ClearAll = findViewById(R.id.clearAll);

        VIBRATE.setChecked(preferences.getBoolean("VIBRATE", true));

        ClearAll.setOnClickListener(this);
        SwLango.setOnClickListener(this);
        VIBRATE.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SwLango: {
                if (SwLango.isChecked()) {
                    preferences.edit().putBoolean("LANGUAGE_CHECKBOX", true).apply();
                    lan = "fa";
                } else {
                    preferences.edit().putBoolean("LANGUAGE_CHECKBOX", false).apply();
                    lan = "en";
                }
                preferences.edit().putString("LANGUAGE", lan).apply();
                break;
            }
            case R.id.VIBRATE: {
                if (VIBRATE.isChecked()) {
                    preferences.edit().putBoolean("VIBRATE", true).apply();
                    app.VIBR = true;
                } else {
                    preferences.edit().putBoolean("VIBRATE", false).apply();
                    app.VIBR = false;
                }
                break;
            }
            case R.id.clearAll: {
                AlertDialog.Builder alert = new AlertDialog.Builder(A08Settings.this);
                alert.setTitle("Log Out ?");
                alert.setMessage("Do you really want to log Out ?");
                alert.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            preferences.edit().clear().apply();
                            DB.get().execSQL("DELETE FROM " + db.tables.SALAVAT);
                        } catch (Exception e) {
                            app.l(e.toString());
                        }

                        startActivity(new Intent(A08Settings.this, A01_Splash_Screen.class));
                        finish();
                    }
                });
                alert.setPositiveButton("No", null);
                alert.show();
                break;
            }
        }
    }
}
