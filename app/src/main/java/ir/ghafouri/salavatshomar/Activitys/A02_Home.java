package ir.ghafouri.salavatshomar.Activitys;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.yalantis.guillotine.animation.GuillotineAnimation;

import app.app;
import app.db;
import ir.ghafouri.salavatshomar.R;

public class A02_Home extends AppCompatActivity implements View.OnClickListener {

    private static final long RIPPLE_DURATION = 250;

    Toolbar toolbar;
    FrameLayout root;
    ImageView contentHamburger;
    AppCompatTextView tv1;

    View guillotineMenu;
    LinearLayout Salavat, amalHafte, RekatShomar, TasbihLi, settings_group, profile_group;
    boolean backPressed = false;
    Typeface SegoeBoldFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a02_home);

        SegoeBoldFont = Typeface.createFromAsset(getAssets(), "fonts/segoe_bold.ttf");
        SQLiteDatabase notes_db = openOrCreateDatabase(app.DB_NAME, MODE_PRIVATE, null);

        notes_db.execSQL("CREATE TABLE IF NOT EXISTS '" + db.tables.SALAVAT + "'" +
                "('" + db.SALAVAT.ID + "' INTEGER PRIMARY KEY AUTOINCREMENT," +
                " '" + db.SALAVAT.NUMBER + "' VARCHAR(50) ," +
                " '" + db.SALAVAT.DATE + "' VARCHAR(30) )");

        guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
        init();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
        }

        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();
    }

    private void init() {
        Salavat = findViewById(R.id.Salavat);
        amalHafte = findViewById(R.id.amalHafte);
        tv1 = findViewById(R.id.tv1);
        toolbar = findViewById(R.id.toolbar);
        root = findViewById(R.id.root);
        RekatShomar = findViewById(R.id.RekatShomar);
        TasbihLi = findViewById(R.id.TasbihLi);
        contentHamburger = findViewById(R.id.content_hamburger);
        root.addView(guillotineMenu);
        settings_group = findViewById(R.id.settings_group);
        profile_group = findViewById(R.id.profile_group);

        //getData();
        Salavat.setOnClickListener(this);
        amalHafte.setOnClickListener(this);
        RekatShomar.setOnClickListener(this);
        TasbihLi.setOnClickListener(this);
        settings_group.setOnClickListener(this);
        profile_group.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Salavat: {
                startActivity(new Intent(A02_Home.this, A05Salavat.class));
                break;
            }
            case R.id.TasbihLi: {
                startActivity(new Intent(A02_Home.this, A06Tasbihat.class));
                break;
            }
            case R.id.amalHafte: {
                startActivity(new Intent(A02_Home.this, A04ZekreHafte.class));
                break;
            }
            case R.id.RekatShomar: {
                startActivity(new Intent(A02_Home.this, A07RekatShomar.class));
                break;
            }
            case R.id.settings_group: {
                startActivity(new Intent(A02_Home.this, A08Settings.class));
                break;
            }
            case R.id.profile_group: {
                startActivity(new Intent(A02_Home.this, A09AppInfo.class));
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (guillotineMenu.getVisibility() == View.VISIBLE)
            guillotineMenu.setVisibility(View.GONE);
        else if (!backPressed) {
            backPressed = true;
            app.toastINFO("برای خروج دو بار دکمه بازگشت رابزنید");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    backPressed = false;
                }
            }, 1500);
        } else {
            super.onBackPressed();
        }
    }

}