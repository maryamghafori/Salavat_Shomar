package ir.ghafouri.salavatshomar.Views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ir.ghafouri.salavatshomar.Objects.AlertDialogObject;
import ir.ghafouri.salavatshomar.R;

public class DialogView extends AlertDialog implements View.OnClickListener {

    private AlertDialogObject object;
    private AppCompatImageView neutralIcon, negativeIcon, positiveIcon, titleIcon;
    private View color;
    private LinearLayout positiveColor, negativeColor, neutralColor, positive, negative, neutral;
    private TextView neutralText, negativeText, positiveText, message, title;
    private AppCompatEditText Input;
    private Context context;

    public DialogView(@NonNull Context context, AlertDialogObject object) {
        super(context);
        this.object = object;
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_alert_dialog);
        initViews();
        init();
    }

    private void initViews() {
        neutralIcon = findViewById(R.id.neutralIcon);
        negativeIcon = findViewById(R.id.negativeIcon);
        positiveIcon = findViewById(R.id.positiveIcon);
        titleIcon = findViewById(R.id.titleIcon);

        color = findViewById(R.id.viewC);

        positiveColor = findViewById(R.id.positiveColor);
        negativeColor = findViewById(R.id.negativeColor);
        neutralColor = findViewById(R.id.neutralColor);

        positive = findViewById(R.id.positiveMD);
        negative = findViewById(R.id.negativeMD);
        neutral = findViewById(R.id.neutralMD);

        neutralText = findViewById(R.id.neutralText);
        negativeText = findViewById(R.id.negativeText);
        positiveText = findViewById(R.id.positiveText);
        message = findViewById(R.id.messageMD);
        title = findViewById(R.id.titleMD);

        Input = findViewById(R.id.inputMD);

        positive.setOnClickListener(this);
        negative.setOnClickListener(this);
        neutral.setOnClickListener(this);

    }

    private void init() {

        title.setText(object.getTitle());
        title.setTextColor(context.getResources().getColor(object.getTitleColor()));
        titleIcon.setImageResource(object.getTitleIcon());

        color.setBackgroundColor(object.getColor());
        message.setText(object.getMessage());
        if (object.getInput()) Input.setVisibility(View.VISIBLE);

        if (object.getPositiveDisplay()) {
            positiveColor.setVisibility(View.VISIBLE);
            positiveColor.setBackgroundColor(context.getResources().getColor(object.getPositiveColor()));
            positiveText.setText(object.getPositive());
            positiveIcon.setImageResource(object.getPositiveIcon());
        }

        if (object.getNegativeDisplay()) {
            negativeColor.setVisibility(View.VISIBLE);
            negativeColor.setBackgroundColor(context.getResources().getColor(object.getNegativeColor()));
            negativeText.setText(object.getNegative());
            negativeIcon.setImageResource(object.getNegativeIcon());
        }

        if (object.getNeutralDisplay()) {
            neutralColor.setVisibility(View.VISIBLE);
            neutralColor.setBackgroundColor(context.getResources().getColor(object.getNeutralColor()));
            neutralText.setText(object.getNeutral());
            neutralIcon.setImageResource(object.getNeutralIcon());
        }

    }

    @Override
    public void onClick(View v) {

        if (v == positive) {
            if (object.getListener() != null) ;
            object.getListener().onPositiveClick(Input.getText().toString());
        }
        else if (v == negative) {
            if (object.getListener() != null) ;
            object.getListener().onNegativeClick(Input.getText().toString());
        }
        else if (v == neutral) {
            if (object.getListener() != null) ;
            object.getListener().onNeutralClick(Input.getText().toString());
        }
    }
}
