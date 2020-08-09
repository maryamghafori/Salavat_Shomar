package ir.ghafouri.salavatshomar.Activitys;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import app.DateConverter;
import app.app;
import app.db;
import ir.ghafouri.salavatshomar.Adapters.DrawerAdapter;
import ir.ghafouri.salavatshomar.Interfaces.DialogListener;
import ir.ghafouri.salavatshomar.Objects.AlertDialogObject;
import ir.ghafouri.salavatshomar.Objects.DrawerObject;
import ir.ghafouri.salavatshomar.R;
import ir.ghafouri.salavatshomar.Views.DialogView;

public class A05Salavat extends AppCompatActivity implements View.OnClickListener {

    AppCompatButton btnSal;
    AppCompatTextView reset, numSal, NumberLy, DateLy;
    DateConverter Converter;
    FlowingDrawer mDrawer;
    ListView listView;
    LottieAnimationView lottieArrow;

    DialogView dialog;
    SQLiteDatabase salavt;
    List<DrawerObject> list;
    DrawerAdapter adapter;

    SharedPreferences share;
    Cursor cursor;
    String Date, name1;
    int counter = 0, id = -1;
    boolean comeback = false, Save = false;
    NumberFormat formatter = new DecimalFormat("#,###,###,###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a05_salavat);

        salavt = openOrCreateDatabase(app.DB_NAME, MODE_PRIVATE, null);
        share = getSharedPreferences(app.TAG, MODE_PRIVATE);
        comeback = (share.getBoolean("COMEBACK", false));

        init();

    }

    private void init() {

        numSal = findViewById(R.id.numSal);
        btnSal = findViewById(R.id.btnSal);
        DateLy = findViewById(R.id.DateLy);
        NumberLy = findViewById(R.id.NumberLy);
        lottieArrow = findViewById(R.id.lottieArrow);
        reset = findViewById(R.id.reset);
        mDrawer = findViewById(R.id.drawerlayout);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        listView = findViewById(R.id.id_container_menu);

        numSal.setText(String.valueOf(counter));

        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == ElasticDrawer.STATE_CLOSED) {
                    lottieArrow.playAnimation();
                }
            }

            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {
                lottieArrow.pauseAnimation();
                listView.setAdapter(adapter);
                list = prepareData();
                adapter = new DrawerAdapter(A05Salavat.this, 0, list);
            }
        });

        cursor = salavt.rawQuery("SELECT * FROM '" + db.tables.SALAVAT + "' ", null);
        while (cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex(db.SALAVAT.ID));
            name1 = cursor.getString(cursor.getColumnIndex(db.SALAVAT.NUMBER));

            counter = Integer.parseInt(name1);
            numSal.setText("" + formatter.format(counter));
        }

        if (counter != 0) {
            if (comeback) {
                recoverySalavt();
            }
        }

        btnSal.setOnClickListener(this);
        reset.setOnClickListener(this);
    }

    private List<DrawerObject> prepareData() {
        List<DrawerObject> list = new ArrayList<>();
        DrawerObject objects = new DrawerObject();

        cursor = salavt.rawQuery("SELECT * FROM '" + db.tables.SALAVAT + "' ", null);
        while (cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex(db.SALAVAT.ID));
            name1 = cursor.getString(cursor.getColumnIndex(db.SALAVAT.NUMBER));
            Date = cursor.getString(cursor.getColumnIndex(db.SALAVAT.DATE));

            objects = new DrawerObject();
            objects.setName(name1);
            objects.setDescription(Date.replace(" ", "\n"));
            list.add(objects);

        }

        return list;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSal: {
                counter++;
                numSal.setText("" + formatter.format(counter));

                if (app.VIBR) {
                    app.VIB();
                }

                PlayAnim();
                break;
            }
            case R.id.reset: {
                Update();
                counter = 0;
                numSal.setText("" + formatter.format(counter));
                Save = false;
                share.edit().putBoolean("COMEBACK", false).apply();
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int mont = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int HH = cal.get(Calendar.HOUR_OF_DAY);
        int mm = cal.get(Calendar.MINUTE);

        Converter = new DateConverter();
        Converter.gregorianToPersian(year, mont + 1, day);
        Date = Converter.getYear() + "/" + Converter.getMonth() + "/" + Converter.getDay() + " " + HH + ":" + mm;

        share.edit().putBoolean("COMEBACK", true).apply();


        if (counter > 0) {

            if (Save) {
                Update();
                finish();
            } else {
                SaveOnBack();
            }

        } else {
            finish();
        }

    }

    private void Update() {
        if (counter > 0)
            salavt.execSQL(" UPDATE " + db.tables.SALAVAT + " SET " + db.SALAVAT.NUMBER + " = " + counter + " WHERE " + db.SALAVAT.ID + " = " + id);
    }

    private void SaveOnBack() {
        String c = String.valueOf(counter);
        if (counter > 0) {
            try {
                salavt.execSQL("INSERT INTO '" + db.tables.SALAVAT + "'('" + db.SALAVAT.NUMBER + "','" + db.SALAVAT.DATE + "') VALUES ('" + c + "','" + Date + "')");
                app.toastSUCCESS("Data Inserted ");
                finish();
            } catch (SQLiteException e) {
                app.l("Error ===============>" + e.toString());
            }
        }

    }

    private void recoverySalavt() {

        AlertDialogObject object = new AlertDialogObject();
        object.setColor("#1976D2")
                .setDismissAble(false)
                .setTitle(getString(R.string.deleteItem))
                .setTitleColor(R.color.black)
                .setTitleIcon(R.drawable.ic_autorenew)
                .setMessage(getString(R.string.deleteMessage).replace("?", "this items"))

                .setPositiveDisplay(true)
                .setPositive(getString(R.string.positiveText))
                .setPositiveColor(R.color.Green)
                .setPositiveIcon(R.drawable.ic_sentiment_satisfied)

                .setNegativeDisplay(true)
                .setNegative(getString(R.string.negativeText))
                .setNegativeColor(R.color.delete)
                .setNegativeIcon(R.drawable.ic_sentiment_very_dissatisfied)

                .setNeutralDisplay(false)
                .setListener(new DialogListener() {
                    @Override
                    public void onPositiveClick(String input) {
                        Save = true;
                        dialog.hide();
                    }

                    @Override
                    public void onNegativeClick(String input1) {
                        counter = 0;
                        numSal.setText("" + formatter.format(counter));
                        Save = false;
                        dialog.hide();
                    }

                    @Override
                    public void onNeutralClick(String input) {

                    }

                    @Override
                    public void onDismissAble(String input) {
                        dialog.hide();
                        dialog = null;
                    }
                })
        ;

        dialog = new DialogView(this, object);
        dialog.setCancelable(object.getDismissAble());
        dialog.show();
    }

    private void PlayAnim() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lottieArrow.playAnimation();
            }
        }, 2000);
    }


}
