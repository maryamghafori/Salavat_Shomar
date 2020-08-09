package ir.ghafouri.salavatshomar.Activitys;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import app.app;
import ir.ghafouri.salavatshomar.Interfaces.DialogListener;
import ir.ghafouri.salavatshomar.Objects.AlertDialogObject;
import ir.ghafouri.salavatshomar.R;
import ir.ghafouri.salavatshomar.Views.DialogView;

public class A06Tasbihat extends AppCompatActivity implements View.OnClickListener {


    AppCompatTextView lblNumber, lblText;
    AppCompatButton btnNumber, reset;
    Typeface QuranTahaFont;
    DialogView dialog;

    Animation animFade;

    private final int maxAlhamd = 33, maxAlahAkbar = 34, maxSobhanAllah = 33;
    private int alhamd = 0, alahAkbar = 0, sobhanAlah = 0;
    private boolean a = true, b = false, c = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a06_tasbihat);

        init();
    }

    private void init() {
        lblNumber = findViewById(R.id.lblNumber);
        lblText = findViewById(R.id.LblZheker);
        btnNumber = findViewById(R.id.btnNumber);
        reset = findViewById(R.id.resetTAs);

        animFade = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        QuranTahaFont = Typeface.createFromAsset(getAssets(), "fonts/qurantaha.ttf");
        lblText.setTypeface(QuranTahaFont);

        lblNumber.setText(String.valueOf(alahAkbar));

        btnNumber.setOnClickListener(this);
        reset.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnNumber: {

                if (app.VIBR) {
                    app.VIB();
                }

                if (a) {
                    alahAkbar++;
                    lblNumber.startAnimation(animFade);
                    lblNumber.setText(String.valueOf(alahAkbar));
                    if (alahAkbar == maxAlahAkbar) {
                        lblText.setText(getResources().getText(R.string.alhamdAllah));
                        lblNumber.setText("0");
                        a = false;
                        b = true;
                    }
                } else if (b) {
                    alhamd++;
                    lblNumber.setText(String.valueOf(alhamd));
                    if (alhamd == maxAlhamd) {
                        lblText.setText(getResources().getText(R.string.sohbhanAllah));
                        lblNumber.setText("0");
                        b = false;
                        c = true;
                    }
                } else if (c) {
                    sobhanAlah++;
                    lblNumber.setText(String.valueOf(sobhanAlah));
                    if (sobhanAlah == maxSobhanAllah) {
                        EndZeker();
                        alhamd = alahAkbar = sobhanAlah = 0;
                        c = false;
                        a = true;
                        lblText.setText(getResources().getText(R.string.allhoAkbar));
                        lblNumber.setText(String.valueOf(alahAkbar));
                    }
                }
                break;
            }

            case R.id.resetTAs: {
                alhamd = alahAkbar = sobhanAlah = 0;
                b = c = false;
                a = true;
                lblText.setText(getResources().getText(R.string.allhoAkbar));
                lblNumber.setText(String.valueOf(alahAkbar));
                break;
            }
        }
    }

    private void EndZeker() {

        AlertDialogObject object = new AlertDialogObject();
        object.setColor("#1976D2")
                .setDismissAble(true)
                .setTitle(getString(R.string.eltemasDoaa))
                .setTitleColor(R.color.black)
                .setTitleIcon(R.drawable.ic_autorenew)
                .setMessage(getString(R.string.eltemasDoaaMess))

                .setPositiveDisplay(true)
                .setPositive(getString(R.string.hatman))
                .setPositiveColor(R.color.Green)
                .setPositiveIcon(R.drawable.ic_sentiment_satisfied)

                .setNegativeDisplay(false)
                .setNeutralDisplay(false)
                .setListener(new DialogListener() {
                    @Override
                    public void onPositiveClick(String input) {
                        dialog.hide();
                    }

                    @Override
                    public void onNegativeClick(String input1) {
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

}
