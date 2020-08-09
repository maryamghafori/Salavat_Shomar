package ir.ghafouri.salavatshomar.Activitys;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.Calendar;

import app.app;
import ir.ghafouri.salavatshomar.R;

public class A04ZekreHafte extends AppCompatActivity {


    AppCompatTextView LblZhekerHafte, LblZhekerRoz, btnNumberZH;
    AppCompatButton resetZHk;

    int ZekerNum = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a04_zekre_hafte);
        init();
    }

    private void init() {

        LblZhekerRoz = findViewById(R.id.LblZhekerRoz);
        LblZhekerHafte = findViewById(R.id.LblZhekerHafte);
        btnNumberZH = findViewById(R.id.btnNumberZH);
        resetZHk = findViewById(R.id.resetZHk);

        Typeface QuranTahaFont = Typeface.createFromAsset(getAssets(), "fonts/qurantaha.ttf");
        LblZhekerHafte.setTypeface(QuranTahaFont);

        final Animation animFade = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SATURDAY:
                LblZhekerRoz.setText("شنبه");
                LblZhekerHafte.setText("یا رَبَّ الْعالَمین");
                break;
            case Calendar.SUNDAY:
                LblZhekerRoz.setText("یک شنبه");
                LblZhekerHafte.setText("یا ذَالْجَلالِ وَالْاِکْرام");
                break;
            case Calendar.MONDAY:
                LblZhekerRoz.setText("دو شنبه");
                LblZhekerHafte.setText("یا قاضِیَ الْحاجات");
                break;
            case Calendar.TUESDAY:
                LblZhekerRoz.setText("سه شنبه");
                LblZhekerHafte.setText("یا اَرْحَمَ الرّاحِمین");
                break;
            case Calendar.WEDNESDAY:
                LblZhekerRoz.setText("چهار شنبه");
                LblZhekerHafte.setText("یا حَیُّ یا قَیّوم");
                break;
            case Calendar.THURSDAY:
                LblZhekerRoz.setText("پنج شنبه");
                LblZhekerHafte.setText(" لا اِلهَ اِلّا اللهُ الْمَلِکُ الْحَقُّ الْمُبین");
                break;
            case Calendar.FRIDAY:
                LblZhekerRoz.setText("جمعه");
                LblZhekerHafte.setText("اَللّهُمَّ صَلِّ عَلی مُحَمَّدٍ وَ آلِ مُحَمَّدٍ وَ عَجِّلْ فَرَجَهُمْ");
                break;
        }

        btnNumberZH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (app.VIBR) {
                    app.VIB();
                }

                ZekerNum--;
                btnNumberZH.startAnimation(animFade);
                btnNumberZH.setText(String.valueOf(ZekerNum));
                if (ZekerNum == 0) {
                    app.toastSUCCESS("قبول حق");
                    ZekerNum = 100;
                    btnNumberZH.startAnimation(animFade);
                    btnNumberZH.setText(String.valueOf(ZekerNum));
                }
            }
        });

        resetZHk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZekerNum = 100;
                btnNumberZH.startAnimation(animFade);
                btnNumberZH.setText(String.valueOf(ZekerNum));
            }
        });
    }
}
