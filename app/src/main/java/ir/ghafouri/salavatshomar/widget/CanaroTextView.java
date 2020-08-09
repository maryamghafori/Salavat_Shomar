package ir.ghafouri.salavatshomar.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import app.Application;

public class CanaroTextView extends TextView {

    public CanaroTextView(Context context) {
        this(context, null);
    }

    public CanaroTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanaroTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(Application.canaroExtraBold);
    }

}
