package ir.ghafouri.salavatshomar.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import ir.ghafouri.salavatshomar.R;
import app.app;

public class A07RekatShomar extends AppCompatActivity {

    private static final String TAG = "Place ActMain";
    private static final long WAIT_TIME = 1000;

    ImageView img_filled_frame, img_empty_frame;
    TextView txt_rokat, txt_sajde;
    Button btn_reset;

    private int step;
    private long waiting_time = 0;
    private Boolean isNear = false;
    private SensorManager mSensorManager;
    private Sensor mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_a07_rekat_shomar);
        findViews();
        setListeners();
        prepare();
    }

    private void findViews() {
        txt_rokat = findViewById(R.id.act_main_txt_rokat);
        txt_sajde = findViewById(R.id.act_main_txt_sajde);
        btn_reset = findViewById(R.id.act_main_btn_reset);
        img_empty_frame = findViewById(R.id.act_main_img_frame_empty);
        img_filled_frame = findViewById(R.id.act_main_img_frame_filled);
    }

    private void setListeners() {

        btn_reset.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                step = 1;
                setStep(step);
            }
        });
    }

    private void prepare() {

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                img_filled_frame.startAnimation(AnimationUtils.loadAnimation(A07RekatShomar.this, R.anim.fade_out));

                mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
                mSensorManager.registerListener(sens_listener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }, 2000);

        //Reset Counter
        step = 1;
        setStep(step);

    }

    private SensorEventListener sens_listener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {

            if (event.values[0] == 0) {
                app.l("Near");
                if (waiting_time < getTime())
                    isNear = true;

            } else {
                app.l("Far");
                if (isNear) {
                    isNear = false;
                    setStep(++step);
                    waiting_time = getTime() + WAIT_TIME;
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    private boolean setStep(int currentStep) {
        switch (currentStep) {
            case 1:
                txt_rokat.setText(getResources().getStringArray(R.array.str_rokat)[0]);
                txt_sajde.setText(getResources().getStringArray(R.array.str_sajde)[0]);
                break;
            case 2:
                txt_rokat.setText(getResources().getStringArray(R.array.str_rokat)[0]);
                txt_sajde.setText(getResources().getStringArray(R.array.str_sajde)[1]);
                break;
            case 3:
                txt_rokat.setText(getResources().getStringArray(R.array.str_rokat)[1]);
                txt_sajde.setText(getResources().getStringArray(R.array.str_sajde)[0]);
                break;
            case 4:
                txt_rokat.setText(getResources().getStringArray(R.array.str_rokat)[1]);
                txt_sajde.setText(getResources().getStringArray(R.array.str_sajde)[1]);
                break;
            case 5:
                txt_rokat.setText(getResources().getStringArray(R.array.str_rokat)[1]);
                txt_sajde.setText("تشهد");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        txt_rokat.setText(getResources().
                                getStringArray(R.array.str_rokat)[2]);
                        txt_sajde.setText(getResources().
                                getStringArray(R.array.str_sajde)[0]);
                        txt_rokat.setAnimation(AnimationUtils.loadAnimation(A07RekatShomar.this, R.anim.fade_in));
                        txt_sajde.setAnimation(AnimationUtils.loadAnimation(A07RekatShomar.this, R.anim.fade_in));
                    }
                }, 5000);
                break;
            case 6:
                txt_rokat.setText(getResources().getStringArray(R.array.str_rokat)[2]);
                txt_sajde.setText(getResources().getStringArray(R.array.str_sajde)[1]);
                break;
            case 7:
                txt_rokat.setText(getResources().getStringArray(R.array.str_rokat)[3]);
                txt_sajde.setText(getResources().getStringArray(R.array.str_sajde)[0]);
                break;
            case 8:
                txt_rokat.setText(getResources().getStringArray(R.array.str_rokat)[3]);
                txt_sajde.setText(getResources().getStringArray(R.array.str_sajde)[1]);
                break;
            case 9:
                txt_rokat.setText(getResources().getStringArray(R.array.str_rokat)[3]);
                txt_sajde.setText("سلام و تشهد");
                break;

            default:
                return false;
        }
        txt_rokat.setAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        txt_sajde.setAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        return true;
    }

    private long getTime() {
        return Calendar.getInstance().getTimeInMillis();
    }

    @Override
    protected void onDestroy() {
        mSensorManager.unregisterListener(sens_listener);
        super.onDestroy();
    }

}
